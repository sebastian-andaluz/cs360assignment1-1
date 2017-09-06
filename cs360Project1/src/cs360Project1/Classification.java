package cs360Project1;

import java.util.ArrayList;
import java.util.Scanner;

public class Classification {
	
	int minimumSize; //minimum enrollment size in the classification
	int maximumSize; //maximum enrollment size in the classification
	
	//scanner for testing
	Scanner kb = new Scanner(System.in);
	
	//arraylist of the schools that are in the enrollment range for the classification
	ArrayList<School> schools = new ArrayList<School>();
	//arraylist of the sectionals in the classification
	ArrayList<Sectional> sectHostSchools = new ArrayList<Sectional>();
	//arraylist of the regionals in the classification
	ArrayList<Regional> regHostSchools = new ArrayList<Regional>();
	//arraylist of the semi-states in the classification
	ArrayList<SemiState> semiHostSchools = new ArrayList<SemiState>();
	
	int sectionalNum; //the number of sectionals in the class
	int regionalNum; //the number of regionals in the class
	int semiNum; //the number of semi-states in the class
	
	Sectional[] sectionals; //array of the sectionals in the class
	Regional[] regionals; //array of the regionals in the class
	SemiState[] semis; //array of the semi-states in the class
	School[] allSchools; //array of the schools in the class
	
	//Constructor for a Classification
	public Classification(int minSize, int maxSize){
		minimumSize = minSize;
		maximumSize = maxSize;
	}
	
	//converts the Arraylist of Schools to the array of Schools
	public School[] convertAllSchoolsToArray()
	{
		allSchools = new School[schools.size()];
		for(int i = 0; i < allSchools.length; i++)
		{
			allSchools[i] = schools.get(i);
		}
		
		return allSchools;
	}
	//converts the Arraylist of Sectionals to the array of Sectionals
	public Sectional[] convertAllSectionalsToArray()
	{
		sectionals = new Sectional[sectHostSchools.size()];
		for(int i = 0; i < sectionals.length; i++)
		{
			sectionals[i] = sectHostSchools.get(i);
		}
		return sectionals;
	}
	//converts the Arraylist of Regionals to the array of Regionals
	public Regional[] convertAllRegionalsToArray()
	{
		regionals = new Regional[regHostSchools.size()];
		for(int i = 0; i < regionals.length; i++)
		{
			regionals[i] = regHostSchools.get(i);
		}
		return regionals;
	}
	//converts the Arraylist of Semi-statess to the array of Semi-states
	public SemiState[] convertAllSemisToArray()
	{
		semis = new SemiState[semiHostSchools.size()];
		for(int i = 0; i < semis.length; i++)
		{
			semis[i] = semiHostSchools.get(i);
		}
		return semis;
	}
	
	//add and get schools in the class
	public void addSchool(School toAdd){
		schools.add(toAdd);	
	}
	public ArrayList<School> getSchools(){
		return schools;
	}
	//iterates through the list of schools  and finds the ones that are classified as sectional hosts
	public void addSectionalHost()
	{
		for(int i = 0; i < schools.size(); i++)
		{
			if(schools.get(i).hostSectional == true)
			{
				sectHostSchools.add(new Sectional(schools.get(i)));
			}
		}	
	}
	//iterates through the list of schools  and finds the ones that are classified as regional hosts
	public void addRegionalHost()
	{
		for(int i = 0; i < schools.size(); i++)
		{
			if(schools.get(i).hostRegional == true)
			{
				regHostSchools.add(new Regional(schools.get(i)));
			}
		}	
	}
	//iterates through the list of schools  and finds the ones that are classified as semi-state hosts
	public void addSemiHost()
	{
		for(int i = 0; i < schools.size(); i++)
		{
			if(schools.get(i).hostSemi == true)
			{
				semiHostSchools.add(new SemiState (schools.get(i)));
			}
		}
	}
	//mutate and access minimum enrollment size of a class
	public void setMinimum(int newMin){
		minimumSize = newMin;
	}
	public int getMinimum(){
		return minimumSize;
	}
	//mutate and access maximum enrollment size of a class
	public void setMaximum(int newMax){
		maximumSize = newMax;
	}
	public int getMaximum(){
		return maximumSize;
	}
	//returns the number of schools in the class
	public int getClassSize(){
		return schools.size();
	}
	//for sectional setSize, set size and the array of schools in sect
	public void setSectionalNum(int size){
		sectionalNum = size;
		sectionals = new Sectional[size];
	}
	//return the number of sectional host schools in the class
	public int getSectionalNum()
	{
		return sectHostSchools.size();
	}
	//returns the array of sectionals in the class
	public Sectional[] getSectionals(){
		return sectionals;
	}
	//for regional setSize, set size and set the array of schools in sect
	public void setRegionalNum(int size){
		regionalNum = size;
		regionals = new Regional[size];
	}
	//return the number of regional hosts in the class
	public int getRegionalNum()
	{
		return regHostSchools.size();
	}
	//return the array of regionals in the class
	public Sectional[] getRegionals(){
		return regionals;
	}
	//for semi-state setSize, set size and set the array of schools in sect
	public void setSemiNum(int size){
		semiNum = size;
		semis = new SemiState[size];
	}
	//return the number of semi hosts in the class
	public int getSemiNum()
	{
		return semiHostSchools.size();
	}
	//return the array of semis in the class
	public Sectional[] getsemis(){
		return semis;
	}	
	//takes the number of sectionals and determines if the number of hosts matches
	public void checkAmountSectionals(int sectionalNum)
	{	int counter = 0;
		//if there are no sectional hosts, remove all hosts from the array of hosts
		if(sectionalNum == 0)
		{
			sectHostSchools.clear();
		}
		//if there are an equal amount of hosts to sectionals, do nothing
		else if(sectHostSchools.size() == sectionalNum)
		{
			//TODO: REMOVE Testing print
			System.out.println("Perfect Amount");
		}
		//if there is one host, ask the user to select a single host to keep, rather than remove many hosts
		else if(sectionalNum == 1)
		{
			System.out.println("Select the one host you would like to keep:");
			String schoolToKeep = kb.nextLine();
			//school object to keep the one school choosen
			School kept = new School();
			//clear the arraylist of all sectional hosts
			sectHostSchools.clear();
			//iterates through all of the schools
			for(int i = 0; i < schools.size(); i++)
			{
				//compares the selected school to the current school in the loop
				if(schools.get(i).schoolName.equalsIgnoreCase(schoolToKeep))
				{
					//if the current school is the inputted one, place it in kept
					kept = schools.get(i);
				}
			}
			//add the school choosen as the first sectional object
			sectHostSchools.add(new Sectional(kept));
				
		}
		//if there are less hosts than there will be sectionals
		else if(sectHostSchools.size() < sectionalNum)
		{
			//not enough
			System.out.println("There is currently not enough sectional hosts. Please add more:");
			//while there are not enough hosts, have user input school to have host
			while(sectHostSchools.size() < sectionalNum)
			{
				System.out.println("School to add:");
				String schoolToAdd = kb.nextLine();
				//iterate though all schools to to compare the inputted school to all the schools
				for(int i = 0; i < schools.size(); i++)
				{
					if(schools.get(i).schoolName.equalsIgnoreCase(schoolToAdd))
					{
						sectHostSchools.add(new Sectional(schools.get(i)));
						schools.get(i).hostSectional = true;
					}
				}
				
			}
			
			
		}
		//if there are more hosts than there will be sectionals
		else if(sectHostSchools.size() > sectionalNum)
		{
			//too many
			System.out.println("There is currently too many sectional hosts. Please delete some:");
			//while there are too many hosts, continue
			while(sectHostSchools.size() > sectionalNum)
			{
				System.out.println("School to remove:");
				//user inputted host school to remove
				String schoolToAdd = kb.nextLine();
				//iterate through all schools, looking for the inputed school
				for(int i = 0; i < schools.size(); i++)
				{
					
					while(counter < sectHostSchools.size() && !(sectHostSchools.get(counter).schoolName.equalsIgnoreCase(schoolToAdd)))
					{
						counter++;
					}
					//if the current school is the one inputted to remove, remove
					if(schools.get(i).schoolName.equalsIgnoreCase(schoolToAdd))
					{
						sectHostSchools.remove(schools.get(i));
						schools.get(i).hostSectional = false;
					}
					
					counter = 0;
				}
			}
		}
	}
	
	public void checkAmountRegionals(int regionalNum)
	{	
		int counter = 0;
		//if no regionals, clear the list of regionals
		if(regionalNum == 0)
		{
			regHostSchools.clear();
		}
		//if the number of hosts and regionals is the same, tell the user
		else if(regHostSchools.size() == regionalNum)
		{
			//TODO: REMOVE Testing print
			System.out.println("Perfect Amount");
		}
		//if there is only one regional, have the user select only one to keep
		else if(regionalNum == 1)
		{
			System.out.println("Select the one host you would like to keep:");
			//holds the name of the school to keep
			String schoolToKeep = kb.nextLine();
			School kept = new School();
			regHostSchools.clear();
			//iterate through all the schools
			for(int i = 0; i < schools.size(); i++)
			{
				//if the current school equals the one entered to keep, keep it
				if(schools.get(i).schoolName.equalsIgnoreCase(schoolToKeep))
				{
					kept = schools.get(i);
				}
			}
			//add only the kept school to the list of regionals
			regHostSchools.add(new Regional(kept));
		}
		//if the there are not enough hosts, get more from user
		else if(regHostSchools.size() < regionalNum)
		{
			//not enough
			System.out.println("There is currently not enough regional hosts. Please add more:");
			//while there are not enough hosts, keep asking
			while(regHostSchools.size() < regionalNum)
			{
				System.out.println("School to add:");
				//holds name of school to add
				String schoolToAdd = kb.nextLine();
				//iterate through all schools to find the one that was entered to add
				for(int i = 0; i < schools.size(); i++)
				{
					//if the current school is the one entereed, add it as a regional object
					if(schools.get(i).schoolName.equalsIgnoreCase(schoolToAdd))
					{
						regHostSchools.add(new Regional(schools.get(i)));
						schools.get(i).hostRegional = true;
					}
				}
				
			}
		}
		//if there are too many hosts
		else if(regHostSchools.size() > regionalNum)
		{
			//too many
			System.out.println("There is currently too many regional hosts. Please delete some:");
			//while there are too many hosts, keep asking for hosts to remove
			while(regHostSchools.size() > regionalNum)
			{
				System.out.println("School to remove:");
				//holds the name of the host to remove
				String schoolToAdd = kb.nextLine();
				for(int i = 0; i < schools.size(); i++)
				{
			
					//finds where in the array list is the school that is wanting to be removed
					while(counter < regHostSchools.size() && !(regHostSchools.get(counter).schoolName.equalsIgnoreCase(schoolToAdd)))
					{
						counter++;
					}
					//if the current school is the school to remove, remove it
					if(schools.get(i).schoolName.equalsIgnoreCase(schoolToAdd))
					{
						regHostSchools.remove(counter);
						schools.get(i).hostRegional = false;
					}
					
					counter = 0;
				}
			}
		}
	}
	
	public void checkAmountSemis(int semiNum)
	{
		int counter = 0;
		//if there are no semis, clear the list
		if(semiNum == 0)
		{
			semiHostSchools.clear();
		}
		//if the number of semis and semi hosts are equal, tell the user
		else if(semiHostSchools.size() == semiNum)
		{
			//TODO: REMOVE Testing print
			System.out.println("Perfect Amount");
		}
		//if there is only one semi, ask the user for 1 semi to keep, and remove the rest
		else if(semiNum == 1)
		{
			System.out.println("Select the one host you would like to keep:");
			//string of the name of the school to keep
			String schoolToKeep = kb.nextLine();
			School kept = new School();
			semiHostSchools.clear();
			//iterates through all the schools 
			for(int i = 0; i < schools.size(); i++)
			{
				if(schools.get(i).schoolName.equalsIgnoreCase(schoolToKeep))
				{
					kept = schools.get(i);
				}
			}
			semiHostSchools.add(new SemiState(kept));
		}
		//if there are not enough hosts, ask user for more
		else if(semiHostSchools.size() < semiNum)
		{
			//not enough
			System.out.println("There is currently not enough semi hosts. Please add more:");
			//while there is not enough hosts, continue asking
			while(semiHostSchools.size() < semiNum)
			{
				System.out.println("School to add:");
				//holds name of host to add
				String schoolToAdd = kb.nextLine();
				//iterate through all the schools to find the one inputted
				for(int i = 0; i < schools.size(); i++)
				{
					//if the current school is the one entered, add it as a semi-state object
					if(schools.get(i).schoolName.equalsIgnoreCase(schoolToAdd))
					{
						semiHostSchools.add(new SemiState(schools.get(i)));
						schools.get(i).hostSemi = true;
					}
				}
				
			}
		}
		//if there are too many hosts, ask user for which to remove
		else if(semiHostSchools.size() > semiNum)
		{
			//too many
			System.out.println("There is currently too many semi hosts. Please delete some:");
			//while there are too many hosts, continue
			while(semiHostSchools.size() > semiNum)
			{
				System.out.println("School to remove:");
				//holds name of school to remove from host list
				String schoolToAdd = kb.nextLine();
				//iterate through all schools to find the one entered
				for(int i = 0; i < schools.size(); i++)
				{
					//finds where in the array list is the school that is wanting to be removed
					while(counter < semiHostSchools.size() && !(semiHostSchools.get(counter).schoolName.equalsIgnoreCase(schoolToAdd)))
					{
						counter++;
					}
					//if the current school is the one entered, remove it
					if(schools.get(i).schoolName.equalsIgnoreCase(schoolToAdd))
					{
						semiHostSchools.remove(schools.get(i));
						schools.get(i).hostSemi = false;
					}
					
					counter = 0;
				}
			}
		}
	}
}
