package com.andeli.tk3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddDataActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTextName, editTextDescription;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        myDb = new DatabaseHelper(this);
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String description = editTextDescription.getText().toString();
            boolean isInserted = myDb.insertData(name, description);
            if (isInserted) {
                Toast.makeText(AddDataActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddDataActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
