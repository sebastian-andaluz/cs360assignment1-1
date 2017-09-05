package cs360Project1;

import java.util.ArrayList;

public class SectionalSort {

	School[] leftOverSchools;

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
	
	public void SectionalSort(School[] allSchools, School[] hostSchools, int numSectionals)
	{
		int schoolsPerSectional = allSchools.length/numSectionals;
		int leftOverAmount = allSchools.length%numSectionals;
		double smallestDistance;
		double[] distances;
		leftOverSchools = new School[leftOverAmount];
		int index;
		School schoolToAdd;
		
		for(int i = 0; i < allSchools.length; i++)
		{
			//the smallest distance available, set at a large number to ensure setting distance for the first element
			smallestDistance = Double.MAX_VALUE;
			//creates an array of distances from school to all host schools
			distances = allSchools[i].addDistanceToHost(hostSchools);
			for(int j = 0; j < distances.length; j++)
			{
				if(distances[j] < smallestDistance)
				{
					smallestDistance = distances[j];
					index = j;
					//TODO:need to create arraylist to add the closest school, but where to create it?
				}
			}
		}
	}
	
}
