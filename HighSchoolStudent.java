import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class HighSchoolStudent extends Student{	//A high schooler is a student, therefore the extend
	private String nameOfSchool;

	public String getSchoolName(){
		return nameOfSchool;
	}														//getter and setter methods for private variables
	public void setSchoolName(String schoolName){
		nameOfSchool = schoolName;
	}

	public void writeToFile(){		//Creates a file containing the name, id school name and errors
		try{																//I take in input from a file in the Grader class and create a student 
			PrintWriter output = new PrintWriter(getId() + "_graded.txt");	//I then use the getters here to access those paramaters from the object
			output.println("High School Student " + getName());
			output.println("Student ID: " + getId());
			output.println("School Name " + getSchoolName());
			output.print(getPrintableErrorList());
			output.close();
		}
		catch(FileNotFoundException ex){		//exception in case file is not found
		}
		catch(Exception ex){					//Just in case there is an unexpected exception
			
		}
		 
	}

	HighSchoolStudent(String name, String id, String essay,		//The High School student constructor
			ArrayList<String> errorList, String nameOfSchool){
		super(name, id, essay, errorList);		//These are variables that are common among all types of students 
		this.nameOfSchool = nameOfSchool;		//and I therefore have to reference the Student parent class to use them
		//name of school is unique to high school students
	}

}
