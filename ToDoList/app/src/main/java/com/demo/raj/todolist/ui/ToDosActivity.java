package com.demo.raj.todolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.raj.todolist.R;

/**
 * Created by raj on 2/14/2016.
 */
public class ToDosActivity extends AppCompatActivity implements View.OnClickListener{

    // log tag for debugging
    private static final String LOG_TAG = ToDosActivity.class.getSimpleName();

    ListView mListView;

    FloatingActionButton mFab;

    TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        mListView = (ListView) findViewById(R.id.todo_list);
        mEmptyView = (TextView) findViewById(R.id.empty_view);
        mFab = (FloatingActionButton) findViewById(R.id.item_add_button);

        mFab.setOnClickListener(this);
    }

    // handle add new to-do button
    @Override
    public void onClick(View v) {

//        Toast.makeText(this, "New To-Do", Toast.LENGTH_LONG).show();

        startActivityForResult(new Intent(this, LocationPickerActivity.class), LocationPickerActivity.PLACE_PICKER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LocationPickerActivity.PLACE_PICKER_REQUEST && resultCode == RESULT_OK){

            if(data != null) {

                String location = data.getStringExtra(LocationPickerActivity.EXTRAS_LOCATION);
                Log.v(LOG_TAG, "Location from LocationPickerActivity: " + location);
            }else{

                Toast.makeText(this, "User didn't select any location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

