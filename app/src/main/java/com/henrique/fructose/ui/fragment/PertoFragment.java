package com.henrique.fructose.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.henrique.fructose.R;
import com.henrique.fructose.adapter.RestauranteAdapter;
import com.henrique.fructose.model.restaurant.RestaurantResponse;
import com.henrique.fructose.viewmodel.MainFragmentViewModel;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.henrique.fructose.util.LoadingHelper.failure;

/**
 * A simple {@link Fragment} subclass.
 */
public class PertoFragment extends Fragment {

    private MainFragmentViewModel mViewModel;
    private RestauranteAdapter restauranteAdapter;
    private RecyclerView recyclerRestaurantes;
    private View rootView;
    private RestaurantResponse restauranteResponse;
    private SwipeRefreshLayout swipePerto;
    private ConstraintLayout clNoRestaurant;


    public PertoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_perto, container, false);
        setupLayout();
        mViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);
        mViewModel.init(getActivity());
        listRestaurants();
        return rootView;
    }

    private void setupLayout() {
        setupAdapter();
        setupRecyclerView();
        setupSwipe();
        //listRestaurants();
    }

    private void setupSwipe() {
        clNoRestaurant = rootView.findViewById(R.id.clNoRestaurant);
        swipePerto = rootView.findViewById(R.id.swipePerto);
        swipePerto.setOnRefreshListener(this::listRestaurants);
    }

    private void setupAdapter() {
      //  restauranteResponse = new RestaurantResponse(new ArrayList<>(),0,0,0);

    }

    private void setupRecyclerView() {
        recyclerRestaurantes = rootView.findViewById(R.id.recyclerRestaurantesPerto);
        recyclerRestaurantes.setHasFixedSize(true);
    }

    private void listRestaurants() {
        mViewModel.getRestauranteRepository().observe(this, (restaurantResponse) -> {
            try {

                if (restaurantResponse.getResultsFound() > 0) {
                    restauranteAdapter = new RestauranteAdapter(restaurantResponse,
                            getActivity(), () -> {
                        //TODO REDIRECT TO THE RESTAURANT PAGE
                    });
                    noPlace(VISIBLE, GONE);
                    recyclerRestaurantes.setAdapter(restauranteAdapter);

                    restauranteResponse = restaurantResponse;
                   // Toast.makeText(getActivity(), "Aqui", Toast.LENGTH_SHORT).show();
                   // restauranteAdapter.notifyDataSetChanged();
                    swipePerto.setRefreshing(false);
                }
                else noPlace(GONE, VISIBLE);

            } catch (Exception e) {
                failure(getActivity());
                swipePerto.setRefreshing(false);
                noPlace(GONE, VISIBLE);
            }
        });

    }

    private void noPlace(int visibilitySwipe, int visibilityConstrains) {
        swipePerto.setVisibility(visibilitySwipe);
        clNoRestaurant.setVisibility(visibilityConstrains);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
