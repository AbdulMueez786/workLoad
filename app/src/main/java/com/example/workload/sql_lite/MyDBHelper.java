package com.example.workload.sql_lite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.workload.Model.employee;
import com.example.workload.Model.task;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    String CREATE_CONTACTS_TABLE_QUERY="CREATE TABLE "+
            MyContactContract.Employee.TABLENAME + "("+
            MyContactContract.Employee._id+ " TEXT PRIMARY KEY, "+
            MyContactContract.Employee._email+ " TEXT NOT NULL, "+
            MyContactContract.Employee._F_Name+ " TEXT, "+
            MyContactContract.Employee._L_Name+ " TEXT, "+
            MyContactContract.Employee._username+ " TEXT, "+
            MyContactContract.Employee._designation+ " TEXT, "+
            MyContactContract.Employee._profile+ " TEXT, "+
            MyContactContract.Employee._joining_date+ " TEXT, "+
            MyContactContract.Employee._total_task_assign+ " TEXT, "+
            MyContactContract.Employee._task_complite+ " TEXT, "+
            MyContactContract.Employee._p_id+ " TEXT);";
    String CREATE_TASK_TABLE_QUERY="CREATE TABLE "+
            MyContactContract.Task.TABLENAME + "("+
            MyContactContract.Task._id+ " TEXT PRIMARY KEY, "+
            MyContactContract.Task._task_title+ " TEXT NOT NULL, "+
            MyContactContract.Task._description+ " TEXT, "+
            MyContactContract.Task._dueDate+ " TEXT, "+
            MyContactContract.Task._emp_id+ " TEXT, "+
            MyContactContract.Task._status+ " TEXT, "+
            MyContactContract.Task._rating+ " TEXT, "+
            MyContactContract.Task._image+ " TEXT);";
    String DELETE_Task_TABLE_QUERY="DROP TABLE IF EXISTS "+ MyContactContract.Task.TABLENAME;

    String DELETE_CONTACTS_TABLE_QUERY="DROP TABLE IF EXISTS "+ MyContactContract.Employee.TABLENAME;

    String insertSQL = "INSERT INTO "+ MyContactContract.Employee.TABLENAME+" \n" +
            "("+ MyContactContract.Employee._id+","+ MyContactContract.Employee._email+","+
            MyContactContract.Employee._F_Name+","+MyContactContract.Employee._L_Name+","+
            MyContactContract.Employee._username +","+MyContactContract.Employee._designation+","
            +MyContactContract.Employee._profile+","+ MyContactContract.Employee._joining_date +","
            +MyContactContract.Employee._total_task_assign +","+MyContactContract.Employee._task_complite+","
            +MyContactContract.Employee._p_id +")\n" + "VALUES \n" + "(?, ?, ?,?,?,?,?,?,?,?,?);";
    String insertTaskSQL = "INSERT INTO "+ MyContactContract.Task.TABLENAME+" \n" +
            "("+ MyContactContract.Task._id+","+ MyContactContract.Task._task_title+","+
            MyContactContract.Task._description+","+MyContactContract.Task._dueDate+","+
            MyContactContract.Task._emp_id+","+MyContactContract.Task._status+","
            +MyContactContract.Task._rating+"," +MyContactContract.Task._image +")\n" +
            "VALUES \n" + "(?,?,?,?,?,?,?,?);";

    public MyDBHelper(@Nullable Context context) {
        super(context, MyContactContract.DB_NAME, null, MyContactContract.DB_VERSION);//it will check database of this name
                                                                                             // and check its version if version
                                                                                            // mismatches then it will call onUpgrade to
                                                                                            //to update the version of db
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE_QUERY);
        sqLiteDatabase.execSQL(CREATE_TASK_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DELETE_CONTACTS_TABLE_QUERY);//delete previous table
        sqLiteDatabase.execSQL(DELETE_Task_TABLE_QUERY);//delete previous table
        onCreate(sqLiteDatabase);//create new table
        //sqLiteDatabase.setVersion(newVersion);
    }
/*
    private void ondrop(){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(DELETE_CONTACTS_TABLE_QUERY);//delete previous table
        database.execSQL(DELETE_Task_TABLE_QUERY);//delete previous table
        System.out.println("Hellokkfdkgkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
    }*/
    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
                //sqLiteDatabase.setVersion(oldVersion);
    }

    //Raw SQL
    public void insert_employee(String id, String email, String f_name, String l_name, String username, String designation, String profile
            , String joining_date, String total_task_assign, String task_complite, String p_id){
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(insertSQL,new String[]{id,email,f_name,l_name,username,designation,profile,joining_date,total_task_assign,task_complite,p_id});
        //Cursor c=database.rawQuery(insertSQL,new String[]{name,phno,email});
        database.close();
    }
    //Raw SQL
    public void insert_task(String id, String task_title, String description, String dueDate, String emp_id, String status
            , String rating, String image){
        System.out.println(id);
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL(insertTaskSQL,new String[]{id,task_title,description,dueDate,emp_id,status,rating,image});
        database.close();
        System.out.println(this.readAlltasks());
    }
    public List<task> readAlltasks(){
        List<task> ls=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=db.rawQuery("Select * from "+MyContactContract.Task.TABLENAME,null);
        while (c.moveToNext()){
            ls.add(
                    new task(
                            c.getString(c.getColumnIndex(MyContactContract.Task._id)),
                            c.getString(c.getColumnIndex(MyContactContract.Task._task_title)),
                            c.getString(c.getColumnIndex(MyContactContract.Task._description)),
                            c.getString(c.getColumnIndex(MyContactContract.Task._dueDate)),
                            c.getString(c.getColumnIndex(MyContactContract.Task._emp_id)),
                            c.getString(c.getColumnIndex(MyContactContract.Task._status)),
                            c.getString(c.getColumnIndex(MyContactContract.Task._rating)),
                            c.getString(c.getColumnIndex(MyContactContract.Task._image))
                    )
            );
        }
        c.close();
        return ls;
    }


    public List<employee> readAllData(){
        List<employee> ls=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=db.rawQuery("Select * from "+MyContactContract.Employee.TABLENAME,null);
        while (c.moveToNext()){
            ls.add(
                    new employee(
                            c.getString(c.getColumnIndex(MyContactContract.Employee._id)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._email)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._F_Name)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._L_Name)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._username)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._designation)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._profile)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._joining_date)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._total_task_assign)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._task_complite)),
                            c.getString(c.getColumnIndex(MyContactContract.Employee._p_id))
                    )
            );
        }
        c.close();
      return ls;
    }

/*
    List<Contact> read(String name){
        List<Contact> ls=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+MyContactContract.Contact.TABLENAME+" WHERE name = ? ", new String[] {name});
        while (c.moveToNext()){
            ls.add(
                    new Contact(
                            //c.getInt()
                            c.getString(c.getColumnIndex(MyContactContract.User._ID)),
                            c.getString(c.getColumnIndex(MyContactContract.User._NAME)),
                            c.getString(c.getColumnIndex(MyContactContract.User._PHNO)),
                            c.getString(c.getColumnIndex(MyContactContract.User._EMAIL))
                    )
            );
        }
        c.close();
        return ls;
    }
*/
    public boolean update_employee(String id, String f_name, String l_name, String username, String designation, String profile) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+ MyContactContract.Employee.TABLENAME+" SET F_Name = "+"'"+f_name+"',L_Name ="+"'"+l_name+"',profile ="+"'"+profile
                +"',username ="+"'"+username+"',designation ="+"'"+designation+"'" +" WHERE id = "+"'"+id+"'");
        //db.execSQL("UPDATE "+MyContactContract.Contact.TABLENAME+" SET name = "+"'"+s+"' "+ "WHERE name = "+"'"+s1+"'");
        db.close();
        return true;
    }
    public boolean update_task(String new_id, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE "+MyContactContract.Task.TABLENAME+" SET id = "+"'"+new_id+"' "+ "WHERE id = "+"'"+id+"'");
        db.close();
        return true;
    }



    public boolean delete(String s1) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ MyContactContract.Task.TABLENAME+" where id=?",new String[] {s1});
        db.close();
        return true;
    }

    public boolean delete1(String s1) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ MyContactContract.Employee.TABLENAME+" where id=?",new String[] {s1});
        db.close();
        return true;
    }
}
