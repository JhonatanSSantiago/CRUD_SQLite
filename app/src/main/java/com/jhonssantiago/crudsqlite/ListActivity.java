package com.jhonssantiago.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private BDsqlite bd;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Pessoa> myDataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        bd = new BDsqlite(this);

        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        recyclerView.setHasFixedSize(true);
        myDataset = bd.consultarDados();

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset, bd);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        bd.close();
        super.onDestroy();
    }
}