package com.hb.youtubebest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hb.youtubebest.dao.VideoDAO;
import com.hb.youtubebest.pojos.Video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddVideoActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDesc;
    private EditText etUrl;
    private Spinner spCategory;
    private Button btnAdd;
    private Video video;
    private VideoDAO videoDAO;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);

        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etUrl = findViewById(R.id.etUrl);
        spCategory = findViewById(R.id.spCategory);
        btnAdd = findViewById(R.id.btnAdd);

        context = getApplicationContext();
        videoDAO = new VideoDAO(context);

        String[] items = new String[]{
                "Films et animations",
                "Auto/moto",
                "Musique",
                "Animaux",
                "Sport",
                "Voyages et événements",
                "Jeux vidéo",
                "People et blogs",
                "Humour",
                "Divertissement",
                "Actualités et politique",
                "Vie pratique et style",
                "Education",
                "Science et technologie",
                "Associations et engagement"
        };

        final List<String> itemsList = new ArrayList<>(Arrays.asList(items));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this,R.layout.spinner_item,itemsList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spCategory.setAdapter(spinnerArrayAdapter);

        btnAdd.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String description = etDesc.getText().toString();
            String url = etUrl.getText().toString();
            String category = spCategory.getSelectedItem().toString();
            video = new Video(title,description,url,category);
            videoDAO.add(video);
            finish();
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}