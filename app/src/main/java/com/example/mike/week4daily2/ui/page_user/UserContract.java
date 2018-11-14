package com.example.mike.week4daily2.ui.page_user;

import com.example.mike.week4daily2.github.remote.gson_repositories.Repository;
import com.example.mike.week4daily2.github.remote.gson_user.User;
import com.example.mike.week4daily2.ui.base.BasePresenter;
import com.example.mike.week4daily2.ui.base.BaseView;

import java.util.List;

public class UserContract {

    public interface View extends BaseView {
        void onGetUser(User u);
        void onGetRepos(List<Repository> r);
        void onError(String e);
    }
    public interface Presenter extends BasePresenter{
        void onAttach(View v);
        void onDetach();
        void getUser(String userName);
        void getRepos(String userName);
    }

}
