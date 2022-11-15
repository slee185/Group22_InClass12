// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class GradesDAO {

    private SQLiteDatabase db;

    public GradesDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Grade grade) {
        ContentValues values = new ContentValues();

        values.put(GradesTable.COURSE_NUMBER, grade.getCourseNumber());
        values.put(GradesTable.COURSE_NAME, grade.getCourseName());
        values.put(GradesTable.COURSE_GRADE, grade.getCourseGrade());
        values.put(GradesTable.CREDIT_HOURS, grade.getCreditHours());

        return db.insert(GradesTable.GRADES_TABLE, null, values);
    }

    public boolean update(Grade grade) {
        ContentValues values = new ContentValues();

        values.put(GradesTable.COURSE_NUMBER, grade.getCourseNumber());
        values.put(GradesTable.COURSE_NAME, grade.getCourseName());
        values.put(GradesTable.COURSE_GRADE, grade.getCourseGrade());
        values.put(GradesTable.CREDIT_HOURS, grade.getCreditHours());

        return db.update(GradesTable.GRADES_TABLE, values, GradesTable.GRADE_ID + " = ?", new String[]{String.valueOf(grade.getGradeId())}) > 0;
    }

    public boolean delete(Grade grade) {
       return db.delete(GradesTable.GRADES_TABLE, GradesTable.GRADE_ID + " = ?", new String[]{String.valueOf(grade.getGradeId())}) > 0;
    }

    public Grade get(long id) {


        return null;
    }

    public ArrayList<Grade> getAll() {
        ArrayList<Grade> grades = new ArrayList<>();
        return grades;
    }
}
