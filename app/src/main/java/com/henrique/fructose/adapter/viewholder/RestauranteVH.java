package com.henrique.fructose.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.henrique.fructose.R.id.imgRestauranteItem;
import static com.henrique.fructose.R.id.txtAvalicaoRestauranteItem;
import static com.henrique.fructose.R.id.txtDescricaoRestauranteItem;
import static com.henrique.fructose.R.id.txtNomeRestauranteItem;
import static com.henrique.fructose.R.id.txtStatusRestauranteItem;

public class RestauranteVH extends RecyclerView.ViewHolder {
    public TextView txtSituacao, txtAvaliacao, txtNome, txtDescricao;
    public ImageView img;

    public RestauranteVH(@NonNull View itemView) {
        super(itemView);
        txtSituacao     = itemView.findViewById(txtStatusRestauranteItem);
        txtAvaliacao    = itemView.findViewById(txtAvalicaoRestauranteItem);
        txtNome         = itemView.findViewById(txtNomeRestauranteItem);
        txtDescricao    = itemView.findViewById(txtDescricaoRestauranteItem);
        img             = itemView.findViewById(imgRestauranteItem);
    }
}
