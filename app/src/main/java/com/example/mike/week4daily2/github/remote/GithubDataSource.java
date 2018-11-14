package com.example.mike.week4daily2.github.remote;

import android.util.Log;

import com.example.mike.week4daily2.github.GithubRepository;
import com.example.mike.week4daily2.github.remote.gson_repositories.Repository;
import com.example.mike.week4daily2.github.remote.gson_user.User;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class GithubDataSource {

    public static final String TAG = GithubDataSource.class.getSimpleName()+"__TAG__";

    public static Observable<User> getUser(String userName ){
        return getService().getUser(userName, "81b11e8fd340f52801bce3bbbcb1f77e043aa8ed");
    }

    public static Observable<List<Repository>> getRepos(String userName ){
        System.out.println("__TAG__GithubDataSource.getRepos");
        return getService().getRepos(userName, "81b11e8fd340f52801bce3bbbcb1f77e043aa8ed");
    }

    private static GithubService getService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create( GithubService.class );
    }



    public interface GithubService {

        @GET("/users/{userName}")
        Observable<User> getUser(@Path("userName") String userName, @Query("access_token") String accessToken);

        @GET("users/{userName}/repos")
        Observable<List<Repository>> getRepos(@Path("userName") String userName, @Query("access_token") String accessToken);
    }


}
