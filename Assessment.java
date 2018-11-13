package schoolsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Assessment {
	private int courseID;
	private String assessmentName;
	private int weightage;
	private int coursework;
	private Score score;
	
	
	public Assessment (int courseID, String assessmentName, int weightage, int coursework) {
		this.courseID = courseID;
		this.weightage = weightage;
		this.assessmentName = assessmentName;
		this.coursework = coursework;
	}
	
	static Scanner sc = new Scanner(System.in);
	
	
	public int getCourseID() {
		return courseID;
	}
	
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	
	public int getWeightage() {
		return weightage;
	}
	
	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}
	
	public String getAssessmentName() {
		return assessmentName;
	}
	
	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}
	
	public int getCoursework() {
		return coursework;
	}
	
	public void setCoursework(int coursework) {
		this.coursework = coursework;
	}
	
	public void addScoretoAssessment (Score score) {
		this.score = score;
	}
	
	
	
	
}
