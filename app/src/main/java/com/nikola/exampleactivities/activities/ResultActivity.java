package com.nikola.exampleactivities.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nikola.exampleactivities.R;

/**
 * Created by nikola on 6/7/17.
 */

public class ResultActivity extends AppCompatActivity{

    ListAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String[] data = (String[]) getIntent().getExtras().get("data");
        ListView listView = (ListView)findViewById(R.id.list_view_artist);

        listAdapter = new ArrayAdapter<String>(this,R.layout.list_item, data);

        listView.setAdapter(listAdapter);
    }
}
