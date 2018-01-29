import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grader extends Dictionary{
	private boolean available = true;	//This tells the grader if it is busy or not
	private Student student;			//This is so that I can create a student based on the first line of the paper and then specifyify what type of student
	private Dictionary dict;			//This assigns a dictionary instance and is necessary to use the isWord method

	public Grader(Dictionary dict){		//This instantiantes the local dict instance
		this.dict = dict;	
	}

	public boolean gradeStudent(String fileName){		//Takes in a file name that is passed in
		try{
			Scanner input = new Scanner(new File(fileName));	//Take input from a file
			String typeOfStudent = input.nextLine();			//This tells what kind of student it is
			if(typeOfStudent.equalsIgnoreCase("Graduate Student")){
				String name = input.nextLine();					//The order comes from the template on canvas and is different for each type of student
				String idNumber = input.nextLine();
				String major = input.nextLine();
				String advisor = input.nextLine();

				ArrayList<String> errorList = new ArrayList<String>();		//This is where I will store the misspelled words
				ArrayList<String> essay1 = new ArrayList<String>();		//Stores the rest of the essay in an array list
				while(input.hasNext()){									//because its size doesn't have to be declared at the beginning, unlike an array of strings
					String word = input.next();
					essay1.add(word);				//This is where I add words to the essay
				}

				for(int i = 0; i < essay1.size(); i++){		//gets rid of punctuation
					essay1.set(i, essay1.get(i).replaceAll("[\\[\\](){},.;!?<>%]", ""));
				}

				for(int i = 0; i < essay1.size(); i++){		//Checks for errors and puts them in an array list
					if(dict.isWord(essay1.get(i)) == false){	//It is necessary to refer to the dict so that the isWord method is properly implemented
						errorList.add(essay1.get(i));
					}
				}	

				//Creates a graduate student object based on the inputs from the File
				student = new GraduateStudent(name, idNumber, essay1.toString(), errorList, major, advisor);
				student.writeToFile();		//Calls the writeToFile method
				input.close();		//I have to close the input inside the if statement because thats where the scope of the scanner is limited to
			}

			else if(typeOfStudent.equalsIgnoreCase("Undergraduate Student")){	//I repeat the process seperately
				String name = input.nextLine();									//for Undergraduate and 
				String idNumber = input.nextLine();								//high school because 
				String major = input.nextLine();								//they have different variables

				ArrayList<String> essay1 = new ArrayList<String>();	//the rest of the file goes into essay
				while(input.hasNext()){
					String word = input.next();			//Adds words to the essay array list
					essay1.add(word);
				}
				for(int i = 0; i < essay1.size(); i++){	//Gets rid of punctuation
					essay1.set(i, essay1.get(i).replaceAll("[\\[\\](){},.;!?<>%]", ""));
				}
				ArrayList<String> errorList = new ArrayList<String>();
				for(int i = 0; i < essay1.size(); i++){		//Checks for errors and puts them in 
					if(dict.isWord(essay1.get(i)) == false){		//an array list
						errorList.add(essay1.get(i));
					}
				}		//Creates a new  undergraduate student 
				student = new UndergraduateStudent(name, idNumber, essay1.toString(), errorList, major);
				student.writeToFile();
				input.close();
			}
			else if(typeOfStudent.equalsIgnoreCase("HighSchool Student")){	//This is the first line of the paper
				String name = input.nextLine();
				String idNumber = input.nextLine();
				String nameOfSchool = input.nextLine();

				ArrayList<String> essay1 = new ArrayList<String>();		//Creates the array list where the essay will be stored
				while(input.hasNext()){
					String word = input.next();				//adds words to the array list
					essay1.add(word);
				}
				for(int i = 0; i < essay1.size(); i++){		//Removes punctuation
					essay1.set(i, essay1.get(i).replaceAll("[\\[\\](){},.;!?<>%]", ""));
				}
				ArrayList<String> errorList = new ArrayList<String>();
				for(int i = 0; i < essay1.size(); i++){		//Stores errors in array list
					if(dict.isWord(essay1.get(i)) == false){
						errorList.add(essay1.get(i));
					}
				}		//creates a high school student
				student = new HighSchoolStudent(name, idNumber, essay1.toString(), errorList, nameOfSchool);
				student.writeToFile();
				input.close();

			}
			else{		//This sort of handles the exception in case the type of student is misspelled
				input.close();
				return false;
			}

			available = false;	//The grader is not available to grade anything else until the reset() method is called
			return true;		//If the program makes it this far, it is obviously graded the essay

		}

		catch(FileNotFoundException ex){	//Catches the exception if the file is not found
			reset();		//resets because the grader is available since nothing happened
			return false;	//returns false because nothing was graded
		}
		catch(Exception ex){	//Catches all other exceptions
			reset();			//resets because the grader is available since nothing happened
			return false;		//returns false because nothing was graded
		}
	}

	public boolean isAvailable(){			//Checks if the grader is available:
		//The grader is available and the student object is empty
		if(student == null && available == true){
			return true;
		}
		else{
			return false;
		}
	}

	public Student getStudent(){		//getter for the Student class		
		return student;
	}

	public void reset(){		//Reset all local variables except dict
		student = null;
		available = true;
	}
}
