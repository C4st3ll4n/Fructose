package com.henrique.fructose.util;

import android.app.Activity;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.henrique.fructose.R;

import static com.henrique.fructose.R.string.*;

public class LoadingHelper {


    private static TTFancyGifDialog.Builder dialogBuilder;
    private static TTFancyGifDialog dialog;

    private static void init(Activity context) {
        dialogBuilder = new TTFancyGifDialog.Builder(context).isCancellable(true);
    }

    public static TTFancyGifDialog sucess(Activity context){
        init(context);
        dialogBuilder.setTitle(context.getString(sucess_title)).setMessage(context.getString(success))
                .setGifResource(R.drawable.sucess).setPositiveBtnText("OK");
        dialog = dialogBuilder.build();
        return dialog;
    }


    public static TTFancyGifDialog failure(Activity context ){
        init(context);
        dialogBuilder.setTitle(context.getString(failure_title)).setMessage(context.getString(failure))
                .setGifResource(R.drawable.failure).setPositiveBtnText("OK");
        dialog = dialogBuilder.build();
        return dialog;
    }
    public static TTFancyGifDialog start(Activity context){
        init(context);
        dialogBuilder.setTitle("Carregando....").setMessage("Aguarde um pouco, estamos trazendo seu cardápio saudável.")
                .setGifResource(R.drawable.loading).setPositiveBtnText("OK");
        dialog = dialogBuilder.build();
        return dialog;
    }

}
