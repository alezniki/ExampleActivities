package com.nikola.exampleactivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void btnTest(View view){
        Toast toast = Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT);
        toast.show();
    }

}
