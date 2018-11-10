package schoolsystem;

import java.util.ArrayList;

public class Assessment {
	public int courseID;
	public int weightage;
	public String assessmentName;
	public int coursework;
	private ArrayList<Score> scoreList = new ArrayList<Score>();
	
	
	public Assessment (int courseID, String assessmentName, int weightage, int coursework) {
		this.courseID = courseID;
		this.weightage = weightage;
		this.assessmentName = assessmentName;
		this.coursework = coursework;
	}
	

	
	public void addScoretoAssessment (int studentID, int score) {
		ArrayList studentList = new ArrayList(); // to store list of courses
		studentList = StudentDB.readStudents("/Users/trifenacaroline/Downloads/student.txt");
		int targetindex;
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
	}
}
