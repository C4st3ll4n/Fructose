package com.henrique.fructose.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.BubbleToggleView;
import com.henrique.fructose.R;

import static com.henrique.fructose.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    BubbleNavigationConstraintView navigationConstraintView;
    BubbleToggleView btvCategoria, btvProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        setupLayout();
    }

    private void setupLayout() {
        setupBubbleNavigation();
    }

    private void setupBubbleNavigation() {
        navigationConstraintView = findViewById(R.id.bottomNavigation);
        btvCategoria             = findViewById(R.id.btvCategoria);
        btvProximo               = findViewById(R.id.btvProximo);

        navigationConstraintView.setNavigationChangeListener((view, position) ->
        {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        });
    }
}
