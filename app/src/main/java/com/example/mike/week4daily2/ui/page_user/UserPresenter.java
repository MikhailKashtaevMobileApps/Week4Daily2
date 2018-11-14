package com.example.mike.week4daily2.ui.page_user;

import com.example.mike.week4daily2.github.GithubRepository;
import com.example.mike.week4daily2.github.remote.gson_repositories.Repository;
import com.example.mike.week4daily2.github.remote.gson_user.User;

import java.util.List;

public class UserPresenter implements UserContract.Presenter {

    UserContract.View view;

    @Override
    public void onAttach(UserContract.View v) {
        this.view = v;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void getUser(String userName) {

        GithubRepository.getUser( userName, new GithubRepository.CallbackUser(){
            @Override
            public void success(User user) {
                view.onGetUser(user);
            }

            @Override
            public void error(String e) {
                view.onError( e );
            }
        } );
    }

    @Override
    public void getRepos(String userName) {
        GithubRepository.getUserRepositories(userName, new GithubRepository.CallbackRepos() {
            @Override
            public void success(List<Repository> repos) {
                System.out.println("UserPresenter.success");
                view.onGetRepos(repos);
            }

            @Override
            public void error(String e) {
                view.onError(e);
            }
        });
    }
}
