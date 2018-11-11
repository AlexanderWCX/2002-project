package schoolsystem;

import java.util.*;

public class Score {
	private Course course;
	//marks out of 100
	// repeat studentID and marks
	private Assessment assessment;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private ArrayList<Integer> marksList	= new ArrayList<Integer>();
	
	
	
	
	
	public Score (Course course, Assessment assessment) {
		this.course = course;
		this.assessment = assessment;
	}

	public int getCourseID() {
		return course.getCourseID();
	}
	
	public void setCourseID(int courseID) {
		this.course.setCourseID(courseID);
	}
	
	public String getAssessmentName() {
		return assessment.assessmentName;
	}
	
	public void setAssessmentName(String assessmentName) {
		this.assessment.assessmentName = assessmentName;
	}
	
	public int getMarks(int i) {
		return marksList.get(i);
	}
	
	public void addMarks(int marks) {
		marksList.add(marks);
	}
	
	public void printStudentsMarks() {
		for (int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i).getStudentID());
			System.out.println(marksList.get(i));
		}
	}
	
	public void addStudent(Student student) {
		studentList.add(student);
	}
	
	public int getMarksListSize() {
		return marksList.size();
	}
	
	public int getStudentListSize () {
		return studentList.size();
	}
	
	public int getStudentID (int index) {
		return studentList.get(index).getStudentID();
	}
	
	
}
