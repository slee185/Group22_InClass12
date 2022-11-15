// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GradesRecyclerAdapter extends RecyclerView.Adapter<GradesRecyclerAdapter.GradeViewHolder> {

    ArrayList<Grade> grades;

    public GradesRecyclerAdapter(ArrayList<Grade> data) {
        this.grades = data;
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_row_item, parent, false);
        return new GradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        Grade grade = grades.get(position);
        holder.textViewCourseNumber.setText(grade.courseNumber);
        holder.textViewCourseName.setText(grade.courseName);
        holder.textViewCourseHours.setText((int) grade.creditHours);
        holder.textViewCourseLetterGrade.setText(grade.courseGrade);
    }

    @Override
    public int getItemCount() {
        return this.grades.size();
    }

    public static class GradeViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCourseNumber;
        TextView textViewCourseName;
        TextView textViewCourseHours;
        TextView textViewCourseLetterGrade;
        View rootView;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            textViewCourseNumber = itemView.findViewById(R.id.textViewCourseNumber);
            textViewCourseName = itemView.findViewById(R.id.textViewCourseName);
            textViewCourseHours = itemView.findViewById(R.id.textViewCourseHours);
            textViewCourseLetterGrade = itemView.findViewById(R.id.textViewCourseLetterGrade);
        }
    }
}
