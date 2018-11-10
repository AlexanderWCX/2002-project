package schoolsystem;

import java.util.*;

public class Class {

	private String classType;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private int courseID;
	private String classCode;
	
	public Class (int courseID, String classType, String classCode) {
		this.courseID = courseID;
		this.classType = classType;
		this.classCode = classCode;
	}
	
	public int getCourseID() {
		return courseID;
	}
	
	public void setCourseID (int courseID) {
		this.courseID = courseID;
	}
	
	public String getClassCode () {
		return classCode;
	}
	
	public void setClassCode (String classCode) {
		this.classCode = classCode;
	}
	
	public String getClassType () {
		return classType;
	}
	
	public void setClassType (String classType) {
		this.classType = classType;
	}
	
	public void printStudents() {
		for (int i = 0; i < studentList.size(); i++) {
			System.out.print(studentList.get(i).getStudentID());
			System.out.print(" | ");
			System.out.println(studentList.get(i).getStudentName());
		}
	}
	
	public void addStudent(Student student) {
		studentList.add(student);
	}
	
	public int getStudentListSize () {
		return studentList.size();
	}
	
	public int getStudentID (int index) {
		return studentList.get(index).getStudentID();
	}
	
}
