package com.hb.youtubebest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hb.youtubebest.adapters.VideoAdapter;
import com.hb.youtubebest.dao.VideoDAO;
import com.hb.youtubebest.pojos.Video;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String KEY_VIDEO = "video";
    private Context context;
    private RecyclerView rvVideo;
    private VideoDAO videoDAO;
    private VideoAsyncTasks videoAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        videoDAO= new VideoDAO(context);

        rvVideo = findViewById(R.id.rvVideos);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvVideo.setHasFixedSize(true);
        rvVideo.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoAsyncTask = new VideoAsyncTasks();
        videoAsyncTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addVideo) {
            Intent intent = new Intent(context, AddVideoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public class VideoAsyncTasks extends AsyncTask<Nullable, Nullable, List<Video>>  {

        @Override
        protected List<Video> doInBackground(Nullable... nullables) {

            return videoDAO.list();
        }


        @Override
        protected void onPostExecute(List<Video> videos){
            rvVideo.setAdapter(new VideoAdapter(videos, item -> {
                System.out.println("click");
                Intent intent = new Intent(context, DetailsVideoActivity.class);
                intent.putExtra(KEY_VIDEO,item);
                startActivity(intent);
            }));
        }


    }


}