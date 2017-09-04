package cs360Project1;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassSort 
{
	ArrayList<Classification> classifications = new ArrayList<Classification>();
	int[] classMins;
	int[] classMaxs;
	
	Sectional[] sectionals;
	Regional[] regionals;
	SemiState[] semis;
	
	int totalSectionals = 0;
	int totalRegionals = 0;
	int totalSemis = 0;
	
	//TODO: Remove when new method for getting stuff added
	Scanner consoleScan = new Scanner(System.in);
	
	public void classSort(School[] schools)
	{
				
		
		//TODO: Methods to ask user for values

		System.out.print("Please enter the number of classes: ");
		
		//TODO: Console used to get number of classes
		int classNumber = getClassNum();
		
		classMins = new int[classNumber];
		classMaxs = new int[classNumber];
		
		//TODO: console used to get min/max enrollment in each class
		getRanges(classNumber);
		
		//iterate through all schools, and use findClass() to put them in classes
		for (int x=0; x < schools.length; x++){
			int checker  = findClass(schools[x], 0);
			//findClass returns 1 if it successfully assigns the class
			if (checker != 1)
			{
				System.out.println("Error with findClass");
			}
		}
		
		//Tell user what the class sizes are (Class 0 has 5 schools, etc)
		for (int y = 0; y < classifications.size(); y++)
		{
			System.out.println("Class " + y+ " has " + classifications.get(y).getClassSize() + " schools");
		}
		
		//TODO: console used to get contests per class
		contestsPerClass();
		
		//initialize the sectional/regional/semis object arrays
		sectionals = new Sectional[totalSectionals];
		regionals = new Regional[totalRegionals];
		semis = new SemiState[totalSemis];
		
		//go through each class, and create the competitions
		for (int q = 0; q < classifications.size(); q++)
		{
			sectionals[q] = sectionalSort(classifications.getSchools(), classifications.getHostSchools, TODO:size of sectional?);
			if (classifications.get(q).getRegionalNum() == 0)
			{
				System.out.println("There are 0 regionals");
			}
			//regionals[q] =;
			if (classifications.get(q).getSemiNum() == 0)
			{
				System.out.println("There are 0 semi-states");
			}
			//semis[q] =;
		}
	}
	
	//recursive function that assigns a school to the correct classification, returning 1
	public int findClass(School currentSchool, int current)
	{
		//if the school's enrollment is less than the current class's max, add it
		if(currentSchool.getEnrollment() < classifications.get(current).getMaximum())
		{
			//both add classification in school, and school in classification
			currentSchool.setClassification(current);
			classifications.get(current).addSchool(currentSchool);
			//TODO: Test Case
			//System.out.println("School:"+ currentSchool.getName() + " Enrollment:" + currentSchool.getEnrollment() + " Class:" + currentSchool.getClassification());
			return 1;
		}
		//if the school's enrollment is more than current class's max, check next largest class
		else
		{
			return findClass(currentSchool, current+1);
		}
	}
	
	public int getClassNum()
	{
		return consoleScan.nextInt();
	}
	
	public void getRanges(int classNumber)
	{
		for (int x=0; x < classNumber; x++){
			//get range (min-max) from user
			System.out.print("Please enter the minimum enrollment for class " + x+ ": " );
			classMins[x] = consoleScan.nextInt();
			System.out.print("Please enter the maximum enrollment for class " + x+ ": " );
			classMaxs[x] = consoleScan.nextInt();
			//create a new classification based on provided mins & maxs
			classifications.add(new Classification(classMins[x], classMaxs[x]));
		}
	}

	public void contestsPerClass()
	{
		//go through each class, get the number of contests per level
		for (int z = 0; z < classifications.size(); z++)
		{
			//get and assign number of sectionals
			System.out.println("How many sectionals for class " + z + " :");
			int sectSize = consoleScan.nextInt();
			classifications.get(z).setSectionalNum(sectSize);
			totalSectionals ++;
			//get and assign number of regionals
			System.out.println("How many regionals for class " + z + " :");
			int regionalSize = consoleScan.nextInt();
			classifications.get(z).setRegionalNum(regionalSize);
			totalRegionals ++;
			//get and assign number of semistates
			System.out.println("How many semi-states for class " + z + " :");
			int semiSize = consoleScan.nextInt();
			classifications.get(z).setSemiNum(semiSize);
			totalSemis ++;
		}
	}
}