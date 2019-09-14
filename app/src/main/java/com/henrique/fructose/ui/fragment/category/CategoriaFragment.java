package com.henrique.fructose.ui.fragment.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.henrique.fructose.R;
import com.henrique.fructose.adapter.CategoriaAdapter;
import com.henrique.fructose.api.ApiPexelsClient;
import com.henrique.fructose.api.ApiPexelsService;
import com.henrique.fructose.model.picture.Pexels;
import com.henrique.fructose.util.LoadingHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

import static com.henrique.fructose.util.LoadingHelper.*;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class CategoriaFragment extends Fragment {

    private CategoriaViewModel mViewModel;
    private CategoriaAdapter categoriaAdapter;
    private View rootView;
    private ApiPexelsService apiService;// = ApiPexelsClient.getClient().create(ApiPexelsClient.class);
    private List<String> urls = new ArrayList<>();
    private RecyclerView recyclerCategorias;

    public static CategoriaFragment newInstance() {
        return new CategoriaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.categoria_fragment, container, false);
        setupLayout();
        return rootView;
    }

    private void setupLayout() {

        recyclerCategorias = rootView.findViewById(R.id.recyclerCategorias);
        recyclerCategorias.setHasFixedSize(true);
    }

    @SuppressLint("CheckResult")
    private void getUrlPexels(String nome, ImageView imgview) {
        apiService.searchPic(nome, 1).subscribeOn(io())
                .observeOn(mainThread())
                .subscribeWith(new DisposableSingleObserver<Pexels>() {
                    @Override
                    public void onSuccess(Pexels pexels) {
                       // Toast.makeText(getActivity(), "Aqui", Toast.LENGTH_SHORT).show();
                        String url = pexels.getPhotos().get(0).getSrc().getSmall();
                        if (url != null &&
                                !url.isEmpty()) {
                            Glide.with(getActivity()).load(url)
                                    .centerCrop()
                                    .into(imgview);
                    }
                        }

                        //sucess();

                    @Override
                    public void onError(Throwable e) {
                        failure(getActivity());
                        Log.d("Deu ruim", e.getLocalizedMessage());
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        start(getActivity());
                    }
                });

        //return urls.get(pos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        apiService = ApiPexelsClient.getClient(getActivity()).create(ApiPexelsService.class);

        //setupLayout();
        mViewModel = ViewModelProviders.of(this).get(CategoriaViewModel.class);
        mViewModel.init(getActivity());

        mViewModel.getCategoryRepository().observe(this, categoryResponse -> {

            categoriaAdapter = new CategoriaAdapter(categoryResponse.getCategories(), getActivity(),
                    () -> Toast.makeText(getActivity(), "You clicked", Toast.LENGTH_SHORT).show(),
                    (name, pos, imgview) -> {
                        getUrlPexels(name, imgview);
                        //String url = urls.get(pos);

                    });

            recyclerCategorias.setAdapter(categoriaAdapter);

            //Toast.makeText(getActivity(),categoryResponse.getCategories().toString() , Toast.LENGTH_SHORT).show();
        });
        //categoriaAdapter.notifyDataSetChanged();
    }
}
