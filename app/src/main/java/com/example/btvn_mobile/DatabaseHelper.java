package com.example.btvn_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "mydb.sqlite", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table Departments("+
                "id_department integer primary key," +
                "department_name text," +
                "phone_number text)");
        sqLiteDatabase.execSQL("Create Table Employees(" +
                "id_employee integer primary key," +
                "name text," +
                "gender text," +
                "address text," +
                "phone text," +
                "image blob," +
                "id_department integer not null constraint id_department references " +
                "Departments(id_department) ON DELETE CASCADE ON UPDATE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists Employees");
        sqLiteDatabase.execSQL("Drop table if exists Departments");
    }

    public int insertEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_employee", employee.getId_employee());
        contentValues.put("name", employee.getName());
        contentValues.put("gender", employee.getGender());
        contentValues.put("address", employee.getAddress());
        contentValues.put("phone", employee.getPhone());
        contentValues.put("image", employee.getImage());
        contentValues.put("id_department", employee.getId_department());
        int result = (int) db.insert("Employees", null, contentValues);
        db.close();
        return result;
    }

    public int updateEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_employee", employee.getId_employee());
        contentValues.put("name", employee.getName());
        contentValues.put("gender", employee.getGender());
        contentValues.put("address", employee.getAddress());
        contentValues.put("phone", employee.getPhone());
        contentValues.put("image", employee.getImage());
        contentValues.put("id_department", employee.getId_department());
        String whereClause = "id_employee=?";
        String whereArgs[] = {employee.getId_employee()+""};
        int result = db.update("Employees", contentValues, whereClause, whereArgs);
        db.close();
        return result;
    }

    public int deleteEmployee(int id){
        SQLiteDatabase db = this.getWritableDatabase()  ;
        String whereClause = "id_employee = ?";
        String whereArgs[] = {id+""};
        int result = db.delete("Employees", whereClause, whereArgs);
        db.close();
        return result;
    }

    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Employees", null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            list.add(new Employee(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getBlob(5),
                    cursor.getInt(6)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public int insertDepartment(Department department){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_department", department.getId_department());
        contentValues.put("department_name", department.getDepartmentName());
        contentValues.put("phone_number", department.getPhoneNumber());
        int result = (int) db.insert("Departments", null, contentValues);
        db.close();
        return result;
    }

    public int updateDepartment(Department department){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_department", department.getId_department());
        contentValues.put("department_name", department.getDepartmentName());
        contentValues.put("phone_number", department.getPhoneNumber());
        String whereClause = "id_department=?";
        String whereArgs[] = {department.getId_department()+""};
        int result = db.update("Departments", contentValues, whereClause, whereArgs);
        db.close();
        return result;
    }

    public int deleteDepartment(int id_department){
        SQLiteDatabase db = this.getWritableDatabase()  ;
        String whereClause = "id_department = ?";
        String whereArgs[] = {id_department+""};
        int result = db.delete("Departments", whereClause, whereArgs);
        db.close();
        return result;
    }

    public ArrayList<Department> getAllDepartment(){
        ArrayList<Department> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Departments", null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            list.add(new Department(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }


}
