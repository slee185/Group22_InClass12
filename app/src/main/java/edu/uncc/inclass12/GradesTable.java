// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GradesTable {
    static final String GRADE_ID = "gradeId";
    static final String GRADES_TABLE = "grades";
    static final String COURSE_NUMBER = "courseNumber";
    static final String COURSE_NAME = "courseName";
    static final String COURSE_GRADE = "courseGrade";
    static final String CREDIT_HOURS = "creditHours";

    static public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + GradesTable.GRADES_TABLE + " (");
        sb.append(GRADE_ID + " integer primary key autoincrement, ");
        sb.append(COURSE_NUMBER + " text not null, ");
        sb.append(COURSE_NAME + " text not null, ");
        sb.append(COURSE_GRADE + " text not null, ");
        sb.append(CREDIT_HOURS + " text not null)");

        try {
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + GradesTable.GRADES_TABLE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
