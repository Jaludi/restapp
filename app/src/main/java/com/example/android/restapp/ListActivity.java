package com.example.android.restapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.restapp.restPack.ContactClass;
import com.example.android.restapp.restPack.Result;
import com.example.android.restapp.restPack.ResultAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    ArrayList<ContactClass> contacts;
    ListView listView;
    ResultAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.list_results);
//        contacts = savedInstanceState.getParcelable("rList");
        contacts = getIntent().getExtras().getParcelableArrayList("rList");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter= new ResultAdapter(contacts, this);
        listView.setAdapter(adapter);
    }
}
