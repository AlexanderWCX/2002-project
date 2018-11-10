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
	int freeSlot;
	int totalSlot; 
	String courseName;
	String courseType; 
	String courseProfName; 
	
	private ArrayList<Student> studentList = new ArrayList<Student>();
	public ArrayList<Assessment> assessmentList = new ArrayList<Assessment>();
	 
	
	
	public Course (int courseID, String courseName, String courseType, String courseProfName, int freeSlot, int totalSlots)
	{
		this.courseID = courseID; 
		this.courseName = courseName; 
		this.courseType = courseType; 
		this.courseProfName = courseProfName; 
		this.freeSlot = freeSlot;
		this.totalSlot = totalSlot; 
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
	public String getcourseProfName()
	{
		return courseProfName; 
	}
	
	public void setcourseProfName(String courseProfName)
	{
		this.courseProfName = courseProfName; 
	}
	
		 
	public int getFreeSlot()
	{
		return freeSlot; 
	}
	
	public void setFreeSlot(int freeSlot)
	{
		this.freeSlot = freeSlot; 
	}
	

	public int getTotalSlot()
	{
		return freeSlot; 
	}
	
	public void setTotalSlot(int totalSlot)
	{
		this.totalSlot = totalSlot; 
	}
	
	
	public int getStudentList()
	{
		return studentList.size(); 
	}
	
	public int getStudentID(int ID)
	{		
		return studentList.get(ID).getStudentID(); 
	}
	
	public void getStudentCourses(Student student)
	{
		student.getStudentID(); 
	}
	
	
	public void addAssessment (int weightage, String name, boolean coursework)
	{

		Assessment assessment = new Assessment(weightage, name, coursework); 
	}
	
	
}


