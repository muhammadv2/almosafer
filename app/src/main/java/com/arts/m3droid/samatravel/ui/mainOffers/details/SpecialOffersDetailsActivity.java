package com.arts.m3droid.samatravel.ui.mainOffers.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.arts.m3droid.samatravel.Constants;
import com.arts.m3droid.samatravel.R;
import com.arts.m3droid.samatravel.model.SpecialOffer;
import com.arts.m3droid.samatravel.utils.ImageUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dd.processbutton.iml.ActionProcessButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.arts.m3droid.samatravel.utils.SocialMediaButtonsHandler.handleFb;
import static com.arts.m3droid.samatravel.utils.SocialMediaButtonsHandler.handleTwitter;

public class SpecialOffersDetailsActivity extends AppCompatActivity {

    @BindView(R.id.container)
    CoordinatorLayout container;

    @BindView(R.id.iv_offer_image)
    ImageView ivSpecialOffer;
    @BindView(R.id.btn_special_offer_request)
    ActionProcessButton btnRequestOffer;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.container_nested_scroll)
    NestedScrollView nestedScrollView;
    @BindView(R.id.tv_offer_details)
    TextView tvOfferDetails;
    @BindView(R.id.iv_twitter)
    ImageView ivTwitter;
    @BindView(R.id.iv_fb)
    ImageView ivFb;
    @BindView(R.id.iv_instagram)
    ImageView ivInstagram;
    @BindView(R.id.iv_snap)
    ImageView ivSnap;

    private SpecialOffer specialOffer;

    //Todo When click on the icons of social medias links works xD

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offer_details);
        ButterKnife.bind(this);

        Timber.plant(new Timber.DebugTree());

        handleReceivedIntent();
        setUpToolbar();
        setUpRequestButton();
        setUpAnimations();
        nestedScrollView.setNestedScrollingEnabled(true);

        ivFb.setOnClickListener(v -> handleFb(this));
        ivTwitter.setOnClickListener(v -> handleTwitter(this));
    }

    private void setUpAnimations() {
        YoYo.with(Techniques.BounceInUp)
                .duration(2000)
                .playOn(container);
    }

    private void setUpRequestButton() {
        btnRequestOffer.setMode(ActionProcessButton.Mode.ENDLESS);
        btnRequestOffer.setText(getResources().getText(R.string.txt_request_offer));
        btnRequestOffer.setProgress(75);
        btnRequestOffer.setOnClickListener(v -> {
            Intent intent = new Intent(this, RequestSpecialOfferActivity.class);
            intent.putExtra(Constants.DATA_SPECIAL_OFFER, specialOffer);
            startActivity(intent);
        });
    }

    private void handleReceivedIntent() {
        specialOffer = getIntent().getParcelableExtra(Constants.DATA_SPECIAL_OFFER);
        ImageUtils.setImageOnImageView(specialOffer.getImageUrl(), ivSpecialOffer);
        tvOfferDetails.setText(specialOffer.getDetails());
    }

    void setUpToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        actionBar.setTitle(specialOffer.getName());
    }


}