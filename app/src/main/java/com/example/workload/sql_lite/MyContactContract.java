package com.example.workload.sql_lite;

import android.provider.BaseColumns;

import java.net.PortUnreachableException;

public class MyContactContract {

    public static String DB_NAME="cheerforpeer.db";

    public static int DB_VERSION=1;

    private MyContactContract(){
    }

    public static class Employee implements BaseColumns
    {
        public static String TABLENAME="user1";//a variable of table Contact by which we access the table contact

        public static String _id="id";

        public static String _designation="designation";

        public static String _email="email";

        public static String _F_Name="F_Name";

        public static String _L_Name="L_Name";

        public static String _joining_date="joining_date";

        public static String _p_id="p_id";

        public static String _profile="profile";

        public static String _task_complite="task_complited";

        public static String _total_task_assign="total_task_assign";

        public static String _username="username";

    }

    public static class Task implements BaseColumns
    {
        public static String TABLENAME="task1";//a variable of table Contact by which we access the table contact

        public static String _id="id";

        public static String _task_title="task_title";

        public static String _description="description";

        public static String _dueDate="dueDate";

        public static String _emp_id="emp_id";

        public static String _status="status";

        public static String _rating="rating";

        public static String _image="image";

    }


}
