package com.example.btvn_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnEmployee:{
                Intent intent1 = new Intent(MainActivity.this, EmployeeActivity.class);
                startActivity(intent1);
                return true;
            }
            case R.id.mnDepartment:{
                Intent intent2 = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(intent2);
                return true;
            }
            case R.id.mnExit:{
                finish();
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

}