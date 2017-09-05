package cs360Project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SectionalSort {

	ArrayList<School> leftOverSchools;

	int overFlow;
	public void sortSectionals(School[] allSchools, Sectional[] hostSchools, int numSectionals)
	{
		//calculates the minimum number of schools per sectional
		int schoolsPerSectional = allSchools.length/numSectionals;
		//contains the current smallest distance to a host
		double smallestDistance;
		//contains the index(in the hostSchools array) of the current nearest school
		int currentHost = 0;
		//holds the currently considered school
		School currentSchool;
		//contains the distances of the current school to the hosts
		double[] distances;
		
		int minSize = schoolsPerSectional - 1;
		int maxSize = schoolsPerSectional + 2;
		
		//iterate through all school
		for(int i = 0; i < allSchools.length; i++)
		{
			currentSchool = allSchools[i];
			
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
			
			hostSchools[currentHost].sectional.add(currentSchool);		
		}
		
		ArrayList<School> currentSectional;
		Sectional closestAvailable;
		ArrayList<Sectional> ignoredSectionals = new ArrayList<Sectional>(0);
		School newSchoolToAdd;
		boolean isValid = false;
		
		for(int b = 0; b < hostSchools.length; b++)
		{	
			ignoredSectionals.clear();
			currentSectional = hostSchools[b].sectional;
			int counter = 0; //meant to skip the host when deleting extra occurrences of the host in an arraylist
			
			//meant to delete any extra occurrences of a host in current arraylist
			for(int a = 0; a < currentSectional.size(); a++)
			{
				if(currentSectional.get(0).schoolName.equals(currentSectional.get(a).schoolName)  && counter != 0)
				{
					currentSectional.remove(a);
				}
				counter++;
			}
			
			//attempt to add current host so it doesn't try to calculate it's own distance
			ignoredSectionals.add(hostSchools[b]);
						
			isValid = false;
			//if the sectional isn't under the minimum amount of schools and not over the maximum amount of schools, continue
			//if(hostSchools[b].sectional.size() > minSize && hostSchools[b].sectional.size() < maxSize)
			if(currentSectional.size() > minSize && currentSectional.size() < maxSize)
			{
				continue;
			}
			
			
			//if the sectional is over the maximum amount of schools 
			if(currentSectional.size() > schoolsPerSectional)/////////////////////////////////////////////////////////////
			{
				//finds the closest available host
				closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
				//finds closest school to the avaialble host
				newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable);
				
				while(isValid == false)
				{
					closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
					newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable);

				 if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == true)
					{
						closestAvailable.sectional.add(newSchoolToAdd);
						currentSectional.remove(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)//////////////////////////////////////////////////
						{
							isValid = true;
						}
					}
					
					if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == false)
					{
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable); //should be school closest to closestAvailable
						
						continue;
					}
					else if(ignoredSectionals.size() == hostSchools.length-1)
					{
						ignoredSectionals.clear();						
					}
					else if(closestAvailable.sectional.size() < minSize)
					{
						closestAvailable.sectional.add(newSchoolToAdd);
						currentSectional.remove(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)/////////////////////////////////////////////////////
						{
							isValid = true;
						}
					}
					else if(closestAvailable.sectional.size() > maxSize)
					{
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable);
					}
					
				}
			}
			
			
			
			if(currentSectional.size() < minSize)//////////////////////////////////////////////////////////
			{
				//finds the closest available host
				closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
				//finds closest school to the avaialble host
				newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable);
								
				while(isValid == false)
				{
					closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
					newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable);

				 if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == true)
					{
						closestAvailable.sectional.remove(newSchoolToAdd);
						currentSectional.add(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)///////////////////////////////////////////////
						{
							isValid = true;
						}
					}
					
					if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == false)
					{
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable); //should be school closest to closestAvailable
						
						continue;
					}
					else if(ignoredSectionals.size() == hostSchools.length-1)
					{
						ignoredSectionals.clear();
						//might have to reset the skipped variable for all skipped schools
						
					}
					else if(closestAvailable.sectional.size() < minSize)
					{
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable);
					}
					else if(closestAvailable.sectional.size() > maxSize)
					{
						closestAvailable.sectional.remove(newSchoolToAdd);
						currentSectional.add(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)//////////////////////////////////
						{
							isValid = true;
						}
					}
					
				}
			}
			
		}
		
	}
	
	public void sortRegionals(Sectional[] allSectionals, Regional[] hostSchools, int numRegionals)
	{
		//calculates the minimum number of schools per sectional
		int schoolsPerRegional = allSectionals.length/numRegionals;
		//contains the current smallest distance to a host
		double smallestDistance;
		//contains the index(in the hostSchools array) of the current nearest school
		int currentHost = 0;
		//holds the currently considered school
		School currentSchool;
		//contains the distances of the current school to the hosts
		double[] distances;
		
		int schoolsPerRegionalIncludingRegional = schoolsPerRegional+1;
		
		//iterate through all school
		for(int i = 0; i < allSectionals.length; i++)
		{
			currentSchool = allSectionals[i];
			
			if (currentSchool.hostRegional == true)/////////////////////////////will this add the regionals?
			{
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
			
			hostSchools[currentHost].regional.add(currentSchool);		
		}
		
		ArrayList<School> currentRegional;
		Regional closestAvailable;
		ArrayList<Regional> ignoredRegionals = new ArrayList<Regional>(0);
		School newSchoolToAdd;
		boolean isValid = false;
		
		for(int a = 0; a < hostSchools.length; a++)
		{
			ignoredRegionals.clear();
			currentRegional = hostSchools[a].regional;
			
			while(currentRegional.size() < schoolsPerRegionalIncludingRegional)
			{
				closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
				while(closestAvailable.regional.size() <= schoolsPerRegionalIncludingRegional)
				{
					ignoredRegionals.add(closestAvailable);
					closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
				}
				newSchoolToAdd = findClosestToNewRegionalToTake(currentRegional, closestAvailable);
				closestAvailable.regional.remove(newSchoolToAdd);
				currentRegional.add(newSchoolToAdd);
			}
			
			while(currentRegional.size() > schoolsPerRegionalIncludingRegional+1)
			{
				closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
				while(closestAvailable.regional.size() >= schoolsPerRegionalIncludingRegional+1)
				{
					ignoredRegionals.add(closestAvailable);
					closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
				}
				newSchoolToAdd = findClosestToNewRegionalToAdd(currentRegional, closestAvailable);
				closestAvailable.regional.add(newSchoolToAdd);
				currentRegional.remove(newSchoolToAdd);
			}
			
		}
	}
	
	public void sortSemis(Regional[] allRegionals, SemiState[] hostSchools, int numSemis)
	{
		//calculates the minimum number of schools per sectional
		int schoolsPerSemi = allRegionals.length/numSemis;
		//contains the current smallest distance to a host
		double smallestDistance;
		//contains the index(in the hostSchools array) of the current nearest school
		int currentHost = 0;
		//holds the currently considered school
		School currentSchool;
		//contains the distances of the current school to the hosts
		double[] distances;
		
		int schoolsPerSemiIncludingSemi = schoolsPerSemi+1;
		
		//iterate through all school
		for(int i = 0; i < allRegionals.length; i++)
		{
			currentSchool = allRegionals[i];
			
			if (currentSchool.hostSemi == true)/////////////////////////////will this add the regionals?
			{
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
			
			hostSchools[currentHost].semi.add(currentSchool);		
		}
		
		ArrayList<School> currentSemi;
		SemiState closestAvailable;
		ArrayList<SemiState> ignoredSemis = new ArrayList<SemiState>(0);
		School newSchoolToAdd;
		boolean isValid = false;
		
		for(int a = 0; a < hostSchools.length; a++)
		{
			ignoredSemis.clear();
			currentSemi = hostSchools[a].semi;
			
			while(currentSemi.size() < schoolsPerSemiIncludingSemi)
			{
				closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				while(closestAvailable.semi.size() <= schoolsPerSemiIncludingSemi)
				{
					ignoredSemis.add(closestAvailable);
					closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				}
				newSchoolToAdd = findClosestToNewSemiToTake(currentSemi, closestAvailable);
				closestAvailable.semi.remove(newSchoolToAdd);
				currentSemi.add(newSchoolToAdd);
			}
			
			while(currentSemi.size() > schoolsPerSemiIncludingSemi+1)
			{
				closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				while(closestAvailable.semi.size() >= schoolsPerSemiIncludingSemi+1)
				{
					ignoredSemis.add(closestAvailable);
					closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				}
				newSchoolToAdd = findClosestToNewSemiToAdd(currentSemi, closestAvailable);
				closestAvailable.semi.add(newSchoolToAdd);
				currentSemi.remove(newSchoolToAdd);
			}
			
		}
	}
	
	
	public Sectional findClosestSectional(School section, Sectional[] hostSchools, ArrayList<Sectional> ignoreHost)
	{
		//finds all distances from current sectional to others
		double distances[] = section.addDistanceToHost(hostSchools);
		
		for(int c = 0; c < distances.length; c++)
		{
			hostSchools[c].distance = distances[c];
		}
		
		ArrayList<Double> listDistances = new ArrayList<Double>();
		for(double d : distances) listDistances.add(d);
				
		double smallestDistance = Double.MAX_VALUE;
		
		int currentHost = 0;
		
		//checks to make sure null pointer isn't thrown for small size array of ignoreHost
		int counter = 0;
		int index = 0;
		
		for(int j = 0; j < distances.length; j++)
		{	
			if(index <= ignoreHost.size()-1 && ignoreHost.contains(hostSchools[j]))
			{
				listDistances.remove(hostSchools[j].distance);
			}
			
		}
		
		for(int r = 0; r < distances.length; r++)
		{
			if(r < listDistances.size() && listDistances.get(r) < smallestDistance)
			{
				smallestDistance = listDistances.get(r);
				index = r;
			}
		}
			int arrIndex = 0;
			//loops to find where the school is in terms of indexes from the distance array (which corresponds to hosts)
			//and the array list which has removed hosts
			while(true)
			{
				
				if(listDistances.get(index) == distances[arrIndex])
				{
					currentHost = arrIndex;
					break;
				}
				else
				{
					arrIndex++;
				}
			}
		
		
		return hostSchools[currentHost];
	}
	
	public Regional findClosestRegional(School region, Regional[] hostSchools, ArrayList<Regional> ignoreHost)
	{
		//finds all distances from current sectional to others
		double distances[] = region.addDistanceToHost(hostSchools);
		
		for(int c = 0; c < distances.length; c++)
		{
			hostSchools[c].distance = distances[c];
		}
		
		ArrayList<Double> listDistances = new ArrayList<Double>();
		for(double d : distances) listDistances.add(d);
				
		double smallestDistance = Double.MAX_VALUE;
		
		int currentHost = 0;
		
		int index = 0;
		
		for(int j = 0; j < distances.length; j++)
		{	
			if(index <= ignoreHost.size()-1 && ignoreHost.contains(hostSchools[j]))
			{
				listDistances.remove(hostSchools[j].distance);
			}
			
		}
		
		for(int r = 0; r < distances.length; r++)
		{
			if(r < listDistances.size() && listDistances.get(r) < smallestDistance)
			{
				smallestDistance = listDistances.get(r);
				index = r;
			}
		}
			int arrIndex = 0;
			//loops to find where the school is in terms of indexes from the distance array (which corresponds to hosts)
			//and the array list which has removed hosts
			while(true)
			{
				
				if(listDistances.get(index) == distances[arrIndex])
				{
					currentHost = arrIndex;
					break;
				}
				else
				{
					arrIndex++;
				}
			}
		
		
		return hostSchools[currentHost];
	}
	
	
	public SemiState findClosestSemi(School semi, SemiState[] hostSchools, ArrayList<SemiState> ignoreHost)
	{
		//finds all distances from current sectional to others
		double distances[] = semi.addDistanceToHost(hostSchools);
		
		for(int c = 0; c < distances.length; c++)
		{
			hostSchools[c].distance = distances[c];
		}
		
		ArrayList<Double> listDistances = new ArrayList<Double>();
		for(double d : distances) listDistances.add(d);
				
		double smallestDistance = Double.MAX_VALUE;
		
		int currentHost = 0;
		
		int index = 0;
		
		for(int j = 0; j < distances.length; j++)
		{	
			if(index <= ignoreHost.size()-1 && ignoreHost.contains(hostSchools[j]))
			{
				listDistances.remove(hostSchools[j].distance);
			}
			
		}
		
		for(int r = 0; r < distances.length; r++)
		{
			if(r < listDistances.size() && listDistances.get(r) < smallestDistance)
			{
				smallestDistance = listDistances.get(r);
				index = r;
			}
		}
			int arrIndex = 0;
			//loops to find where the school is in terms of indexes from the distance array (which corresponds to hosts)
			//and the array list which has removed hosts
			while(true)
			{
				
				if(listDistances.get(index) == distances[arrIndex])
				{
					currentHost = arrIndex;
					break;
				}
				else
				{
					arrIndex++;
				}
			}
		
		
		return hostSchools[currentHost];
	}
	
	public School findFarthestFromHost(Sectional current)
	{	
		double maxDistance = Double.MIN_VALUE;//creates comparison variable to see what is the farthest distance
		double currentDistance = 0;//the distance from the current school to the sectional
		int newIndex = 0;//the index of the farthest school
		
		//loop through each school and find the distance, if distance is greater than current largest distance, reassign variable
		//reassign index and return the farthest school from host
		for(int i = 1; i < current.sectional.size(); i++)
		{
			currentDistance = Mapping.getDistanceBetween(current.sectional.get(0).getLat(), current.sectional.get(0).getLng(),
					current.sectional.get(i).getLat(), current.sectional.get(i).getLng());
			
			if(currentDistance > maxDistance)
			{
				maxDistance = currentDistance;
				newIndex = i;
			}
		}
		
		return current.sectional.get(newIndex);
	}
	
	//for when sectional is over the limit and you need to add one school to a new sectional
	public School findClosestToNewSectionalToAdd(ArrayList<School> current, Sectional newSectional)
	{
		double minDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int newIndex = 0;
		
		for(int i = 1; i < current.size(); i++)
		{
			currentDistance = Mapping.getDistanceBetween(current.get(i).getLat(), current.get(i).getLng(),
					newSectional.sectional.get(0).getLat(), newSectional.sectional.get(0).getLng());
			if(currentDistance < minDistance)
			{
				minDistance = currentDistance;
				newIndex = i;
			}
			
		}
		
		return current.get(newIndex);
	}
		
	//for when sectional is under the limit and you need to take one school from a sectional
	public School findClosestToNewSectionalToTake(ArrayList<School> current, Sectional newSectional)
	{
		double minDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int newIndex = 0;
		
		for(int i = 1; i < newSectional.sectional.size(); i++)
		{
			currentDistance = Mapping.getDistanceBetween(current.get(0).getLat(), current.get(0).getLng(),
					newSectional.sectional.get(i).getLat(), newSectional.sectional.get(i).getLng());
			if(currentDistance < minDistance)
			{
				minDistance = currentDistance;
				newIndex = i;
			}
			
		}
		
		return newSectional.sectional.get(newIndex);
	}
	

	public School findClosestToNewRegionalToAdd(ArrayList<School> current, Regional newRegional)
	{
		double minDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int newIndex = 0;
		
		for(int i = 1; i < current.size(); i++)
		{
			currentDistance = Mapping.getDistanceBetween(current.get(i).getLat(), current.get(i).getLng(),
					newRegional.regional.get(0).getLat(), newRegional.regional.get(0).getLng());
			if(currentDistance < minDistance &&	
	Mapping.getDistanceBetween(current.get(0).getLat(), current.get(0).getLng(), current.get(i).getLat(), current.get(i).getLng()) != 0)
			{
				minDistance = currentDistance;
				newIndex = i;
			}
			
		}
		
		return current.get(newIndex);
	}
	
	public School findClosestToNewRegionalToTake(ArrayList<School> current, Regional newRegional)
	{
		double minDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int newIndex = 0;
		
		for(int i = 1; i < newRegional.regional.size(); i++)
		{
			currentDistance = Mapping.getDistanceBetween(current.get(0).getLat(), current.get(0).getLng(),
					newRegional.regional.get(i).getLat(), newRegional.regional.get(i).getLng());
			if(currentDistance < minDistance&&	
	Mapping.getDistanceBetween(newRegional.regional.get(0).getLat(), newRegional.regional.get(0).getLng(),
			newRegional.regional.get(i).getLat(), newRegional.regional.get(i).getLng()) != 0)
			{
				minDistance = currentDistance;
				newIndex = i;
			}
			
		}
		
		return newRegional.regional.get(newIndex);
	}
	
	public School findClosestToNewSemiToAdd(ArrayList<School> current, SemiState newSemi)
	{
		double minDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int newIndex = 0;
		
		for(int i = 1; i < current.size(); i++)
		{
			currentDistance = Mapping.getDistanceBetween(current.get(i).getLat(), current.get(i).getLng(),
					newSemi.semi.get(0).getLat(), newSemi.semi.get(0).getLng());
			if(currentDistance < minDistance &&	
	Mapping.getDistanceBetween(current.get(0).getLat(), current.get(0).getLng(), current.get(i).getLat(), current.get(i).getLng()) != 0)
			{
				minDistance = currentDistance;
				newIndex = i;
			}
			
		}
		
		return current.get(newIndex);
	}
	
	public School findClosestToNewSemiToTake(ArrayList<School> current, SemiState newSemi)
	{
		double minDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int newIndex = 0;
		
		for(int i = 1; i < newSemi.semi.size(); i++)
		{
			currentDistance = Mapping.getDistanceBetween(current.get(0).getLat(), current.get(0).getLng(),
					newSemi.semi.get(i).getLat(), newSemi.semi.get(i).getLng());
			if(currentDistance < minDistance&&	
	Mapping.getDistanceBetween(newSemi.semi.get(0).getLat(), newSemi.semi.get(0).getLng(),
			newSemi.semi.get(i).getLat(), newSemi.semi.get(i).getLng()) != 0)
			{
				minDistance = currentDistance;
				newIndex = i;
			}
			
		}
		
		return newSemi.semi.get(newIndex);
	}

}

