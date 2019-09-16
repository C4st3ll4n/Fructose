package com.henrique.fructose.ui.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.BubbleToggleView;
import com.henrique.fructose.R;
import com.henrique.fructose.ui.fragment.LoginFragment;
import com.henrique.fructose.ui.fragment.MainFragment;
import com.henrique.fructose.ui.fragment.PertoFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static androidx.fragment.app.FragmentTransaction.*;
import static com.henrique.fructose.R.id.*;
import static com.henrique.fructose.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    BubbleNavigationConstraintView navigationConstraintView;
    BubbleToggleView btvCategoria, btvProximo, btvPerfil;
    FragmentTransaction ft;

    MainFragment mf = MainFragment.newInstance();
    LoginFragment lf = new LoginFragment();
    PertoFragment pf = new PertoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        setupLayout();
        getKeyHASH();

    }

    private void getKeyHASH(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {

        }
        catch (NoSuchAlgorithmException e) {

        }
    }

    private void setupLayout() {
        setupBubbleNavigation();
        setupFragments();
    }

    private void setupFragments() {
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(containerFragment, mf ).commitNow();

    }

    private void setupBubbleNavigation() {
        navigationConstraintView = findViewById(bottomNavigation);
        btvCategoria             = findViewById(R.id.btvCategoria);
        btvProximo               = findViewById(R.id.btvProximo);
        btvPerfil                = findViewById(R.id.btvProfile);

        navigationConstraintView.setNavigationChangeListener((view, position) ->
        {
            switch (position) {
                case 0:
                    getSupportFragmentManager().beginTransaction()
                            .replace(containerFragment,mf)
                            .commitNow();
                    break;
                case 1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(containerFragment,pf)
                            .commitNow();
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction()
                            .replace(containerFragment,lf)
                            .commitNow();
                    break;
            }
        });
    }
}
