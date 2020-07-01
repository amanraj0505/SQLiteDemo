package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etCell = (EditText)findViewById(R.id.etCell);
    }

    public void btnSubmit(View v)
    {
        String name = etName.getText().toString().trim();
        String cell = etCell.getText().toString().trim();

        try {

            ContactsDB db = new ContactsDB(this);
            db.open();
            db.createEntry(name,cell);
            db.close();
            Toast.makeText(this, "successfully saved.", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etCell.setText("");
        }
        catch (SQLException e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public  void btnShowData(View v)
    {
        startActivity(new Intent(this,Data.class));
    }

    public void btnEditData(View v)
    {
        try {

            ContactsDB db = new ContactsDB(this);
            db.open();
            db.updateEntry("1", "Aman Raj", "9952908249");
            db.close();
            Toast.makeText(this, "updated successfully.", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDeleteData(View v)
    {
        try {

            ContactsDB db = new ContactsDB(this);
            db.open();
            db.deleteEntry("1");
            db.close();
            Toast.makeText(this, "deleted successfully.", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
