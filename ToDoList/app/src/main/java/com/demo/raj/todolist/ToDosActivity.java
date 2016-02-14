package com.demo.raj.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by raj on 2/14/2016.
 */
public class ToDosActivity extends AppCompatActivity {

    // log tag for debugging
    private static final String LOG_TAG = ToDosActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        FragmentManager mgr = getSupportFragmentManager();

        mgr.beginTransaction()
            .add(R.id.container, new ToDosFragment())
            .commit();
    }
}

class ToDosFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = null;

        ListView mListView;

        FloatingActionButton mFab;

        TextView mEmptyView;

        rootView = inflater.inflate(R.layout.fragment_todos, container, false);

        mListView = (ListView)rootView.findViewById(R.id.todo_list);
        mEmptyView = (TextView)rootView.findViewById(R.id.empty_view);
        mFab = (FloatingActionButton)rootView.findViewById(R.id.item_add_button);

        mFab.setOnClickListener(this);

        return rootView;
    }

    // handle add new to-do button
    @Override
    public void onClick(View v) {

        Toast.makeText(getActivity(), "New To-Do", Toast.LENGTH_LONG).show();
    }
}