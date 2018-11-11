package schoolsystem;

import java.util.ArrayList; 
import java.io.*; 
import java.util.Scanner;
import java.util.List; 
import java.util.StringTokenizer; 
import schoolsystem.Student;
import schoolsystem.Assessment;

public class Course {
	
	int courseID; 
	int courseFreeSlot;
	int courseTotalSlot; 
	String courseName;
	String courseType; 
	String courseProfName; 
	
	
	private ArrayList<Student> studentList = new ArrayList<Student>();
	public ArrayList<Assessment> assessmentList = new ArrayList<Assessment>();
	 
	
	
	public Course (int courseID, String courseName, String courseType, String courseProfName, int courseFreeSlot, int courseTotalSlot)
	{
		this.courseID = courseID; 
		this.courseName = courseName; 
		this.courseType = courseType; 
		this.courseProfName = courseProfName; 
		this.courseFreeSlot = courseFreeSlot;
		this.courseFreeSlot = courseTotalSlot;
		
	}
	
	//For Course ID 
	public int getCourseID()
	{
		return courseID; 
	}
	
	public void setCourseID(int courseID)
	{
		this.courseID = courseID; 
	}
		
	
	//For Course Name 
	public String getCourseName()
	{
		return courseName; 
	}
			
	public void setCourseName(String courseName)
	{
		this.courseName = courseName; 
	}
	
	
	//For Course Type 
	public String getCourseType()
	{
		return courseType; 
	}
	
	public void setCourseType(String courseType)
	{
		this.courseType = courseType; 
	}
	
	
	//For Course Coordinator 
	public String getCourseProfName()
	{
		return courseProfName; 
	}
	
	public void setCourseProfName(String courseProfName)
	{
		this.courseProfName = courseProfName; 
	}
	
		 
	public int getCourseFreeSlot()
	{
		return courseFreeSlot; 
	}
	
	public void setCourseFreeSlot(int courseFreeSlot)
	{
		this.courseFreeSlot = courseFreeSlot; 
	}
	

	public int getCourseTotalSlot()
	{
		return courseTotalSlot; 
	}
	
	public void setCourseTotalSlot(int courseTotalSlot)
	{
		this.courseTotalSlot = courseTotalSlot; 
	}
	
	
	public int getStudentList()
	{
		return studentList.size(); 
	}
	
	public int getStudentID(int index)
	{		
		return studentList.get(index).getStudentID(); 
	}
	
	public void addStudents(Student student)
	{
		studentList.add(student); 
	}
	
	
}


