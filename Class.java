package schoolsystem;

import java.util.*;

public class Class {
	private int courseID;
	private String classType;
	private String classCode;
	private int classSize;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	
	public Class (int courseID, String classType, String classCode, int classSize) {
		this.courseID = courseID;
		this.classType = classType;
		this.classCode = classCode;
		this.classSize = classSize; 
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
	
	public int getClassSize () {
		return classSize;
	}
	
	public void setClassSize (int classSize) {
		this.classSize = classSize;
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

	public Student getStudentObject (int index) {
		return studentList.get(index);
	}

	public int getFreeSlots () {
		int usedSlots = studentList.size();
		return classSize - usedSlots;
	}
	
}
