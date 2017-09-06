package cs360Project1;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassSort 
{
	//creates an array list of different classes
	ArrayList<Classification> classifications = new ArrayList<Classification>();
	//max and min size of enrollment per class
	int[] classMins;
	int[] classMaxs;
	
	//sectionals, regionals, semis per class
	Sectional[] sectionals;
	Regional[] regionals;
	SemiState[] semis;
	
	//total regionals, sectionals, and semis in all clasess
	int totalSectionals = 0;
	int totalRegionals = 0;
	int totalSemis = 0;
	
	//create and instantiate a sort class
	SectionalSort sort = new SectionalSort();
	
	Scanner consoleScan = new Scanner(System.in);
	
	public void classSort(School[] schools)
	{			
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
			classifications.get(y).addSectionalHost();
			classifications.get(y).addRegionalHost();
			classifications.get(y).addSemiHost();
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
			if (classifications.get(q).getRegionalNum() == 0)
			{
				System.out.println("There are 0 regionals");
			}
			if (classifications.get(q).getSemiNum() == 0)
			{
				System.out.println("There are 0 semi-states");
			}
		}
		
		sortPerClass();
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
			return 1;
		}
		//if the school's enrollment is more than current class's max, check next largest class
		else
		{
			return findClass(currentSchool, current+1);
		}
	}
	
	/*
	 * @return the size of the class number
	 */
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
			System.out.println("Current amount of sectionals: " + classifications.get(z).getSectionalNum());
			System.out.println("How many sectionals for class " + z + " :");
			int sectSize = consoleScan.nextInt();
			classifications.get(z).checkAmountSectionals(sectSize);
			totalSectionals += sectSize;
			
			//get and assign number of regionals
			System.out.println("Current amount of regionals: " + classifications.get(z).getRegionalNum());
			System.out.println("How many regionals for class " + z + " :");
			int regionalSize = consoleScan.nextInt();
			classifications.get(z).checkAmountRegionals(regionalSize);
			totalRegionals += regionalSize;
			
			//get and assign number of semistates
			System.out.println("Current amount of semis: " + classifications.get(z).getSemiNum());
			System.out.println("How many semi-states for class " + z + " :");
			int semiSize = consoleScan.nextInt();
			classifications.get(z).checkAmountSemis(semiSize);
			totalSemis += semiSize;
			
			//grab all the schools in the class and put them in an array
			classifications.get(z).convertAllSchoolsToArray();
		}
	}
	
	public void sortPerClass()
	{
		
		//loop through each class
		for(int i = 0; i < classifications.size(); i++)
		{	
			//if there are no sectionals, create a shadow sectional
			if(classifications.get(i).sectHostSchools.size() ==  0)
			{
				//add a shadow sectional into a regional so the sorting method can work
				for(int j = 0; j < classifications.get(i).regHostSchools.size(); j++)
				{
					//takes the current sectional & adds the regional as a "shadow" sectional
					classifications.get(i).regHostSchools.get(j).hostSectional = true;
					classifications.get(i).sectHostSchools.add(classifications.get(i).regHostSchools.get(j));
				}
				
				//convert all sectionals, regionals, and semis from array lists to arrays
				Sectional[] testSec = classifications.get(i).convertAllSectionalsToArray();
				Regional[] testReg = classifications.get(i).convertAllRegionalsToArray();
				SemiState[] testSem = classifications.get(i).convertAllSemisToArray();
				
				//sort the sectionals, regionals, and semis
				sort.sortSectionals(classifications.get(i).allSchools, testSec, testSec.length, true);
				sort.sortRegionals(testSec, testReg, testReg.length, false);
				sort.sortSemis(testReg, testSem, testSem.length);
				
				//average distance per sectional, regional, semi
				//total distacne per sectional, regional, semi
				double total = 0;
				double avg = 0;
				
				//reset average and total to 0
				total = 0;
				avg = 0;
				//loop through all the regionals and find the total for each iteration
				for(int reg = 0; reg < testReg.length; reg++)
				{
					//reset average and total to 0
					total = 0;
					avg = 0;
					//finds total
					for(int a = 0; a < testReg[reg].sectional.size(); a++)
					{
						total += Mapping.getDistanceBetween(testReg[reg].sectional.get(a).getLat(), testReg[reg].sectional.get(a).getLng(),
								testReg[reg].getLat(), testReg[reg].getLng());
					}
					
					//calculate the average
					avg = total/(testReg[reg].sectional.size() - 1);
					
					//print the current regional and it's schools and it's average
					System.out.println("Regional " + (reg+1) + " Average: " + avg);
					for(int j = 0; j < testReg[reg].sectional.size(); j++)
					{
						System.out.print(testReg[reg].sectional.get(j).schoolName);
						System.out.println(" distance: " + Mapping.getDistanceBetween(testReg[reg].sectional.get(j).getLat(), testReg[reg].sectional.get(j).getLng(),
								testReg[reg].getLat(), testReg[reg].getLng()));
					}
					System.out.println("\n");
				}
				
				System.out.println("\n\n");
				
				//reset average and total to 0
				total = 0;
				avg = 0;
				//loop through all the regionals and find the total for each iteration
				for(int sem = 0; sem < testSem.length; sem++)
				{
					//reset average and total to 0
					total = 0;
					avg = 0;
					//finds total
					for(int a = 0; a < testSem[sem].semi.size(); a++)
					{
						total += Mapping.getDistanceBetween(testSem[sem].semi.get(a).getLat(), testSem[sem].semi.get(a).getLng(),
								testSem[sem].getLat(), testSem[sem].getLng());
					}
					
					//calculate the average
					avg = total/(testSem[sem].semi.size() - 1);
					
					//print the current regional and it's schools and it's average
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
			//if there are no regionals
			else if(classifications.get(i).regHostSchools.size() == 0)
			{
				//add a shadow sectional into a regional so the sorting method can work
				for(int j = 0; j < classifications.get(i).semiHostSchools.size(); j++)
				{
					//takes the current sectional & adds the regional as a "shadow" sectional
					classifications.get(i).semiHostSchools.get(j).hostRegional = true;
					classifications.get(i).regHostSchools.add(classifications.get(i).semiHostSchools.get(j));
				}
				
				//convert all sectionals, regionals, and semis from array lists to arrays
				Sectional[] testSec = classifications.get(i).convertAllSectionalsToArray();
				Regional[] testReg = classifications.get(i).convertAllRegionalsToArray();
				SemiState[] testSem = classifications.get(i).convertAllSemisToArray();
				
				//sort the sectionals, regionals, and semis
				sort.sortSectionals(classifications.get(i).allSchools, testSec, testSec.length, false);
				sort.sortRegionals(testSec, testReg, testReg.length, true);
				sort.sortSemis(testReg, testSem, testSem.length);
				
				//average distance per sectional, regional, semi
				//total distacne per sectional, regional, semi
				double total = 0;
				double avg = 0;
				
				//loop through all the sectionals and find the total for each iteration
				for(int sect = 0; sect < testSec.length; sect++)
				{
					//reset average and total to 0
					total = 0;
					avg = 0;
					//finds total
					for(int a = 0; a < testSec[sect].sectional.size(); a++)
					{
						total += Mapping.getDistanceBetween(testSec[sect].sectional.get(a).getLat(), testSec[sect].sectional.get(a).getLng(),
								testSec[sect].getLat(), testSec[sect].getLng());
					}
					
					//calculate the average
					avg = total/testSec[sect].sectional.size();
					
					
					//print the current sectional and it's schools and it's average
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
				
				System.out.println("\n\n");
				
				//reset average and total to 0
				total = 0;
				avg = 0;
				
				//loop through all the semis and find the total for each iteration
				for(int sem = 0; sem < testSem.length; sem++)
				{
					//reset average and total to 0
					total = 0;
					avg = 0;
					//finds total
					for(int a = 0; a < testSem[sem].regional.size(); a++)
					{
						total += Mapping.getDistanceBetween(testSem[sem].regional.get(a).getLat(), testSem[sem].regional.get(a).getLng(),
								testSem[sem].getLat(), testSem[sem].getLng());
					}
					
					//calculate the average
					avg = total/(testSem[sem].semi.size() - 1);
					
					//print the current semi and it's schools and it's average
					System.out.println("Semi " + (sem+1) + " Average: " + avg);
					for(int j = 0; j < testSem[sem].regional.size(); j++)
					{
						System.out.print(testSem[sem].regional.get(j).schoolName);
						System.out.println(" distance: " + Mapping.getDistanceBetween(testSem[sem].regional.get(j).getLat(), testSem[sem].regional.get(j).getLng(),
								testSem[sem].getLat(), testSem[sem].getLng()));
					}
					System.out.println("\n");	
				}
			}
			//skip this occurrence
			else if(classifications.get(i).semiHostSchools.size() == 0)
			{
				System.out.println("No Semis");
			}
			else
			{
				//convert all sectionals, regionals, and semis from array lists to arrays
				Sectional[] testSec = classifications.get(i).convertAllSectionalsToArray();
				Regional[] testReg = classifications.get(i).convertAllRegionalsToArray();
				SemiState[] testSem = classifications.get(i).convertAllSemisToArray();
				
				//sort the sectionals, regionals, and semis
				sort.sortSectionals(classifications.get(i).allSchools, testSec, testSec.length, false);
				sort.sortRegionals(testSec, testReg, testReg.length, false);
				sort.sortSemis(testReg, testSem, testSem.length);
				
				//average distance per sectional, regional, semi
				//total distacne per sectional, regional, semi
				double total = 0;
				double avg = 0;
				
				//loop through all the sectionals and find the total for each iteration
				for(int sect = 0; sect < testSec.length; sect++)
				{
					//reset average and total to 0
					total = 0;
					avg = 0;
					//finds total
					for(int a = 0; a < testSec[sect].sectional.size(); a++)
					{
						total += Mapping.getDistanceBetween(testSec[sect].sectional.get(a).getLat(), testSec[sect].sectional.get(a).getLng(),
								testSec[sect].getLat(), testSec[sect].getLng());
					}
					
					//calculate the average
					avg = total/testSec[sect].sectional.size();
					
					//print the current sectional and it's schools and it's average
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
				
				//reset average and total to 0
				total = 0;
				avg = 0;
						
				//loop through all the regionals and find the total for each iteration
				for(int reg = 0; reg < testReg.length; reg++)
				{
					//reset average and total to 0
					total = 0;
					avg = 0;
					//finds total
					for(int a = 0; a < testReg[reg].regional.size(); a++)
					{
						total += Mapping.getDistanceBetween(testReg[reg].regional.get(a).getLat(), testReg[reg].regional.get(a).getLng(),
								testReg[reg].getLat(), testReg[reg].getLng());
					}
					
					//calculate the average
					avg = total/(testReg[reg].regional.size() - 1);
					
					//print the current regional and it's schools and it's average
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
				
				//reset average and total to 0
				total = 0;
				avg = 0;
							
				//loop through all the semis and find the total for each iteration
				for(int sem = 0; sem < testSem.length; sem++)
				{
					//reset average and total to 0
					total = 0;
					avg = 0;
					//finds total
					for(int a = 0; a < testSem[sem].semi.size(); a++)
					{
						total += Mapping.getDistanceBetween(testSem[sem].semi.get(a).getLat(), testSem[sem].semi.get(a).getLng(),
								testSem[sem].getLat(), testSem[sem].getLng());
					}
					
					//calculate the average
					avg = total/(testSem[sem].semi.size() - 1);
					
					//print the current semi and it's schools and it's average
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
	}
}
