
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class UndergraduateStudent extends Student{	//an undergraduate is a student, therefore it extends

	private String major;

	public String getMajor(){	
		return major;
	}											//getters and setters for private variables
	public void setMajor(String major){
		this.major = major;
	}

	public void writeToFile(){		//Writes the grading report
		try{																	//I take in input from a file in the Grader class and create a student 
			PrintWriter output = new PrintWriter(getId() + "_graded.txt");		//I then use the getters here to access those paramaters from the object
			output.println("Undergraduate Student " + getName());		//Uses the getters because
			output.println("Student ID: " + getId());					//they are all private data fields
			output.println("Major: "  + getMajor());
			output.print(getPrintableErrorList());
			output.close();
		}
		catch(FileNotFoundException ex){		//If the file is not found
		}
		catch(Exception ex){					//all other exceptions

		}
	}

	UndergraduateStudent(String name, String id, String essay, 	//undergraduate student constructor
			ArrayList<String> errorList, String major){
		super(name, id, essay, errorList);		//These are variables that are common among all types of students 
		this.major = major;						//and I therefore have to reference the Student parent class to use them
		//major is unique to undergraduate students
	}
}
