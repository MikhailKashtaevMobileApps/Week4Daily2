package com.example.mike.week4daily2.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ImageEditor {

    private final ImageView view;

    public ImageEditor(ImageView view){
        this.view = view;
    }

    @SuppressLint("CheckResult")
    public void setImage(final String url){

        Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                return loadBitmap(url);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                view.setImageBitmap(bitmap);
            }
        });
    }

    private Bitmap loadBitmap(String url) throws IOException {
        InputStream in = new java.net.URL(url).openStream();
        return BitmapFactory.decodeStream(in);
    }

}
