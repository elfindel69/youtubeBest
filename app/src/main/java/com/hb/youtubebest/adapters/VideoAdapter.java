package com.hb.youtubebest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.youtubebest.R;
import com.hb.youtubebest.pojos.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private List<Video> videos;

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvDescription;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }

    public VideoAdapter(List<Video> videos){
        this.videos = videos;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,
                false);
        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {
        Video todo = videos.get(position);

        holder.tvTitle.setText(todo.getTitle());
        holder.tvDescription.setText(todo.getDescription());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
