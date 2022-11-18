// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    Context mContext;
    SQLiteDatabase db;
    DatabaseHelper dbOpenHelper;
    GradesDAO gradesDAO;

    public DatabaseManager(Context context) {
        this.mContext = context;
        dbOpenHelper = new DatabaseHelper(mContext);
        db = dbOpenHelper.getWritableDatabase();
        gradesDAO = new GradesDAO(db);
    }

    public GradesDAO getGradesDAO() {
        return gradesDAO;
    }
}
