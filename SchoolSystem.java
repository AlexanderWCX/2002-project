public void addAssessment () {
			int courseID;
			String assessmentName;
			int targetCourseIndex = 0;
			boolean courseExist = false;
			Course targetCourse;
			boolean falsebefore=false;
			boolean madeBefore = true;
			int courseworkInput;
			int weightage;
			
			System.out.println("Enter the courseID of the course: ");
			courseID = sc.nextInt();
			sc.nextLine();
			
			try {
			ArrayList courseList = new ArrayList(); // to store list of courses
			courseList = CourseDB.readCourses("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Course.txt");
			
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
				falsebefore=true;
				System.out.println("Course does not exist!");
				System.out.println("Enter the courseID of course to add assessment to: ");
				courseID = sc.nextInt();
				}
				
			}
			
			/*
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
				System.out.println("Course does not exist");
			}
			
			*/
			targetCourse = (Course)courseList.get(targetCourseIndex);
			System.out.println(targetCourse.getAssessmentName(0));
			if(falsebefore) {
			String empty =sc.nextLine();
			}
			System.out.println("Enter the name of the assessment: ");
			
			assessmentName = sc.nextLine();
			
			ArrayList assessmentList = new ArrayList(); // to store list of courses
			assessmentList = AssessmentDB.readAssessments("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Assessment.txt");
			
			
			while (madeBefore) {
				
				
				for (int i=0; i< targetCourse.getAssessmentListSize(); i++) {
				;
				Assessment assessmentToTest = targetCourse.getAssessment(i);
				if (assessmentName == assessmentToTest.getAssessmentName()) {
					
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
				
				assessmentName = sc.nextLine();
			}
			
			}
			
			/*
			for (int i=0; i< targetCourse.getAssessmentListSize(); i++) {
				Assessment assessmentToTest = targetCourse.getAssessment(i);
				if (assessmentName == assessmentToTest.getAssessmentName()) {
							madeBefore = true;
				}
				else {
					madeBefore = false;
				}
			}
			*/
			System.out.println("Is this assessment a Coursework sub-component? ");
			System.out.println("Enter 1 for Yes");
			System.out.println("Enter 0 for No");
			courseworkInput = sc.nextInt();
			
			System.out.println("Enter the weightage of the assessment(out of 100): ");
			weightage = sc.nextInt();
			
			Assessment case6newAssessment = new Assessment(courseID, assessmentName, weightage, courseworkInput);
			assessmentList.add(case6newAssessment);
			AssessmentDB.saveAssessments("C:\\Users\\xanwo\\eclipse-workspace\\java2002project\\src\\schoolsystem\\Assessment.txt", assessmentList);
			
			System.out.println("Assessment succesfully added!");
			
			} catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		