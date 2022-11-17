// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uncc.inclass12.databinding.FragmentAddCourseBinding;

public class AddCourseFragment extends Fragment {

    FragmentAddCourseBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCancel.setOnClickListener(v -> mListener.goGrades());

        binding.buttonSubmit.setOnClickListener(v -> {
            String courseNumber = binding.editTextCourseNumber.getText().toString();
            String courseName = binding.editTextCourseName.getText().toString();
            String courseHours = binding.editTextCourseHours.getText().toString();

            int selectedId = binding.radioGroupGrades.getCheckedRadioButtonId();

            if(courseName.isEmpty() || courseNumber.isEmpty() || courseHours.isEmpty()) {
               Toast.makeText(getContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
               return;
            }

            double hours = Double.parseDouble(courseHours);

            if(hours <= 0) {
                Toast.makeText(getContext(), "Please enter the number of hours", Toast.LENGTH_SHORT).show();
                return;
            }

            if(selectedId == -1){
                Toast.makeText(getContext(), "Please select a letter grade !!", Toast.LENGTH_SHORT).show();
                return;
            }

            String courseLetterGrade;
            if(selectedId == R.id.radioButtonA) {
                courseLetterGrade = "A";
            } else if(selectedId == R.id.radioButtonB) {
                courseLetterGrade = "B";
            } else if(selectedId == R.id.radioButtonC) {
                courseLetterGrade = "C";
            } else if(selectedId == R.id.radioButtonD) {
                courseLetterGrade = "D";
            } else {
                courseLetterGrade = "F";
            }

            mListener.createGrade(courseNumber, courseName, courseLetterGrade, hours);
        });

        requireActivity().setTitle(R.string.add_label);
    }

    AddCourseListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddCourseListener) context;
    }

    interface AddCourseListener {
        void createGrade(String course_number, String course_name, String course_grade, Double course_hours);
        void goGrades();
    }
}