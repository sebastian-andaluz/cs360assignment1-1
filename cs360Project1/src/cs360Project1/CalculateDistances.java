package cs360Project1;

import java.io.FileWriter;
import java.io.IOException;

import com.google.maps.errors.ApiException;

public class CalculateDistances 
{

	public static void main(String[] args) throws IOException, ApiException, InterruptedException 
	{
		Mapping location = new Mapping();
		//Create new FileWriter that reads the file containing the schools names & lat/long
		FileWriter fw = new FileWriter("schoolCoordinates.txt");
		int a = FileCrawler.length();
		String[] input;
		double distance;
		
		input = FileCrawler.getSchoolArray(a);
		//creates an array of school objects
		School[] schools = SchoolBuilder.getSchools(input, 392);
		
		//TODO: Remove old code?
		for(int i = 0; i < schools.length; i++)
		{
			//System.out.println(schools[i].schoolName + "*" + schools[i].getLat() + "*" + schools[i].getLng());
			//fw.write(schools[i].schoolName + "*" + schools[i].getLat() + "*" + schools[i].getLng() + "\n");
		}
		fw.close();
	}
}
