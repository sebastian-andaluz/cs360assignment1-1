package cs360Project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class FileCrawler {
		
	File file = new File("SchoolList.txt");
	FileReader reader;
	int i = 0;
	int j = 0;
	String[] array1;
	Scanner skan;
	Scanner scan;
	
	public int length(){
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNext()){
			scan.nextLine();
			j++;
		}
		return j;
		
		
	}
	
		
		
	
	public String[] getArray(int x) {
		array1 = new String[x];
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
	
	
	
	
	
	
	
