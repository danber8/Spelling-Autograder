import java.io.PrintWriter;
import java.util.ArrayList;

public class GraduateStudent extends Student{

	private String major;		//declares private data fields
	private String advisor;

	public String getMajor(){		//getters and setter for the private data fields
		return major;
	}
	public void setMajor(String major){		
		this.major = major;					
	}

	public String getAdvisor(){
		return advisor;
	}
	public void setAdvisor(String advisor){
		this.advisor = advisor;
	}

	public void writeToFile(){
		try{																//I take in input from a file in the Grader class and create a student 
			PrintWriter output = new PrintWriter(getId() + "_graded.txt");	//I then use the getters here to access those paramaters from the object
			output.println("Graduate Student " + getName());				
			output.println("Student ID: " + getId());
			output.println("Major: " + getMajor());
			output.println("Advisor " + getAdvisor());
			output.print(getPrintableErrorList());
			output.close();
		}
		catch(Exception ex){		//Catches exceptions and doesn't do anything with them because theres no declared protocol
		}
	}

	GraduateStudent(String name, String id, String essay, 		//The constructor for that object is right here
			ArrayList<String> errorList, String major, String advisor){
		super(name, id, essay, errorList);			//These are variables that are common among all types of students 
		this.major = major;							//and I therefore have to reference the Student parent class to use them
		this.advisor = advisor;		//major and advisor are unique to graduate students
	}
}