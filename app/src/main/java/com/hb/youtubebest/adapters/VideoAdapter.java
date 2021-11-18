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
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Video item);
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvDescription;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
        public void bind(final Video item, final OnItemClickListener listener) {
            tvTitle.setText(item.getTitle());
            tvDescription.setText(item.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public VideoAdapter(List<Video> videos, OnItemClickListener listener){
        this.videos = videos;
        this.listener = listener;
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
        Video video = videos.get(position);

        holder.bind(video,listener);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
