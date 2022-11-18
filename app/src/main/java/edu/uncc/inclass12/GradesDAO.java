// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import android.content.ContentValues;
import android.database.Cursor;
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

    public boolean delete(long id) {
        return db.delete(GradesTable.GRADES_TABLE, GradesTable.GRADE_ID + " = ?", new String[]{String.valueOf(id)}) > 0;
    }

    public Grade get(long id) {
        Grade grade = null;

        Cursor cursor = db.query(GradesTable.GRADES_TABLE,
                new String[]{GradesTable.GRADE_ID, GradesTable.COURSE_NUMBER, GradesTable.COURSE_NAME, GradesTable.COURSE_GRADE, GradesTable.CREDIT_HOURS}, GradesTable.GRADE_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            grade = buildGradeFromCursor(cursor);
        }

        return grade;
    }

    public ArrayList<Grade> getAll() {
        ArrayList<Grade> grades = new ArrayList<>();

        Cursor cursor = db.query(GradesTable.GRADES_TABLE,
                new String[]{GradesTable.GRADE_ID, GradesTable.COURSE_NUMBER, GradesTable.COURSE_NAME, GradesTable.COURSE_GRADE, GradesTable.CREDIT_HOURS},
                null, null, null, null, null);

        // cursor.moveToFirst();

        while (cursor.moveToNext()) {
            Grade grade = buildGradeFromCursor(cursor);
            grades.add(grade);
        }

        return grades;
    }

    private Grade buildGradeFromCursor(Cursor cursor) {
        Grade grade = new Grade();
        grade.setGradeId(cursor.getLong(0));
        grade.setCourseNumber(cursor.getString(1));
        grade.setCourseName(cursor.getString(2));
        grade.setCourseGrade(cursor.getString(3));
        grade.setCreditHours(cursor.getDouble(4));

        return grade;
    }
}
