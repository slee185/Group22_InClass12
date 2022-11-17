// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.uncc.inclass12.databinding.FragmentGradesBinding;

public class GradesFragment extends Fragment implements GradesRecyclerAdapter.iGrades {
    DatabaseManager dm;
    Double hours = 0.0;
    Double points = 0.0;
    FragmentGradesBinding binding;
    GradesRecyclerAdapter adapter;
    LinearLayoutManager layoutManager;
    List<Grade> grades = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_bar_add) {
            mListener.goAddCourse();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dm = new DatabaseManager(requireActivity());

        binding.gradesRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(requireActivity());
        binding.gradesRecyclerView.setLayoutManager(layoutManager);

        adapter = new GradesRecyclerAdapter(requireActivity(), grades, this);
        binding.gradesRecyclerView.setAdapter(adapter);

        requireActivity().setTitle(R.string.grades_label);
    }

    @Override
    public void onResume() {
        super.onResume();

        updateGrades();
    }

    GradesListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (GradesListener) context;
    }

    private void setGpa(Double gpa) {
        TextView textView = binding.textViewGPA;
        textView.setText(getString(R.string.grades_gpa_label, gpa));
    }

    private void setHours(Double hours) {
        TextView textView = binding.textViewHours;
        textView.setText(getString(R.string.grades_hours_label, hours));
    }

    private void updateGrades() {
        grades = dm.getGradesDAO().getAll();

        hours = 0.0;
        points = 0.0;

        for (Grade grade: grades) {
            hours += grade.getCreditHours();
            points += pointsForGrade(grade.getCourseGrade()) * grade.getCreditHours();
        }

        setGpa(points / hours);
        setHours(hours);

        adapter.notifyDataSetChanged();
    }

    private double pointsForGrade(String grade) {
        switch (grade.toUpperCase(Locale.ROOT)) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }

    @Override
    public void trashButtonClicked(Grade grade) {
        dm.getGradesDAO().delete(grade);
    }

    interface GradesListener {
        void goAddCourse();
    }
}