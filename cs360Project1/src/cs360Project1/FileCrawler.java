package cs360Project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class FileCrawler {
		
	
	FileReader reader;
	
	
	
	
	
	
	public static int length(){
		int j = 0;
		Scanner scan = null;
		String curLine = "";
		File file = new File("SchoolList.txt");
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNextLine()){
			curLine = scan.nextLine();
		//	System.out.println(curLine);
			j++;
		}
		return j;
		
		
	}
	
		
		
	
	public static String[] getSchoolArray(int x) {
		int i = 0;
		String[] array1;
		File file = new File("SchoolList.txt");
		array1 = new String[x];
		Scanner skan = null;
		try {
			skan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(skan.hasNext()){
			for(i = 0; i < x; i++){
				array1[i] = skan.nextLine();
			
				
		}
			
		}
		skan.close();
		return array1;
		
		
		
	}

	

	
   
    
 }
	
	
	
	
	
	
	

