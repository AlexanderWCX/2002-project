package schoolsystem;

import java.util.*;

public class Score {
	private int courseID;
	private String assessmentName;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private ArrayList<Integer> marksList	= new ArrayList<Integer>();//marks out of 100
	
	
	
	
	
	public Score (int courseID, String assessmentName) {
		this.courseID = courseID;
		this.assessmentName = assessmentName;
	}

	public int getCourseID() {
		return courseID;
	}
	
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	
	public String getAssessmentName() {
		return assessmentName;
	}
	
	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
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
	
	public Student getStudent (int index) {
		return studentList.get(index);
	}
	
	public ArrayList<Integer> getMarksList() {
		return marksList;
	}
	
	public ArrayList<Student> getStudentList(){
		return studentList;
	}
	
	
	
	
}
