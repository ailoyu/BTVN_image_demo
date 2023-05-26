package com.example.btvn_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;

public class EmployeeActivity extends AppCompatActivity {

    private static final int CAMERA_PIC_REQUEST = 1337;

    ArrayList<Department> listDepartment;
    ImageView imageview;
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    Button bt_changeAvatar, bt_select, bt_save, bt_delete, bt_update;
    EditText et_employeeId, et_employeeName, et_phoneNumber, et_address;
    RadioButton rd_male, rad_female;
    Bitmap image;
    DatabaseHelper dbhelper;
    ListView gv_diplay;
    ArrayList<Employee> list_employee;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        et_employeeId = (EditText) findViewById(R.id.et_employeeID);
        et_employeeName = (EditText) findViewById(R.id.et_employeeName);
        et_phoneNumber = (EditText) findViewById(R.id.et_employeePhoneNumber);
        et_address = (EditText) findViewById(R.id.et_address);
        rd_male = (RadioButton) findViewById(R.id.radioButton_male);
        rad_female = (RadioButton) findViewById(R.id.radioButton_female);
        dbhelper = new DatabaseHelper(this);


        Spinner sp_department = (Spinner)findViewById(R.id.spinner_department);
        listDepartment = dbHelper.getAllDepartment();
        ArrayList<String> listItem = new ArrayList<>();
        for (Department department: listDepartment) {
            listItem.add(department.getId_department() + "  " + department.getDepartmentName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listItem);
        sp_department.setAdapter(adapter);

        bt_save = (Button) findViewById(R.id.bt_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = new Employee();
                employee.setId_employee(Integer.parseInt(et_employeeId.getText().toString()));
                employee.setName(et_employeeName.getText().toString());
                employee.setAddress(et_address.getText().toString());
                if(rd_male.isChecked()){
                    employee.setGender("male");
                } else if(rad_female.isChecked()) {
                    employee.setGender("female");
                }
                employee.setPhone(et_phoneNumber.getText().toString());

                // convert image from bitmap to byte[]
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                employee.setImage(byteArray);

                String s = sp_department.getSelectedItem().toString();
                String arr[] = s.split(" ");
                // get first word
                String firstWord = arr[0];
                employee.setId_department(Integer.parseInt(firstWord));
                if(dbhelper.insertEmployee(employee) > 0){
                    Toast.makeText(getBaseContext(), "Save successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Save Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        bt_update = (Button) findViewById(R.id.bt_update);
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = new Employee();
                employee.setId_employee(Integer.parseInt(et_employeeId.getText().toString()));
                employee.setName(et_employeeName.getText().toString());
                employee.setAddress(et_address.getText().toString());
                if(rd_male.isChecked()){
                    employee.setGender("male");
                } else if(rad_female.isChecked()) {
                    employee.setGender("female");
                }
                employee.setPhone(et_phoneNumber.getText().toString());

                String s = sp_department.getSelectedItem().toString();
                String arr[] = s.split(" ");
                // get first word
                String firstWord = arr[0];
                employee.setId_department(Integer.parseInt(firstWord));

                // convert image from bitmap to byte[]
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageview = (ImageView) findViewById(R.id.avatarView);
                Bitmap bm=((BitmapDrawable)imageview.getDrawable()).getBitmap();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                employee.setImage(byteArray);


                if(dbhelper.updateEmployee(employee) > 0){
                    Toast.makeText(getBaseContext(), "Update successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Update Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = new Employee();
                employee.setId_employee(Integer.parseInt(et_employeeId.getText().toString()));
                if(dbhelper.deleteEmployee(employee.getId_employee()) > 0){
                    Toast.makeText(getBaseContext(), "Delete successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "Delete Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        bt_changeAvatar = (Button) findViewById(R.id.bt_changeAvatar);
        bt_changeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });

        bt_select = (Button)findViewById(R.id.bt_select);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_employee = dbhelper.getAllEmployee();

                ArrayList<String> list_String = new ArrayList<>();
                for(Employee employee : list_employee){
                    list_String.add(employee.getName()+"");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(EmployeeActivity.this,
                        android.R.layout.simple_list_item_1, list_String);
                gv_diplay.setAdapter(adapter);
            }
        });




        gv_diplay = (ListView) findViewById(R.id.list_employee);
        gv_diplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee employee =  list_employee.get(i);
                et_employeeId.setText(String.valueOf(employee.getId_employee()));
                et_employeeName.setText(employee.getName());
                et_phoneNumber.setText(employee.getPhone());
                et_address.setText(employee.getAddress());
                if(employee.getGender().equals("female")){
                    rad_female.setChecked(true);
                }else{
                    rd_male.setChecked(true);
                }
                imageview = (ImageView) findViewById(R.id.avatarView);
                imageview.getLayoutParams().height = 500;
                imageview.getLayoutParams().width = 500;
                imageview.setImageBitmap(BitmapFactory.decodeByteArray(employee.getImage(), 0 , employee.getImage().length));
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST) {
            image = (Bitmap) data.getExtras().get("data");
            imageview = (ImageView) findViewById(R.id.avatarView); //sets imageview as the bitmap

            imageview.getLayoutParams().height = 500;
            imageview.getLayoutParams().width = 500;
            imageview.setImageBitmap(image);
        }
    }
}