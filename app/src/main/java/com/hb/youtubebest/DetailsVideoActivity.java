package com.hb.youtubebest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.hb.youtubebest.pojos.Video;

import java.util.Locale;

public class DetailsVideoActivity extends AppCompatActivity {
    private static final String YT_URL = "https://www.youtube.com/watch?v=";
    private Video video;
    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvUrl;
    private TextView tvCategory;
    private Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_video);

        tvTitle = findViewById(R.id.tvTitleDetails);
        tvDesc = findViewById(R.id.tvDescDetails);
        tvUrl = findViewById(R.id.tvUrlDetails);
        tvCategory = findViewById(R.id.tvCategoryDetails);
        btnView = findViewById(R.id.btnView);

        Intent i = getIntent();
        video = i.getParcelableExtra(MainActivity.KEY_VIDEO);

        System.out.println(video);

        tvTitle.setText(video.getTitle());
        tvDesc.setText(video.getDescription());
        tvUrl.setText(String.format(Locale.FRANCE,"%s%s", YT_URL, video.getUrl()));
        tvCategory.setText(video.getCategory());

        btnView.setOnClickListener(v -> {

            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getUrl()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(YT_URL + video.getUrl()));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(webIntent);
            }
        });
    }
}