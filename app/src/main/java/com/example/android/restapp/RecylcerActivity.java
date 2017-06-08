package com.example.android.restapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.android.restapp.restPack.ContactClass;
import com.example.android.restapp.restPack.ResultRecyclerAdapter;

import java.util.ArrayList;

public class RecylcerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ContactClass> contacts;
    ResultRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylcer);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contacts = getIntent().getExtras().getParcelableArrayList("rList");
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter= new ResultRecyclerAdapter(contacts, this);
        recyclerView.setAdapter(adapter);
    }
}
