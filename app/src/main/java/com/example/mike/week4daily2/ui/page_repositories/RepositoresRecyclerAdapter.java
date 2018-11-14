package com.example.mike.week4daily2.ui.page_repositories;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mike.week4daily2.R;
import com.example.mike.week4daily2.github.remote.gson_repositories.Repository;
import com.example.mike.week4daily2.utils.DateFormatter;

import java.util.List;

public class RepositoresRecyclerAdapter extends RecyclerView.Adapter< RepositoresRecyclerAdapter.ViewHolder > {

    List<Repository> repositoryList;

    public RepositoresRecyclerAdapter(List<Repository> repos) {
        repositoryList = repos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.repository_item, viewGroup, false );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Repository r = repositoryList.get(i);
        viewHolder.repoName.setText( r.getName() );
        viewHolder.repoLink.setText(Html.fromHtml( "<a href=\"+" +r.getUrl()+ "+\" >See Page</a>"));

        final Context context = viewHolder.repoLink.getContext();

        viewHolder.repoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = r.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        viewHolder.repoCreated.setText("Created: "+DateFormatter.formatDate( r.getCreatedAt() ));
        viewHolder.repoUpdated.setText("Updated: "+DateFormatter.formatDate( r.getUpdatedAt() ));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView repoName;
        TextView repoLink;
        TextView repoCreated;
        TextView repoUpdated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById( R.id.repoName );
            repoLink = itemView.findViewById( R.id.repoLink );
            repoCreated = itemView.findViewById( R.id.repoCreated );
            repoUpdated = itemView.findViewById( R.id.repoUpdated );
        }
    }

}
