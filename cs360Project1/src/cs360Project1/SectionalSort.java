package cs360Project1;

//imports ArrayList
import java.util.ArrayList;

public class SectionalSort {
	
	public void sortSectionals(School[] allSchools, Sectional[] hostSchools, int numSectionals, boolean shadow)
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
		//maximum and minimum size requirements per sectional
		int minSize;
		int maxSize;
		
		//if there are sectionals, then use the normal range of sectionals
		//if there are no sectionals (used in class structure) then we call a shadow sectional
		//and add the regionals to the sectional, also increasing the range of sectionals in this case
		if(shadow == false)
		{
			minSize = schoolsPerSectional - 1;
			maxSize = schoolsPerSectional + 2;
		}
		else
		{
			minSize = schoolsPerSectional - (schoolsPerSectional/4);
			maxSize = schoolsPerSectional + (schoolsPerSectional/4);
		}
		
		
		//iterate through all school
		for(int i = 0; i < allSchools.length; i++)
		{
			//grab the current school
			currentSchool = allSchools[i];
			
			//if the current school is a host, we skip it
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
				}
			}
			
			//add schools to a host, not caring about size at this point
			hostSchools[currentHost].sectional.add(currentSchool);		
		}
		
		ArrayList<School> currentSectional; //the current sectional we are at
		Sectional closestAvailable; //the closest available sectional
		ArrayList<Sectional> ignoredSectionals = new ArrayList<Sectional>(0); //the sectionals we are skipping over
		School newSchoolToAdd; //new school to add from a sectional
		boolean isValid = false; //boolean to check if the current sectional is not in the range
		
		//loop through the host schools
		for(int b = 0; b < hostSchools.length; b++)
		{	
			//remove any leftover ignored sectionals from previous iterations
			ignoredSectionals.clear();
			//grab the current sectional from iteration
			currentSectional = hostSchools[b].sectional;
			//meant to skip the host when deleting extra occurrences of the host in an arraylist
			int counter = 0; 
			
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
			if(currentSectional.size() > schoolsPerSectional)
			{
				//finds the closest available host
				closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
				//finds closest school to the avaialble host
				newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable);
				
				//loops through while sectional is not in the range
				while(isValid == false)
				{
					//grab the closest sectional and finds a school to add to the closest sectional, ignored desired sectionals
					closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
					newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable);

				//if it's in the range and skipped, just add it here
				 if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == true)
					{
						closestAvailable.sectional.add(newSchoolToAdd);
						currentSectional.remove(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)
						{
							isValid = true;
						}
					}
					//if it's in the range and not skipped, skip for now and set boolean field to true
					if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == false)
					{
						//if the current sectional is already in the ignored sectionals, find the next sectional
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						//otherwise add it here to ignore
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						//set it's boolean skipped field to true and find the next closest sectional and school
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable); //should be school closest to closestAvailable
						
						continue;
					}
					//if the ignoredSectionals Array List is full, clear it
					else if(ignoredSectionals.size() == hostSchools.length-1)
					{
						ignoredSectionals.clear();						
					}
					//if the closest available sectional is under the range, add the school here
					//remove from the current sectional and check if the current sectional
					//is at the right size, if not, keep looping, otherwise, break
					else if(closestAvailable.sectional.size() < minSize)
					{
						closestAvailable.sectional.add(newSchoolToAdd);
						currentSectional.remove(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)
						{
							isValid = true;
						}
					}
					//if the closest available sectional is over the range, skip it
					else if(closestAvailable.sectional.size() > maxSize)
					{
						//if the current sectional is already in the ignored sectionals, find the next sectional
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						//otherwise add it here to ignore
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						//set it's boolean skipped field to true and find the next closest sectional and school
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToAdd(currentSectional, closestAvailable);
					}
				}
			}
			
			
			//if the current sectional is under the range
			if(currentSectional.size() < minSize)
			{
				//finds the closest available host
				closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
				//finds closest school to the avaialble host
				newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable);
							
				//loops through while the sectional is not in the desired range
				while(isValid == false)
				{
					//finds the closest available host, ignoring the desired sectionals
					closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
					//finds the closes school from the current sectional to take from
					newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable);

				//if the sectional is in the range and has been skipped, add new school to current sectional
				//remove from old sectional and check if current sectional is at the right size, if not, continue looping
				 if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == true)
					{
						closestAvailable.sectional.remove(newSchoolToAdd);
						currentSectional.add(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)
						{
							isValid = true;
						}
					}
					//if the closest available sectional is in the range and has not been skipped, skip it and add it to ignored sectionals
				 	//also assign skipped boolean to true
					if(closestAvailable.sectional.size() >= minSize && closestAvailable.sectional.size() <= maxSize && closestAvailable.skippedSectional == false)
					{
						//if the sectional is in the ignored array, then skip it and find the next closest sectional
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						//otherwise add it to the ignored list
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						//set it's skipped field to true and find the next closest sectional and school
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable); //should be school closest to closestAvailable
						
						continue;
					}
					//if the ignored sectionals array list is full, clear it
					else if(ignoredSectionals.size() == hostSchools.length-1)
					{
						ignoredSectionals.clear();
						
					}
					//if the closest sectional is under the minimum range, skip over it
					else if(closestAvailable.sectional.size() < minSize)
					{
						//if it is already in the ignored list, find the next available sectional
						if(ignoredSectionals.contains(closestAvailable))
						{
							closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
							continue;

						}
						//otherwise add it here
						else
						{
							ignoredSectionals.add(closestAvailable);
						}
						//set it's skipped field to true and find the next closest sectional and school
						closestAvailable.skippedSectional = true;
						closestAvailable = findClosestSectional(currentSectional.get(0), hostSchools, ignoredSectionals);
						newSchoolToAdd = findClosestToNewSectionalToTake(currentSectional, closestAvailable);
					}
					//if the closest available sectional is over the range, take the school closest from the
					//current sectional and delete it from the old sectional, then check to see if the
					//current sectional is now in the range, if it is, break the loop, otherwise, continue
					else if(closestAvailable.sectional.size() > maxSize)
					{
						closestAvailable.sectional.remove(newSchoolToAdd);
						currentSectional.add(newSchoolToAdd);
						if(currentSectional.size() == schoolsPerSectional)
						{
							isValid = true;
						}
					}
				}
			}
			
		}
		
	}
	
	public void sortRegionals(Sectional[] allSectionals, Regional[] hostSchools, int numRegionals, boolean shadow)
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
		//helps set range for the regionals
		int maxSize;
		int minSize; 
		
		//if there are regionals, add the normal amount of range
		//if there are no regionals (from the shadowing of class structure)
		//then increase the range of regionals
		if(shadow == false)
		{
			minSize = schoolsPerRegional - 1;
			maxSize = schoolsPerRegional + 2;
		}
		else
		{
			minSize = schoolsPerRegional - (schoolsPerRegional/4);
			maxSize = schoolsPerRegional + (schoolsPerRegional/4);
		}
		//add to the range the host
		int schoolsPerRegionalIncludingRegional = schoolsPerRegional+1;
			
		//iterate through all school
		for(int i = 0; i < allSectionals.length; i++)
		{
			//current sectionals
			currentSchool = allSectionals[i];
			
			//if the current sectional hosts a regional, then skip over it
			if (currentSchool.hostRegional == true)
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
				}
			}
			
			//add to the regional not caring about size or range
			hostSchools[currentHost].regional.add(currentSchool);		
		}
		
		ArrayList<School> currentRegional; //current regional in the loop
		Regional closestAvailable; //closest available regional
		ArrayList<Regional> ignoredRegionals = new ArrayList<Regional>(0); //list of ignored regionals
		School newSchoolToAdd; //new school to add to/from regionals
		boolean isValid = false; //variable that checks if the current regional is in the range
		
		for(int a = 0; a < hostSchools.length; a++)
		{
			//clear the previous list of ignored regionals and assign the current regional to the
			//one in the iteration
			ignoredRegionals.clear();
			currentRegional = hostSchools[a].regional;
			
			//if there are regionals, use the normal range
			if(shadow == false)
			{
				//while the current regional is under the range
				while(currentRegional.size() < schoolsPerRegionalIncludingRegional)
				{
					//find the closest available regional
					closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					//while the closest available regional is under or equal to the range, ignore it and find a new
					//regional to check
					while(closestAvailable.regional.size() <= schoolsPerRegionalIncludingRegional)
					{
						ignoredRegionals.add(closestAvailable);
						closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					}
					//assign the new school to take from the closest available regional
					//take it from the old one and add it to the new regional
					newSchoolToAdd = findClosestToNewRegionalToTake(currentRegional, closestAvailable);
					closestAvailable.regional.remove(newSchoolToAdd);
					currentRegional.add(newSchoolToAdd);
				}
			
				//while the current regional is over the range (including the host)
				while(currentRegional.size() > schoolsPerRegionalIncludingRegional+1)
				{
					//find the closest available regional
					closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					//while the closest regional is over or equal to the range (including the host)
					//ignore it and find a new regional
					while(closestAvailable.regional.size() >= schoolsPerRegionalIncludingRegional+1)
					{
						ignoredRegionals.add(closestAvailable);
						closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					}
					//assign new school to add to the closest available regional
					//add to the new regional and delete from the old
					newSchoolToAdd = findClosestToNewRegionalToAdd(currentRegional, closestAvailable);
					closestAvailable.regional.add(newSchoolToAdd);
					currentRegional.remove(newSchoolToAdd);
				}
			}
			else
			{
				//while the current regional is under the range
				while(currentRegional.size() < minSize)
				{
					//find the closest available regional
					closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					//while the closest available regional is under or equal to the range
					//ignore it and find the next closest regional
					while(closestAvailable.regional.size() <= schoolsPerRegionalIncludingRegional)
					{
						ignoredRegionals.add(closestAvailable);
						closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					}
					//assign the school to take from the closest available regional
					//add it the current regional and remove from the closest available
					newSchoolToAdd = findClosestToNewRegionalToTake(currentRegional, closestAvailable);
					closestAvailable.regional.remove(newSchoolToAdd);
					currentRegional.add(newSchoolToAdd);
				}
			
				//while the current regional is over the range (including the host)
				while(currentRegional.size() > maxSize+1)
				{
					//find the closest available regional
					closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					//while the closest available is over or equal to the range (including the host)
					//ignore it and find the next closest regional
					while(closestAvailable.regional.size() >= schoolsPerRegionalIncludingRegional+1)
					{
						ignoredRegionals.add(closestAvailable);
						closestAvailable = findClosestRegional(currentRegional.get(0), hostSchools, ignoredRegionals);
					}
					//assign school to the closest school to add to the closest available regional
					//add to the closest available regional and remove from the current
					newSchoolToAdd = findClosestToNewRegionalToAdd(currentRegional, closestAvailable);
					closestAvailable.regional.add(newSchoolToAdd);
					currentRegional.remove(newSchoolToAdd);
				}
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
				}
			}
			
			//add to semi not caring for equal sizes of semis
			hostSchools[currentHost].semi.add(currentSchool);		
		}
		
		ArrayList<School> currentSemi; //the current semi in the iteration
		SemiState closestAvailable; //the closest available semi to the current one
		ArrayList<SemiState> ignoredSemis = new ArrayList<SemiState>(0); //list of ignored semis
		School newSchoolToAdd; //school to add to/from semi
		boolean isValid = false; //boolean that checks to see if semi is in the range of schools
		
		//iterates through all semis
		for(int a = 0; a < hostSchools.length; a++)
		{
			//clears list of previous semis
			ignoredSemis.clear();
			//assigns current semi to the one in the iteration
			currentSemi = hostSchools[a].semi;
			
			//while the current semi is under the range
			while(currentSemi.size() < schoolsPerSemiIncludingSemi)
			{
				//find the closest available semi
				closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				//while the closest available semi is under or equal to the range
				//ignore it and the next closest semi
				while(closestAvailable.semi.size() <= schoolsPerSemiIncludingSemi)
				{
					ignoredSemis.add(closestAvailable);
					closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				}
				//find school to take from the closest available semi
				//add it to the current semi and remove from the closest available
				newSchoolToAdd = findClosestToNewSemiToTake(currentSemi, closestAvailable);
				closestAvailable.semi.remove(newSchoolToAdd);
				currentSemi.add(newSchoolToAdd);
			}
			
			//while the current semi is over the range (including the host)
			while(currentSemi.size() > schoolsPerSemiIncludingSemi+1)
			{
				//find the closest available semi
				closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				//while the closest available semi is over or equal to the range (including the host)
				//ignore it and find the next closest semi
				while(closestAvailable.semi.size() >= schoolsPerSemiIncludingSemi+1)
				{
					ignoredSemis.add(closestAvailable);
					closestAvailable = findClosestSemi(currentSemi.get(0), hostSchools, ignoredSemis);
				}
				//assign to the school to add to the closest available semi
				//add it to the closest available semi and remove from the current semi
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
		
		//assigns the distance from the host school to the distance array
		for(int c = 0; c < distances.length; c++)
		{
			hostSchools[c].distance = distances[c];
		}
		
		//create an array list of the distances and pass over the information to an array list
		ArrayList<Double> listDistances = new ArrayList<Double>();
		for(double d : distances) listDistances.add(d);
				
		//smallest distance from one sectional to the other
		double smallestDistance = Double.MAX_VALUE;
		
		//where the current host is at in the host array
		int currentHost = 0;
		//where the current index is at in the list of distances for the array list
		int index = 0;
		
		//removes any distance from the ignored schools
		for(int j = 0; j < distances.length; j++)
		{	
			if(index <= ignoreHost.size()-1 && ignoreHost.contains(hostSchools[j]))
			{
				listDistances.remove(hostSchools[j].distance);
			}
			
		}
		
		//finds the smallest distance possible
		for(int r = 0; r < distances.length; r++)
		{
			if(r < listDistances.size() && listDistances.get(r) < smallestDistance)
			{
				smallestDistance = listDistances.get(r);
				index = r;
			}
		}
		
		//current index in the distances array
		int arrIndex = 0;
		//loops to find where the school is in terms of indexes from the distance array (which corresponds to hosts)
		//and the array list which has removed hosts
		//finds where the host is at and breaks out of the loop
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
		
		//return the host closest to the sectional
		return hostSchools[currentHost];
	}
	
	public Regional findClosestRegional(School region, Regional[] hostSchools, ArrayList<Regional> ignoreHost)
	{
		//finds all distances from current sectional to others
		double distances[] = region.addDistanceToHost(hostSchools);
		
		//assigns the distance from the host school to the distance array
		for(int c = 0; c < distances.length; c++)
		{
			hostSchools[c].distance = distances[c];
		}
		
		//create an array list of the distances and pass over the information to an array list
		ArrayList<Double> listDistances = new ArrayList<Double>();
		for(double d : distances) listDistances.add(d);
				
		//smallest distance from one regional to the other
		double smallestDistance = Double.MAX_VALUE;
		
		//where the current host is at in the host array
		int currentHost = 0;
		//where the current index is at in the list of distances for the array list
		int index = 0;
		
		//removes any distance from the ignored schools
		for(int j = 0; j < distances.length; j++)
		{	
			if(index <= ignoreHost.size()-1 && ignoreHost.contains(hostSchools[j]))
			{
				listDistances.remove(hostSchools[j].distance);
			}
			
		}
		
		//finds the smallest distance possible
		for(int r = 0; r < distances.length; r++)
		{
			if(r < listDistances.size() && listDistances.get(r) < smallestDistance)
			{
				smallestDistance = listDistances.get(r);
				index = r;
			}
		}
			
		//current index in the distances array
		int arrIndex = 0;
		//loops to find where the school is in terms of indexes from the distance array (which corresponds to hosts)
		//and the array list which has removed hosts
		//finds where the host is at and breaks out of the loop
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
		
		//return the host closest to the regional
		return hostSchools[currentHost];
	}
	
	
	public SemiState findClosestSemi(School semi, SemiState[] hostSchools, ArrayList<SemiState> ignoreHost)
	{
		//finds all distances from current sectional to others
		double distances[] = semi.addDistanceToHost(hostSchools);
		
		//assigns the distance from the host school to the distance array
		for(int c = 0; c < distances.length; c++)
		{
			hostSchools[c].distance = distances[c];
		}
		
		//create an array list of the distances and pass over the information to an array list
		ArrayList<Double> listDistances = new ArrayList<Double>();
		for(double d : distances) listDistances.add(d);
				
		//smallest distance from one regional to the other
		double smallestDistance = Double.MAX_VALUE;
		
		//where the current host is at in the host array
		int currentHost = 0;
		//where the current index is at in the list of distances for the array list
		int index = 0;
		
		//removes any distance from the ignored schools
		for(int j = 0; j < distances.length; j++)
		{	
			if(index <= ignoreHost.size()-1 && ignoreHost.contains(hostSchools[j]))
			{
				listDistances.remove(hostSchools[j].distance);
			}
			
		}
		
		//finds the smallest distance possible
		for(int r = 0; r < distances.length; r++)
		{
			if(r < listDistances.size() && listDistances.get(r) < smallestDistance)
			{
				smallestDistance = listDistances.get(r);
				index = r;
			}
		}

		//current index in the distances array
		int arrIndex = 0;
		//loops to find where the school is in terms of indexes from the distance array (which corresponds to hosts)
		//and the array list which has removed hosts
		//finds where the host is at and breaks out of the loop
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
		
		//return the host closest to the semi
		return hostSchools[currentHost];
	}
	
	//for when sectional is over the limit and you need to add one school to a new sectional
	public School findClosestToNewSectionalToAdd(ArrayList<School> current, Sectional newSectional)
	{
		//smallest distance to travel
		double minDistance = Double.MAX_VALUE;
		//the current distance in the loop
		double currentDistance = 0;
		//the index of where in the current sectional you will add
		int newIndex = 0;
		
		//loop through and find the smallest distance possible
		//assign the index to the variable newIndex
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
		
		//return the school that is closest to add
		return current.get(newIndex);
	}
		
	//for when sectional is under the limit and you need to take one school from a sectional
	public School findClosestToNewSectionalToTake(ArrayList<School> current, Sectional newSectional)
	{
		//smallest distance to travel
		double minDistance = Double.MAX_VALUE;
		//the current distance in the loop
		double currentDistance = 0;
		//the index of where in the current sectional you will add
		int newIndex = 0;
		
		//loop through and find the smallest distance possible
		//assign the index to the variable newIndex
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
		
		//return the school that is closest to add
		return newSectional.sectional.get(newIndex);
	}
	
	//for when regional is over the limit and you need to add one school to a new regional
	public School findClosestToNewRegionalToAdd(ArrayList<School> current, Regional newRegional)
	{
		//smallest distance to travel
		double minDistance = Double.MAX_VALUE;
		//the current distance in the loop
		double currentDistance = 0;
		//the index of where in the current regional you will add
		int newIndex = 0;
		
		//loop through and find the smallest distance possible
		//assign the index to the variable newIndex
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
		
		//return the school that is closest to add
		return current.get(newIndex);
	}
	
	//for when regional is under the limit and you need to take one school from a regional
	public School findClosestToNewRegionalToTake(ArrayList<School> current, Regional newRegional)
	{
		//smallest distance to travel
		double minDistance = Double.MAX_VALUE;
		//the current distance in the loop
		double currentDistance = 0;
		//the index of where in the current regional you will add
		int newIndex = 0;
		
		//loop through and find the smallest distance possible
		//assign the index to the variable newIndex
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
		
		//return the school that is closest to add
		return newRegional.regional.get(newIndex);
	}

	//for when semi is over the limit and you need to add one school to a new semi
	public School findClosestToNewSemiToAdd(ArrayList<School> current, SemiState newSemi)
	{
		//smallest distance to travel
		double minDistance = Double.MAX_VALUE;
		//the current distance in the loop
		double currentDistance = 0;
		//the index of where in the current semi you will add
		int newIndex = 0;
		
		//loop through and find the smallest distance possible
		//assign the index to the variable newIndex
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
		
		//return the school that is closest to add
		return current.get(newIndex);
	}
	
	//for when semi is under the limit and you need to take one school from a semi
	public School findClosestToNewSemiToTake(ArrayList<School> current, SemiState newSemi)
	{
		//smallest distance to travel
		double minDistance = Double.MAX_VALUE;
		//the current distance in the loop
		double currentDistance = 0;
		//the index of where in the current semi you will add
		int newIndex = 0;
		
		//loop through and find the smallest distance possible
		//assign the index to the variable newIndex
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
		
		//return the school that is closest to add
		return newSemi.semi.get(newIndex);
	}

}

