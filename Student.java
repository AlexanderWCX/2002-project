package schoolsystem;


import java.util.*;
import schoolsystem.Course;

public class Student {
	private int studentID;
	private String studentName;
	private ArrayList<Course> courseList = new ArrayList<Course>();
	
	
	public Student (int studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
	}
	
	public int getStudentID() {
		return studentID;
	}
	
	public void setStudentID (int studentID) {
		this.studentID = studentID;
	}
	
	public String getStudentName () {
		return studentName;
	}
	
	public void setStudentName (String studentName) {
		this.studentName = studentName;
	}
	
	public void printCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			System.out.println(courseList.get(i).getCourseID());
		}
	}
	
	public void addCourse(Course course) {
		courseList.add(course);
	}
	
	public int getCourseListSize () {
		return courseList.size();
	}
	
	public int getCourseID (int index) {
		return courseList.get(index).getCourseID();
	}
	
}
