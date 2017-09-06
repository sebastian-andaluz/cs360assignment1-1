package cs360Project1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.google.maps.errors.ApiException;

public class SchoolBuilder {
	//creates array lists for all schools, sectionals, regionals, and semis
	static School[] schools;
	static ArrayList<School> hostSectionals = new ArrayList<School>();
	static ArrayList<School> hostRegionals = new ArrayList<School>();
	static ArrayList<School> hostSemis = new ArrayList<School>();
	public static School[] getSchools(String[] input, int length) throws ApiException, InterruptedException, IOException{
		
		//creates map object to get distances
		Mapping map = new Mapping();
		int a = 0;
		String temp; //temporary variable to assign to a school field
		String delimiter; //what breaks up the array to read the next variable
		StringTokenizer splitter; //breaks up array to read the next variable
				
		schools = new School[length];
		delimiter = "*"; //what the delimeter is
		//loops through all the schools
		for(int i = 0; i < length; i++){
			//temporary school to read info
			School tempSchool = new School();
			schools[i] = tempSchool;
			//reads the name and enrollment
			splitter = new StringTokenizer(input[i], delimiter);
			schools[i].schoolName = splitter.nextToken();
			schools[i].enrollment = Integer.parseInt(splitter.nextToken());
			//schools[i].setCoordinates(map.getCoordinates(schools[i].schoolName));
			//System.out.println(i);
			//System.out.println(schools[i].schoolName);
			//System.out.print(schools[i].coordinates[0] + " " + schools[i].coordinates[1] + "\n\n");
			
			//
			
			//reads whether or not there is a boy team
			temp = splitter.nextToken();
			if(temp.equalsIgnoreCase("T")){
				schools[i].boyTeam=true;
			}
			else{
				schools[i].boyTeam=false;
			}
			
			
			//reads whether or not there is a girl team
			temp = splitter.nextToken();
			if(temp.equalsIgnoreCase("T")){
				schools[i].girlTeam=true;
			}
			else{
				schools[i].girlTeam=false;
			}
			
			
			//reads whether or not school is a sectional
			temp = splitter.nextToken();
			if(temp.equalsIgnoreCase("T"))
			{
				schools[i].hostSectional=true;
				hostSectionals.add(schools[i]);
			}
			else
			{
				schools[i].hostSectional=false;
			}
			
			
			//reads whether or not school is a regional
			temp = splitter.nextToken();
			if(temp.equalsIgnoreCase("T")){
				schools[i].hostRegional=true;
				hostRegionals.add(schools[i]);
			}
			else{
				schools[i].hostRegional=false;
			}
			
			//reads whether or not school is a semi
			temp = splitter.nextToken();
			if(temp.equalsIgnoreCase("T")){
				schools[i].hostSemi=true;
				hostSemis.add(schools[i]);
			}
			else{
				schools[i].hostSemi=false;
			}
			
			//reads latitude of school
			schools[i].setLat(Double.parseDouble(splitter.nextToken()));
			//reads longitude of school
			schools[i].setLng(Double.parseDouble(splitter.nextToken()));
		}
		
		
		return schools;
		
	}
	
	//grabs the array list of sectionals
	public ArrayList<School> getSectionalHosts()
	{
		return hostSectionals;
	}
	
	//grabs the array list of regionals
	public ArrayList<School> getRegionalHosts()
	{
		return hostRegionals;
	}
	
	//grabs the array list of semis
	public ArrayList<School> getSemiHosts()
	{
		return hostSemis;
	}
	
}
