package cs360Project1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.maps.errors.ApiException;

public class Driver {
	static School[] schools;
	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		//MainGUI mainWindow = new MainGUI();
		int a = FileCrawler.length();
			
		//creates a string that will be assigned to what was read from a file
		String[] schoolInput;
		
		//uses that string to be what was in the file
		schoolInput = FileCrawler.getSchoolArray(a);
		
		//creates the schools after reading the files
		schools = SchoolBuilder.getSchools(schoolInput, a);
		
		//asks if they want a 5th semi or class sort
		System.out.println("Would you like Class Sort or a 5th Semi?");
		int choice = kb.nextInt();
		
		//if the first choice, class structure, if the second choice, fifth semi
		if(choice == 0)
		{
			sortClass();
		}
		else if(choice == 1)
		{
			fifthSemi();
		}
		
	}
	
	public static void sortClass()
	{
		//creates and calls the class sort and it's sort method
		ClassSort testSort = new ClassSort();
		testSort.classSort(schools);
	}
	
	public static void fifthSemi()
	{
		//counter for how many sectionals, regionals, and semis
		int secCounter = 0;
		int regCounter = 0;	
		int semCounter = 0;
		
		//arrays created for sectionals, regionals, and semis
		Sectional[] testSec = new Sectional[SchoolBuilder.hostSectionals.size()];
		Regional[] testReg = new Regional[SchoolBuilder.hostRegionals.size()];
		SemiState[] testSem = new SemiState[SchoolBuilder.hostSemis.size()+1];
		//adds 5th semi
		System.out.println("School to add:");
		kb.nextLine();
		String schoolToAdd = kb.nextLine();
		//finds it in the array of all schools and assigns it
		for(int i = 0; i < schools.length; i++)
		{
			if(SchoolBuilder.schools[i].schoolName.equalsIgnoreCase(schoolToAdd))
			{
				SchoolBuilder.hostSemis.add(new SemiState(schools[i]));
				schools[i].hostSemi = true;
			}
		}
		
		//finds and assigns all sectionals
		for(int i = 0; i < schools.length; i++)
		{
			if(schools[i].hostSectional == true)
			{
				testSec[secCounter] = new Sectional(schools[i]);
				secCounter++;
			}
			
			//finds and assigns all regionals
			if(schools[i].hostRegional == true)
			{
				testReg[regCounter] = new Regional(schools[i]);
				regCounter++;
			}
			
			//finds and assigns all semis
			if(schools[i].hostSemi == true)
			{
				testSem[semCounter] = new SemiState(schools[i]);
				semCounter++;
			}
		}
		
		//creates a sectional object for sorting sectionals
		SectionalSort sort = new SectionalSort();
		sort.sortSectionals(schools, testSec, testSec.length, false);
		
		//average and total for each sectional, regional, and semi
		double total = 0;
		double avg = 0;
		
		//finds average and prints the sectionals
		for(int sect = 0; sect < testSec.length; sect++)
		{
			total = 0;
			avg = 0;
			for(int i = 0; i < testSec[sect].sectional.size(); i++)
			{
				total += Mapping.getDistanceBetween(testSec[sect].sectional.get(i).getLat(), testSec[sect].sectional.get(i).getLng(),
						testSec[sect].getLat(), testSec[sect].getLng());
			}
			
			avg = total/testSec[sect].sectional.size();
			
			System.out.println("Sectional " + (sect+1) + " Average: " + avg);
			
			for(int j = 0; j < testSec[sect].sectional.size(); j++)
			{
				System.out.print(testSec[sect].sectional.get(j).schoolName);
				System.out.println(" distance: " + Mapping.getDistanceBetween(testSec[sect].sectional.get(j).getLat(), testSec[sect].sectional.get(j).getLng(),
						testSec[sect].getLat(), testSec[sect].getLng()));
			}
			System.out.println("\n");
		}
		
		System.out.println("\n\n");
		
		//resets total and average
		total = 0;
		avg = 0;
		
		//sorts the regionals
		sort.sortRegionals(testSec, testReg, testReg.length, false);
		
		//finds the total and average of regionals and prints it
		for(int reg = 0; reg < testReg.length; reg++)
		{
			total = 0;
			avg = 0;
			for(int i = 0; i < testReg[reg].regional.size(); i++)
			{
				total += Mapping.getDistanceBetween(testReg[reg].regional.get(i).getLat(), testReg[reg].regional.get(i).getLng(),
						testReg[reg].getLat(), testReg[reg].getLng());
			}
			
			avg = total/(testReg[reg].regional.size() - 1);
			
			System.out.println("Regional " + (reg+1) + " Average: " + avg);
			for(int j = 0; j < testReg[reg].regional.size(); j++)
			{
				System.out.print(testReg[reg].regional.get(j).schoolName);
				System.out.println(" distance: " + Mapping.getDistanceBetween(testReg[reg].regional.get(j).getLat(), testReg[reg].regional.get(j).getLng(),
						testReg[reg].getLat(), testReg[reg].getLng()));
			}
			System.out.println("\n");
		}
		
		System.out.println("\n\n");
		
		//resets the total and average
		total = 0;
		avg = 0;
	
		//calls the sort method for semis
		sort.sortSemis(testReg, testSem, testSem.length);
		
		//calculates average and total of semi and prints it out
		for(int sem = 0; sem < testSem.length; sem++)
		{
			total = 0;
			avg = 0;
			for(int i = 0; i < testSem[sem].semi.size(); i++)
			{
				total += Mapping.getDistanceBetween(testSem[sem].semi.get(i).getLat(), testSem[sem].semi.get(i).getLng(),
						testSem[sem].getLat(), testSem[sem].getLng());
			}
			
			avg = total/(testSem[sem].semi.size() - 1);
			
			System.out.println("Semi " + (sem+1) + " Average: " + avg);
			for(int j = 0; j < testSem[sem].semi.size(); j++)
			{
				System.out.print(testSem[sem].semi.get(j).schoolName);
				System.out.println(" distance: " + Mapping.getDistanceBetween(testSem[sem].semi.get(j).getLat(), testSem[sem].semi.get(j).getLng(),
						testSem[sem].getLat(), testSem[sem].getLng()));
			}
			System.out.println("\n");
		}
		
	}
}
