// In Class Assignment 12
// Group22_InClass12
// Stephanie Lee Karp & Ken Stanley

package edu.uncc.inclass12;

public class Grade {

    long gradeId;
    String courseNumber;
    String courseName, courseGrade;
    double creditHours;

    public Grade(){}

    public Grade(long gradeId, String courseNumber, String courseName, String courseGrade, double creditHours) {
        this.gradeId = gradeId;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseGrade = courseGrade;
        this.creditHours = creditHours;
    }

    public Grade(String courseNumber, String courseName, String courseGrade, double creditHours) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseGrade = courseGrade;
        this.creditHours = creditHours;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public double getCreditHours() {
        return creditHours;
    }

    public long getGradeId() {
        return gradeId;
    }

    public Grade setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
        return this;
    }

    public Grade setCourseName(String courseName) {
        this.courseName = courseName;
        return this;

    }

    public Grade setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
        return this;
    }

    public Grade setCreditHours(double creditHours) {
        this.creditHours = creditHours;
        return this;
    }

    public Grade setGradeId(long gradeId) {
        this.gradeId = gradeId;
        return this;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", courseNumber=" + courseNumber +
                ", courseName='" + courseName + '\'' +
                ", courseGrade='" + courseGrade + '\'' +
                ", creditHours=" + creditHours +
                '}';
    }
}
