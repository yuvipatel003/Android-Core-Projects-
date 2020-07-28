package com.yuvrajpatel.rxjava_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yuvrajpatel.rxjava_application.Model.Post;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerAdapter";

    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void updatePost(Post post){
        posts.set(posts.indexOf(post), post);
        notifyItemChanged(posts.indexOf(post));
    }

    public List<Post> getPosts(){
        return posts;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, numComments;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            numComments = itemView.findViewById(R.id.num_comments);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        public void bind(Post post){
            title.setText(post.getTitle());

            if(post.getComments() == null){
                showProgressBar(true);
                numComments.setText("");
            }
            else{
                showProgressBar(false);
                numComments.setText(String.valueOf(post.getComments().size()));
            }
        }

        private void showProgressBar(boolean showProgressBar){
            if(showProgressBar) {
                progressBar.setVisibility(View.VISIBLE);
            }
            else{
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
