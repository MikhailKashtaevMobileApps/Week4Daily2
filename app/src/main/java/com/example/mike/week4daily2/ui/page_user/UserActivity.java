package com.example.mike.week4daily2.ui.page_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mike.week4daily2.R;
import com.example.mike.week4daily2.github.remote.gson_repositories.Repository;
import com.example.mike.week4daily2.github.remote.gson_user.User;
import com.example.mike.week4daily2.ui.page_repositories.RepositoriesActivity;
import com.example.mike.week4daily2.utils.DateFormatter;
import com.example.mike.week4daily2.utils.ImageEditor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserActivity extends AppCompatActivity implements UserContract.View {

    private UserPresenter userPresenter;
    private ImageView avatar;
    private TextView userName;
    private TextView createdOn;
    private TextView lastModified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPresenter = new UserPresenter();
        userPresenter.onAttach(this);

        avatar = findViewById( R.id.avatar );
        userName = findViewById( R.id.userName );
        createdOn = findViewById( R.id.createdOn );
        lastModified = findViewById( R.id.lastModified );

        userPresenter.getUser("MikhailKashtaevMobileApps");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.onDetach();
    }

    @Override
    public void onGetUser(User u) {
        userName.setText( u.getLogin() );
        createdOn.setText( "Created: "+DateFormatter.formatDate(u.getCreatedAt()) );
        lastModified.setText( "Updated: "+DateFormatter.formatDate( u.getUpdatedAt() ) );
        ImageEditor imageEditor = new ImageEditor(avatar);
        imageEditor.setImage( u.getAvatarUrl() );
    }

    @Override
    public void onGetRepos(List<Repository> r) {

    }

    @Override
    public void onError(String e) {
        Toast.makeText( this, "Got Error: "+e, Toast.LENGTH_SHORT ).show();
    }

    public void goRepositories(View view) {

        Intent intent = new Intent( getApplicationContext(), RepositoriesActivity.class);
        startActivity( intent );

    }
}
