// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements GradesFragment.GradesListener, AddCourseFragment.AddCourseListener {

    DatabaseManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DatabaseManager(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new GradesFragment())
                .commit();
    }

    @Override
    public void goAddCourse() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddCourseFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void createGrade(String course_number, String course_name, String course_grade, Double course_hours) {
        Grade grade = new Grade(course_number, course_name, course_grade, course_hours);
        dm.getGradesDAO().save(grade);
        goGrades();
    }

    @Override
    public void goGrades() {
        getSupportFragmentManager().popBackStack();
    }
}