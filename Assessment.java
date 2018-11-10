package schoolsystem;

import java.util.ArrayList;

public class Assessment {
	public Course course;
	public int courseID;
	public int weightage;
	public String assessmentName;
	public int coursework;
	private ArrayList<Score> scoreList = new ArrayList<Score>();
	
	
	public Assessment (int courseID, String assessmentName, int weightage, int coursework) {
		boolean courseExist = false;
		this.courseID = courseID;
		try {
		ArrayList courseList = new ArrayList(); // to store list of courses
		courseList = CourseDB.readCourse("/Users/trifenacaroline/Downloads/Course.txt");
		for(int i=0;i<courseList.size();i++) {
			Course courseToCheck = (Course)courseList.get(i);
			if(courseToCheck.getCourseID()==courseID) {
				courseExist = true;
				this.course = courseToCheck;
				break;
			}
		}
		
		
		} catch (Exception e) {
            e.printStackTrace();
        }
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
}
