package schoolsystem;

import java.util.*;

public class Professor {
	
	
	
	private int staffID;
	private String professorName;
	private ArrayList<Course> courseList = new ArrayList<Course>();	
	
	public Professor(int staffID, String professorName) {
		this.staffID = staffID;
		this.professorName = professorName;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}
	
	public int getCourseListSize () {
		return courseList.size();
	}
	
	public void addCourse(Course course) {
		courseList.add(course);
	}
	
	public int getCourseID(int index) {
		return courseList.get(index).getCourseID();
	}
}

