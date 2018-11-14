package com.example.mike.week4daily2.github;

import android.util.Log;

import com.example.mike.week4daily2.github.remote.GithubDataSource;
import com.example.mike.week4daily2.github.remote.gson_repositories.Repository;
import com.example.mike.week4daily2.github.remote.gson_user.User;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GithubRepository {

    public static final String TAG = GithubRepository.class.getSimpleName()+"__TAG__";

    public static void getUser(String userName, final CallbackUser callback ){
        Log.d(TAG, "getUser: ");
        GithubDataSource.getUser(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: "+user.getAvatarUrl());
                        callback.success(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("__TAG__GithubRepository.onError"+e.getMessage());
                        callback.error( e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static void getUserRepositories(String userName, final CallbackRepos callback){
        GithubDataSource.getRepos( userName )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Repository> repository) {
                        System.out.println("__TAG__GithubRepository.onNext"+repository.size());

                        callback.success( repository );
                    }

                    @Override
                    public void onError(Throwable e) {

                        System.out.println("__TAG__GithubRepository.onError"+e.getMessage());
                        callback.error( e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface CallbackUser{
        void success( User user );
        void error ( String e );
    }

    public interface CallbackRepos {
        void success( List<Repository> repos );
        void error( String e );
    }

}
