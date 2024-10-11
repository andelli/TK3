package com.andeli.tk3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTextName, editTextDescription;
    Button buttonUpdate, buttonDelete;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        myDb = new DatabaseHelper(this);
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        // Retrieve the ID of the item from the Intent
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");

        // Load the data for the given ID into the EditText fields
        loadData(id);

        // Update data in the database when "Update" button is clicked
        buttonUpdate.setOnClickListener(v -> updateData());

        // Delete data from the database when "Delete" button is clicked
        buttonDelete.setOnClickListener(v -> deleteData());
    }

    // Load data based on ID and set it in the EditTexts
    private void loadData(String id) {
        Cursor cursor = myDb.getAllData();
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(id)) {
                editTextName.setText(cursor.getString(1)); // Name
                editTextDescription.setText(cursor.getString(2)); // Description
                break;
            }
        }
    }

    // Update the existing data in the database
    private void updateData() {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();

        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isUpdated = myDb.updateData(id, name, description);
        if (isUpdated) {
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
            finish(); // Close this activity and go back to MainActivity
        } else {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete the selected data from the database
    private void deleteData() {
        Integer deletedRows = myDb.deleteData(id);
        if (deletedRows > 0) {
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
            finish(); // Close this activity and go back to MainActivity
        } else {
            Toast.makeText(this, "Deletion Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
