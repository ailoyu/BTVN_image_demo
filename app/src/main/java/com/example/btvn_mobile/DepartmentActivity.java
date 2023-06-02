package com.example.btvn_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class DepartmentActivity extends AppCompatActivity {

    EditText et_departmentId, et_departmentName, et_phoneNumber;
    Button bt_add, bt_search, bt_update, bt_delete, bt_exit;
    GridView gv_diplay;
    DatabaseHelper dbhelper;
    ArrayList<Department> list_department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        et_departmentId = (EditText) findViewById(R.id.et_departmentID);
        et_departmentName = (EditText) findViewById(R.id.et_departmentName);
        et_phoneNumber = (EditText) findViewById(R.id.et_phoneNumber);
        gv_diplay =(GridView)findViewById(R.id.gridview_display);
        dbhelper = new DatabaseHelper(this);

        bt_add = (Button)findViewById(R.id.btAdd);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Department department = new Department();
                department.setId_department(Integer.parseInt(et_departmentId.getText().toString()));
                department.setDepartmentName(et_departmentName.getText().toString());
                department.setPhoneNumber(et_phoneNumber.getText().toString());
                if(dbhelper.insertDepartment(department) > 0){
                    Toast.makeText(getBaseContext(), "Save successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Save Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_update = (Button)findViewById(R.id.btUpDate);
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Department department = new Department();
                department.setId_department(Integer.parseInt(et_departmentId.getText().toString()));
                department.setDepartmentName(et_departmentName.getText().toString());
                department.setPhoneNumber(et_phoneNumber.getText().toString());
                if(dbhelper.updateDepartment(department) > 0){
                    Toast.makeText(getBaseContext(), "Update successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Update Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_delete = (Button) findViewById(R.id.btDelete);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbhelper.deleteDepartment(Integer.parseInt(et_departmentId.getText().toString())) > 0){
                    Toast.makeText(getBaseContext(), "Delete successfully!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getBaseContext(), "Update Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_search = (Button)findViewById(R.id.btSearch);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_department = dbhelper.getAllDepartment();

                ArrayList<String> list_String = new ArrayList<>();
                for(Department department : list_department){
                    list_String.add(department.getId_department()+"");
                    list_String.add(department.getDepartmentName());
                    list_String.add(department.getPhoneNumber());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DepartmentActivity.this,
                        android.R.layout.simple_list_item_1, list_String);
                gv_diplay.setAdapter(adapter);
            }
        });

        gv_diplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Department department =  list_department.get(i / 3);
                et_departmentId.setText(department.getId_department()+"");
                et_departmentName.setText(department.getDepartmentName());
                et_phoneNumber.setText(department.getPhoneNumber());
            }
        });


        bt_exit = (Button) findViewById(R.id.exit_bt);
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



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
                Intent intent1 = new Intent(DepartmentActivity.this, EmployeeActivity.class);
                startActivity(intent1);
                return true;
            }
            case R.id.mnDepartment:{
                Intent intent2 = new Intent(DepartmentActivity.this, DepartmentActivity.class);
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