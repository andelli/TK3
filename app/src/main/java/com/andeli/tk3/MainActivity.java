package com.andeli.tk3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        btnAddData = findViewById(R.id.btnAddData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load and display data in the RecyclerView
        loadData();

        // Navigate to com.andeli.tk3.AddDataActivity when the button is clicked
        btnAddData.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
            startActivity(intent);
        });
    }

    // Load data from the database and populate the RecyclerView
    private void loadData() {
        Cursor cursor = myDb.getAllData();
        List<DataModel> dataList = new ArrayList<>();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                dataList.add(new DataModel(
                        cursor.getString(0), // ID
                        cursor.getString(1), // Name
                        cursor.getString(2)  // Description
                ));
            }
        }

        dataAdapter = new DataAdapter(dataList, id -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        });
        recyclerView.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload data when returning to this activity to refresh the list
        loadData();
    }
}
