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

    public static TTFancyGifDialog sucess(Activity context, String title, String message){
        init(context);
        dialogBuilder.setTitle(title).setMessage(message)
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

    public static TTFancyGifDialog failure(Activity context, String title, String message){
        init(context);
        dialogBuilder.setTitle(title).setMessage(message)
                .setGifResource(R.drawable.failure).setPositiveBtnText("OK");
        dialog = dialogBuilder.build();
        return dialog;
    }

    public static TTFancyGifDialog start(Activity context){
        init(context);
        return start(context, "Carregando..... ",
                "Aguarde um pouco, estamos trazendo seu cardápio saudável.");
    }

    public static TTFancyGifDialog start(Activity context, String mensage){
        init(context);
        return start(context, "Carregando..... ",
                mensage);
    }

    public static TTFancyGifDialog start(Activity context, String mensage, String title){
        init(context);
        dialogBuilder.setTitle(title).setMessage(mensage)
                .setGifResource(R.drawable.loading).setPositiveBtnText("OK");
        dialog = dialogBuilder.build();
        return dialog;
    }

}
