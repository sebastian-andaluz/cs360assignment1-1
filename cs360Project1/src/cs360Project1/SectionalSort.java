package cs360Project1;

public class SectionalSort {


	int overFlow;
	//Returns an array of Schools (Divided by sectional they belong to
	public String[][] sectionals (School[] schoolList, int sectionalCount, int sectSize)
	{
		//Sectional * Schools in Sectional
		String[][] sectionals = new String[sectionalCount][sectSize]; //TODO:sectSize + 2?];
		//Array for Schools that are closest to a "full" sectional
		School[] leftOver = new School[overFlow];
		int leftOverTracker = 0;
		int sectFloor = sectSize;
		
		//goes through all ~400 schools to do initial assignments
		for (int x = 0; x <= schoolList.length; x++)
		{
			School current = schoolList[x];
			double leastDistance = Double.MAX_VALUE; // should be highest possible distance, might be inefficient
			int closestSchoolIndex;
			//Go through ~30 sectional hosts
			for (int y=0; y <= current.distanceToSectionalHost.length; y++)
			{
				//Checks if the sectional it is currently comparing is closer than the previous closest
				if (current.distanceToSectionalHost[y] < leastDistance)
				{

					leastDistance = current.distanceToSectionalHost[y];
					closestSchoolIndex = y;
				
				}
			}
			//If the Sectional is already full to the minimum, place it in the leftOver for later
			//if (TODO: the sectional is already at its floor (minimum))
			{
				leftOver[leftOverTracker] = current;
				leftOverTracker++;
				//TODO: Break to next school in ~400
			}
			//else 
				//{TODO: Assign current to sectional}
		}
		
		return sectionals;
	}
	
}
