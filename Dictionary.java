import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Dictionary {


	private ArrayList<String> dictionary = new ArrayList<String>();	//Creates the array list where the dictionary will be stored

	public int getVocabularySize(){		//Returns the number of words in the dictionary: 80 thousand something
		return dictionary.size();
	}

	public boolean loadDictionaryFromFile(String filePath){		//The filePath is passed in by the user and is dictionary
		File dictionaryArrayList = new File(filePath);		//Creates a file
		try{
			Scanner input = new Scanner(dictionaryArrayList);
			while(input.hasNext()){			//While there is another word in the dictionary
				String word = input.next();		//Takes in the next word
				dictionary.add(word.toLowerCase());		//Writes to file, lowercase to make it easier to compare to the essay
			}
			input.close();		//Close the scanner to avoid a resouce leak
			return true;		//The contents of the dictionary were successfully added to the array list
		}
		catch(FileNotFoundException ex){		//If the file is not found, the contents fo the dictionary obviously didn't get transfered to the array list
			return false;
		}
		catch(Exception ex1){					//All other exceptions
			return false;
		}
	}

	public boolean isWord(String word){
		if(dictionary.contains(word.toLowerCase())){		//If the word is in the dictionary, return true
			return true;
		}		
		else												//Otherwise return false
		{
			return false;
		}
	}
	
	public ArrayList<String> getDictionary() {		//Returns the dictionary
		return dictionary;
	}
	
	public void setDictionary(ArrayList<String> dictionary) {	//sets the dictionary
		this.dictionary = dictionary;
	}
}