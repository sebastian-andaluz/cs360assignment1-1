package cs360Project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class FileCrawler {
		
	//creates the file reader and the file to be read
	FileReader reader;	
	File file;
	
	public FileCrawler(File file)
	{
		this.file = file;
	}
	
	public static int length(){
		int j = 0;
		Scanner scan = null;
		String curLine = "";
		//assigns file to the school file
		File file = new File("SchoolList-1.txt");
		try {
			//scans file to read
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNextLine()){
			//grabs the current line and increases amount of lines
			curLine = scan.nextLine();
			j++;
		}
		//returns how many schools there are in the file
		return j;
		
		
	}	
	
	public static String[] getSchoolArray(int x) {
		int i = 0;
		//creates array of strings which are the schools and their info
		String[] array1;
		//reads the file over
		File file = new File("SchoolList.txt");
		array1 = new String[x];
		//scanner starts to scan
		Scanner skan = null;
		try {
			skan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//adds the whole line of information to the array
		while(skan.hasNext()){
			for(i = 0; i < x; i++){
				array1[i] = skan.nextLine();
			
				
		}
			
		}
		skan.close();
		//returns the array
		return array1;
		
		
		
	}

	

	
   
    
 }
	
	
	
	
	
	
	

