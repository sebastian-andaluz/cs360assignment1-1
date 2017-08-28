package cs360Project1;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class SchoolBuilder {
	
	School[] schools;
	int a = 0;
	String temp;
	String delimiter;
	StringTokenizer splitter;
	
	public School[] getSchools(String[] input, int length){
				
		schools = new School[length];
		delimiter = "*";
		for(int i = 0; i < length; i++){
			
			splitter = new StringTokenizer(input[i], delimiter);
			schools[i].schoolName = splitter.nextToken();
			schools[i].enrollment = Integer.parseInt(splitter.nextToken());
			
			//
			
			temp = splitter.nextToken();
			if(temp == "T"){
				schools[i].boyTeam=true;
			}
			else{
				schools[i].boyTeam=false;
			}
			
			//
			
			temp = splitter.nextToken();
			if(temp == "T"){
				schools[i].girlTeam=true;
			}
			else{
				schools[i].girlTeam=false;
			}
			
			//
			
			temp = splitter.nextToken();
			if(temp == "T"){
				schools[i].hostSectional=true;
			}
			else{
				schools[i].hostSectional=false;
			}
			
			//
			
			temp = splitter.nextToken();
			if(temp == "T"){
				schools[i].hostRegional=true;
			}
			else{
				schools[i].hostRegional=false;
			}
		}
		
		
		return schools;
		
	}
	
	
}