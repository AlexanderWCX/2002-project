package schoolsystem;


import java.io.IOException;
import java.util.*;
import schoolsystem.Course;

public class Student {
	private int studentID;
	private String studentName;
	private ArrayList<Course> courseList = new ArrayList<Course>();
	
	static Scanner sc = new Scanner(System.in);
	
	public Student (int studentID, String studentName) {
		this.studentID = studentID;
		this.studentName = studentName;
	}
	
	public static void addStudent () {
		boolean studentIDExists = true;
		
    	String studentFile = "/Users/trifenacaroline/Downloads/student.txt";
    	try {
			
			ArrayList studentList = StudentDB.readStudents(studentFile) ;
			
			System.out.println("Enter New Student ID:");
			int studentID = sc.nextInt();
			
			while(studentIDExists == true) {
			for (int i = 0 ; i < studentList.size() ; i++) {
				Student studentToCheck = (Student)studentList.get(i);
				if (studentID == studentToCheck.getStudentID())
					studentIDExists = true;
				else 
					studentIDExists = false;
			}
			
			//prompt if studentID does not exist
			if(studentIDExists == true) {
				System.out.println("Student ID already exists!");
				System.out.println("Enter New Student ID:");
				studentID = sc.nextInt();
				
			}
			}
			
			System.out.println("Enter New Student Name:");
			String studName = sc.next();
			Student student = new Student(studentID, studName);
			
			//add student obj into list of all students
			studentList.add(student);
			//write student record(s) to file
			StudentDB.saveStudents(studentFile, studentList);
			
			for (int i = 0 ; i < studentList.size() ; i++) {
					Student studenttoprint = (Student)studentList.get(i);
					System.out.println("Student ID: " + studenttoprint.getStudentID());
					System.out.println("Student Name: " + studenttoprint.getStudentName() );
					System.out.println("---------------------------------");
					
			}
			
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
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
