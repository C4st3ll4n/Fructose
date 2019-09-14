package com.henrique.fructose.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.henrique.fructose.R;

import java.util.ArrayList;
import java.util.List;

import static com.henrique.fructose.R.string.description1;
import static com.henrique.fructose.R.string.description2;
import static com.henrique.fructose.R.string.description3;
import static com.henrique.fructose.R.string.title1;
import static com.henrique.fructose.R.string.title2;
import static com.henrique.fructose.R.string.title3;

public class StarActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setupIntroCards();

    }

    private void setupIntroCards() {
        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard(getString(title1),
                getString(description1), R.drawable.ic_fruta);

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard(getString(title2)
                ,getString(description2), R.drawable.ic_lambreta);

        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard(getString(title3),
                getString(description3), R.drawable.ic_jantar);

        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        setOnboardPages(pages);
        showNavigationControls(false);

//Set pager indicator colors
        setGradientBackground();
        setInactiveIndicatorColor(R.color.grey_200);
        setActiveIndicatorColor(R.color.white);

//Set finish button text
        setFinishButtonTitle("Iniciar");
    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(this, TestActivity.class));//TODO TROCAR ACTIVITY
        finish();
    }
}