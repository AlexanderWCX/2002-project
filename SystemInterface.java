package schoolsystem;


import schoolsystem.Course;
import schoolsystem.CourseDB;
import schoolsystem.Assessment;

import java.io.IOException;
import java.util.*;

public class SystemInterface {
	
	
	public static void main(String args[]) {
		SchoolSystem schoolsystem = new SchoolSystem();
		int choice = 0;
		
		System.out.println("Choose an option: ");
		System.out.println("1: Add a student");
		System.out.println("2: Add a course");
		System.out.println("3: Register a student into a course");
		System.out.println("4: Check slots for course or classes");
		System.out.println("5: Print the student list by lecture, tutorial or laboratory session for a course");
		System.out.println("6: Add an assessment for a course");
		System.out.println("7: Enter a student's marks");
		System.out.println("8: Print a course's performance");
		System.out.println("9: Print a student's transcript");
		
		Scanner sc = new Scanner(System.in);
		
		choice = sc.nextInt();
		
		switch(choice) {
		
		case 1: 
			schoolsystem.addStudent();
						
			break;
			
			
		case 2:
			schoolsystem.addCourse();
							
			break;
			
		case 3:
			
			schoolsystem.registerStudent();
			
			break;
			
		case 4:
			schoolsystem.checkClassAvailability();
			
			break;
			
			case 5:
			
			schoolsystem.printCourseStudentList();

			break; 
			
		case 6:
			
			schoolsystem.addAssessment();
			break;	
			
		case 7:
			
			schoolsystem.enterAssessmentMark();
			break;
		case 8:
			schoolsystem.printCourseStats();
			
			break;
		case 9:
			schoolsystem.printStudentTranscript();
			break;
		
		
		
		
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
