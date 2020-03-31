package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Human.Student;
import Utils.Util;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_DEPARMENT + " TEXT, "
                + Util.KEY_FAMILY + " TEXT, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_AVERAGE_CHECK + " REAL " + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_DEPARMENT, student.getDepartment());
        contentValues.put(Util.KEY_FAMILY, student.getFamily());
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_AVERAGE_CHECK, student.getAverageCheck());
        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Student getStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_DEPARMENT, Util.KEY_FAMILY, Util.KEY_NAME, Util.KEY_AVERAGE_CHECK},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Student student = new Student(
                Integer.parseInt(cursor.getString(0)),//id
                cursor.getString(1),//dep
                cursor.getString(2),//fam
                cursor.getString(3),//name
                Double.parseDouble(cursor.getString(4))//av_check
        );
        return student;
    }

    public List<Student> getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Student> students = new ArrayList<>();
        String selectAllStudents = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStudents, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        Integer.parseInt(cursor.getString(0)),//id
                        cursor.getString(1),//dep
                        cursor.getString(2),//fam
                        cursor.getString(3),//name
                        Double.parseDouble(cursor.getString(4))//av_check
                );
                students.add(student);
            } while (cursor.moveToNext());
        }
        return students;
    }
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_DEPARMENT, student.getDepartment());
        contentValues.put(Util.KEY_FAMILY, student.getFamily());
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_AVERAGE_CHECK, student.getAverageCheck());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?", new String[] {String.valueOf(student.getId())});
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[] {String.valueOf(student.getId())});
    }
    public int getStudentsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}
