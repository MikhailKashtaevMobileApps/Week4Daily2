package com.example.mike.week4daily2.ui.page_repositories;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mike.week4daily2.R;
import com.example.mike.week4daily2.github.remote.gson_repositories.Repository;
import com.example.mike.week4daily2.github.remote.gson_user.User;
import com.example.mike.week4daily2.ui.page_user.UserContract;
import com.example.mike.week4daily2.ui.page_user.UserPresenter;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesActivity extends AppCompatActivity implements UserContract.View {

    private UserPresenter userPresenter;
    private RecyclerView rvRepositoriesList;
    private List<Repository> repositories;
    private RepositoresRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        repositories = new ArrayList<>();
        rvRepositoriesList = findViewById( R.id.rvRepositoriesList );
        adapter = new RepositoresRecyclerAdapter(repositories);
        rvRepositoriesList.setAdapter(adapter);
        rvRepositoriesList.setLayoutManager( new LinearLayoutManager(this));

        userPresenter = new UserPresenter();
        userPresenter.onAttach( this );
        userPresenter.getRepos("MikhailKashtaevMobileApps");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.onDetach();
    }

    @Override
    public void onGetUser(User u) {

    }

    @Override
    public void onGetRepos(List<Repository> r) {
        System.out.println("RepositoriesActivity.onGetRepos __TAG__");
        this.repositories = r;
        adapter.repositoryList = r;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String e) {
        Toast.makeText( this, e, Toast.LENGTH_SHORT ).show();
    }
}
