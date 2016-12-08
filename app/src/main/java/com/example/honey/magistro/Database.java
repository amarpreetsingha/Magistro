package com.example.honey.magistro;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by honey on 6/12/16.
 */

public class Database extends Activity {

    public void createTables(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists  student(s_id integer PRIMARY KEY AUTOINCREMENT, name varchar(30), address varchar(30), phone_no numeric(10), dob date);");
        sqLiteDatabase.execSQL("create table if not exists  staff(st_id integer PRIMARY KEY AUTOINCREMENT, name varchar(30), address varchar(30), qualification varchar(30));");
        sqLiteDatabase.execSQL("create table if not exists institute(i_id integer PRIMARY KEY AUTOINCREMENT, i_name varchar(30), st_id int, foreign key(st_id) references staff(st_id));");
        sqLiteDatabase.execSQL("create table if not exists course(c_id integer PRIMARY KEY AUTOINCREMENT, c_name varchar(30), i_id int, foreign key(i_id) references institute (i_id));");

        sqLiteDatabase.execSQL("create table if not exists batches(b_name varchar(30), timing varchar(20), capacity numeric(2), duration varchar(20), c_id int, primary key(b_name, c_id)," +
                "foreign key (c_id) references course(c_id));");

        sqLiteDatabase.execSQL("create table if not exists student_batch(s_id int, b_name varchar(30),c_id int, primary key(s_id,c_id,b_name), foreign key (s_id) references student(s_id)," +
                "foreign key (c_id) references course(c_id), foreign key (b_name) references batches(b_name));");

        sqLiteDatabase.execSQL("create table if not exists pay(s_id int, c_id int, p_date date, amount numeric(5), installment_choosen int, installment_remaining int,primary key(s_id,c_id)," +
                "foreign key (s_id) references student(s_id), foreign key (c_id) references course(c_id));");

        sqLiteDatabase.execSQL("create table if not exists staff_batch(st_id int, b_name int, c_id int, primary key(st_id,c_id,b_name), foreign key (st_id) references  staff(st_id)," +
                "foreign key (b_name) references batches(b_name),foreign key (c_id) references course(c_id));");

        sqLiteDatabase.execSQL("create table if not exists installment(i_number int,amount numeric(5),c_id int,primary key(i_number,c_id),foreign key (c_id) references course(c_id));");
    }


    public void addStaff(SQLiteDatabase sqLiteDatabase, String name, String address, String qual) {
        sqLiteDatabase.execSQL("insert into staff(name, address, qualification) values('"+name+"', '"+address+"', '"+qual+"')");
    }
    public String[][] getStaff(SQLiteDatabase sqLiteDatabase) {
        String staff[][];
        int i = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select st_id, name from staff", null);
        staff = new String[cursor.getCount()][2];
        cursor.moveToNext();
        do {
            staff[i][0] = cursor.getString(0);
            staff[i][1] = cursor.getString(1);
            i++;
        }while(cursor.moveToNext());

        return staff;
    }

    public String[][] getInstitute(SQLiteDatabase sqLiteDatabase) {
        String ins[][];
        int i = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select i_id, i_name from institute", null);
        ins = new String[cursor.getCount()][2];
        cursor.moveToNext();
        do {
            ins[i][0] = cursor.getString(0);
            ins[i][1] = cursor.getString(1);
            i++;
        }while(cursor.moveToNext());

        return ins;
    }

    public void addInstitute(SQLiteDatabase sqLiteDatabase, String name, int staff_id) {
            sqLiteDatabase.execSQL("insert into institute(i_name, st_id) values('"+name+"', "+staff_id+")");
    }

    public void addCourse(SQLiteDatabase sqLiteDatabase, String name, int i_id) {
        sqLiteDatabase.execSQL("insert into course(c_name, i_id) values('"+name+"', "+i_id+")");
    }

    public void addBatches(SQLiteDatabase sqLiteDatabase, String name, String timing, String capacity, String duration, int c_id) {
        sqLiteDatabase.execSQL("insert into batches(b_name, timing, capacity, duration, c_id) values('"+name+"', '"+timing+"', '"+capacity+"', '"+duration+"', "+c_id+")");
    }

    public String[][] getInstituteCourse(SQLiteDatabase sqLiteDatabase) {
        String ins[][];
        int i = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select institute.i_name, course.c_name, course.c_id, institute.i_id from course join institute on course.i_id = institute.i_id", null);
        ins = new String[cursor.getCount()][4];
        cursor.moveToNext();
        do {
            ins[i][0] = cursor.getString(0);
            ins[i][1] = cursor.getString(1);
            ins[i][2] = cursor.getString(2);
            ins[i][3] = cursor.getString(3);
            i++;
        }while(cursor.moveToNext());

        return ins;
    }

    public String[] getBatches(SQLiteDatabase sqLiteDatabase, int c_id) {
        String ins[];
        int i = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select b_name from batches join course where batches.c_id = course.c_id and course.c_id = "+c_id, null);
        ins = new String[cursor.getCount()];
        cursor.moveToNext();
        do {
            ins[i] = cursor.getString(0);
            i++;
        }while(cursor.moveToNext());


        return ins;
    }

    public String[][] getInstituteWiseCourse(SQLiteDatabase sqLiteDatabase, int i_id) {
        String ins[][];
        int i = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select c_id, c_name from course join institute on course.i_id = institute.i_id and institute.i_id = "+i_id, null);
        ins = new String[cursor.getCount()][2];
        cursor.moveToNext();
        do {
            ins[i][0] = cursor.getString(0);
            ins[i][1] = cursor.getString(1);
            i++;
        }while(cursor.moveToNext());

        return ins;
    }

    public void addStudent(SQLiteDatabase sqLiteDatabase, String name, String address, long phoneno, String dob, int s_id, String b_name, int c_id) {
        sqLiteDatabase.execSQL("insert into student(name, address, phone_no, dob) values('"+name+"', '"+address+"',"+phoneno+", '"+dob+"');");
        sqLiteDatabase.execSQL("insert into student_batch values("+s_id+", '"+b_name+"', "+c_id+")");
    }

    public int getLastRowId(SQLiteDatabase sqLiteDatabase, String table) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SEQ from sqlite_sequence WHERE name = '"+table+"'", null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public String[][] getStudentsBatchWise(SQLiteDatabase sqLiteDatabase, int c_id, String batch) {
        String st[][];
        int i = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select student.s_id, student.name from student join student_batch on student_batch.s_id = student.s_id and student_batch.b_name = '"+batch+"' and student_batch.c_id = "+c_id , null);
        st = new String[cursor.getCount()][2];
        cursor.moveToNext();
        do {
            st[i][0] = cursor.getString(0);
            st[i][1] = cursor.getString(1);
            i++;
        }while(cursor.moveToNext());

        return st;
    }

    public String[] getStudentInfo(SQLiteDatabase sqLiteDatabase, int s_id, int c_id, int i_id) {
        String st[] = new String[5];
        Cursor cursor = sqLiteDatabase.rawQuery("select distinct student.address, student.phone_no, student.dob, institute.i_name, staff.name from student as a join institute as b join batches as c join staff as d join staff_batch as e join student_batch as f  where e.st_id=d.st_id and a.s_id=f.s_id and e.c_id=f.c_id and c.b_name=e.b_name and s_id = "+s_id+" and c_id = "+c_id+" and i_id = "+i_id , null);
        cursor.moveToNext();
        do {
            st[0] = cursor.getString(0);
            st[1] = cursor.getString(1);
            st[2] = cursor.getString(2);
            st[3] = cursor.getString(3);
            st[4] = cursor.getString(4);
        }while(cursor.moveToNext());

        return st;
    }



}
