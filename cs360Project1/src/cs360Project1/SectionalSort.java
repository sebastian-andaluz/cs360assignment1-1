package cs360Project1;

import java.util.ArrayList;

public class SectionalSort {

	ArrayList<School> leftOverSchools;

	int overFlow;
	//Returns an array of Schools (Divided by sectional they belong to
	public ArrayList<String> sectionals (ArrayList<School> schoolList, int sectionalCount, int sectSize)
	{
		//Sectional * Schools in Sectional
		//String[][] sectionals = new String[sectionalCount][sectSize]; //TODO:sectSize + 2?];
		
		ArrayList<String> sectionals = new ArrayList<String>(sectionalCount);
		//Array for Schools that are closest to a "full" sectional
		ArrayList<School> leftOver = new ArrayList<School>(overFlow);
		int leftOverTracker = 0;
		int sectFloor = sectSize;
		
		//goes through all ~400 schools to do initial assignments
		for (int x = 0; x <= schoolList.size(); x++)
		{
			School current = schoolList.get(x);
			double leastDistance = Double.MAX_VALUE; // should be highest possible distance, might be inefficient
			int closestSchoolIndex;
			//Go through ~30 sectional hosts
			for (int y=0; y <= current.distanceToSectionalHost.size(); y++)
			{
				//Checks if the sectional it is currently comparing is closer than the previous closest
				if (current.distanceToSectionalHost.get(y) < leastDistance)
				{

					//leastDistance = current.distanceToSectionalHost[y];
					current.distanceToSectionalHost.set(y, leastDistance);
					closestSchoolIndex = y;
				
				}
			}
			//If the Sectional is already full to the minimum, place it in the leftOver for later
			//if (TODO: the sectional is already at its floor (minimum))
			{
				//leftOver[leftOverTracker] = current;
				leftOver.add(leftOverTracker, current);
				leftOverTracker++;
				//TODO: Break to next school in ~400
			}
			//else 
				//{TODO: Assign current to sectional}
		}
		
		return sectionals;
	}
	
	public void SectionalSort(School[] allSchools, Sectional[] hostSchools, int numSectionals)
	{
		//calculates the minimum number of schools per sectional
		int schoolsPerSectional = allSchools.length/numSectionals;
		//calculates the number of schools that will be left after all sectionals are full to the minimum
		int leftOverAmount = allSchools.length%numSectionals;
		//contains the current smallest distance to a host
		double smallestDistance;
		//contains the index(in the hostSchools array) of the current nearest school
		int currentHost = 0;
		//an array of the schools that are closest to a "full" sectional
		leftOverSchools = new ArrayList<School>();
		//counter for leftOverArray below
		int counter = 0; //do we use?
		//holds the currently considered school
		School currentSchool;
		//contains the distances of the current school to the hosts
		double[] distances;
		
		//iterate through all school
		for(int i = 0; i < allSchools.length; i++)
		{
			currentSchool = allSchools[i];

			if(currentSchool.schoolName.equals("Kouts High School"))
			{
				System.out.print("");
			}
			
			if (currentSchool.hostSectional == true){
				continue;
			}
			//the smallest distance available, set at a large number to ensure setting distance for the first element
			smallestDistance = Double.MAX_VALUE;
			//creates an array of distances from school to all host schools
			distances = currentSchool.addDistanceToHost(hostSchools);
			
			//iterates through all host schools
			for(int j = 0; j < distances.length; j++)
			{
				//if the distance to the current host is less than the distance of the previous closest, make current new closest
				if(distances[j] < smallestDistance)
				{
					smallestDistance = distances[j];
					currentHost = j;
					//TODO:need to create arraylist to add the closest school, but where to create it?
				}
			}
			
			
			if(currentSchool.schoolName.equalsIgnoreCase("Gary Roosevelt High School"))
			{
				System.out.print("");
			}
			
			//if the closest sectional is not full to the minimum, add the current school to it
			if(hostSchools[currentHost].sectional.size() < schoolsPerSectional)
			{
				//TODO:
				hostSchools[currentHost].sectional.add(currentSchool);
			}
			//if the current sectional is "full," add the current school to a list of leftovers
			else
			{
				leftOverSchools.add(currentSchool);
				
			}
			
			
		}
		
		//reset smallest distance to largest possible value
		smallestDistance = Double.MAX_VALUE;
		
		//iterate through the entire list of leftover schools, removing schools as they are dealt with
		while(leftOverSchools.size() > 0){
			//assigning the school to be considered to a variable
			currentSchool = leftOverSchools.get(0);
			distances = leftOverSchools.get(0).addDistanceToHost(hostSchools);
			
			//iterate through all of the host schools
			for(int k = 0; k < distances.length; k++){
				//if the distance to current host is less than the old closest host, set closest host to current host
				if(distances[k] < smallestDistance)
				{
					smallestDistance = distances[k];
					currentHost = k;
				}
			}
			//add the closest school 
			hostSchools[currentHost].sectional.add(currentSchool);
			leftOverSchools.remove(0);
		}
		
	}
	
}
