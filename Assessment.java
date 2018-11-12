package schoolsystem;

import java.util.ArrayList;

public class Assessment {
	public Course course;
	public int weightage;
	public String assessmentName;
	public int coursework;
	private ArrayList<Score> scoreList = new ArrayList<Score>();
	
	
	public Assessment (Course course, String assessmentName, int weightage, int coursework) {
		this.course = course;
		this.weightage = weightage;
		this.assessmentName = assessmentName;
		this.coursework = coursework;
	}
	

	
	public void addScoretoAssessment (int studentID, int score) {
		
		try {
		ArrayList studentList = new ArrayList(); // to store list of courses
		studentList = StudentDB.readStudents("/Users/trifenacaroline/Downloads/student.txt");
		
		
		int targetindex = 0;
		for (int i = 0; i<studentList.size(); i++) {
			Student studenttocheckID = (Student)studentList.get(i);
			if (studentID == studenttocheckID.getStudentID()) {
				targetindex = i;
				break;
			}
		}
		
		Student targetStudent = (Student)studentList.get(targetindex);
		
		Score newScoreToAdd = new Score(targetStudent, score);
		scoreList.add(newScoreToAdd);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

	public int getCourseID () {
		return course.getCourseID();
	}
	
	
	public static void addAssessment () {
		int courseID;
		String assessmentName;
		int targetCourseIndex = 999;
		boolean courseExist = false;
		Course targetCourse;
		boolean madeBefore = true;
		int courseworkInput;
		int weightage;
		
		System.out.println("Enter the courseID of course to add assessment to: ");
		courseID = sc.nextInt();
		sc.nextLine();
		
		try {
		ArrayList courseList = new ArrayList(); // to store list of courses
		courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");
		/*
		while (courseExist == false ) {
			for (int j = 0; j < courseList.size(); j++) {
				Course courseToTest = (Course)courseList.get(j);
				int courseToTestID = courseToTest.getCourseID();
				if (courseToTestID == courseID) {
					targetCourseIndex = j;
					courseExist = true;
				}
				else {
					courseExist = false;
				}
			}
			
			if(courseExist == false) {
			System.out.println("Course does not exist!");
			System.out.println("Enter the courseID of course to add assessment to: ");
			courseID = sc.nextInt();
			}
		}
		*/
		for (int j = 0; j < courseList.size(); j++) {
			Course courseToTest = (Course)courseList.get(j);
			int courseToTestID = courseToTest.getCourseID();
			if (courseToTestID == courseID) {
				targetCourseIndex = j;
				courseExist = true;
			}
			else {
				courseExist = false;
			}
		}
		
		targetCourse = (Course)courseList.get(targetCourseIndex);
		
		
		System.out.println("Enter the name of the assessment: ");
		
		assessmentName = sc.nextLine();
		
		ArrayList assessmentList = new ArrayList(); // to store list of courses
		assessmentList = AssessmentDB.readAssessments("/Users/trifenacaroline/Downloads/Assessment.txt");
		
		
		/*while (madeBefore == true) {
		for (int i=0; i< targetCourse.assessmentList.size(); i++) {
			Assessment assessmentToTest = targetCourse.assessmentList.get(i);
			if (assessmentName == assessmentToTest.assessmentName) {
						madeBefore = true;
			}
			else {
				madeBefore = false;
			}
		}
		
		if(madeBefore == true) {
			String assessmentinvalid;
			assessmentinvalid = String.format("Assessment with the same assessment name has been made before for CourseID %d ", courseID);
			System.out.println(assessmentinvalid);
			System.out.println("Enter the name of the assessment: ");
			
			assessmentName = sc.next();
		}
		
		}
		*/
		
		for (int i=0; i< targetCourse.assessmentList.size(); i++) {
			Assessment assessmentToTest = targetCourse.assessmentList.get(i);
			if (assessmentName == assessmentToTest.assessmentName) {
						madeBefore = true;
			}
			else {
				madeBefore = false;
			}
		}
		
		System.out.println("Is this assessment a Coursework sub-component? ");
		System.out.println("Enter 1 for Yes");
		System.out.println("Enter 0 for No");
		courseworkInput = sc.nextInt();
		
		System.out.println("Enter the weightage of the assessment(out of 100): ");
		weightage = sc.nextInt();
		
		Assessment case6newAssessment = new Assessment(targetCourse, assessmentName, weightage, courseworkInput);
		assessmentList.add(case6newAssessment);
		AssessmentDB.saveAssessments("/Users/trifenacaroline/Downloads/Assessment.txt", assessmentList);
		
		System.out.println("Assessment succesfully added!");
		
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
}

}
