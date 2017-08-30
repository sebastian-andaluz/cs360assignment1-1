package cs360Project1;

import java.io.FileWriter;
import java.io.IOException;

import com.google.maps.errors.ApiException;

public class CalculateDistances 
{

	public static void main(String[] args) throws IOException, ApiException, InterruptedException 
	{
		Mapping location = new Mapping();
		FileWriter fw = new FileWriter("schoolCoordinates.txt");
		int a = FileCrawler.length();
		String[] input;
		double distance;
		
		input = FileCrawler.getSchoolArray(a);
		
		School[] schools = SchoolBuilder.getSchools(input, 392);
		
		for(int i = 0; i < schools.length; i++)
		{
			//System.out.println(schools[i].schoolName + "*" + schools[i].getLat() + "*" + schools[i].getLng());
			//fw.write(schools[i].schoolName + "*" + schools[i].getLat() + "*" + schools[i].getLng() + "\n");
		}
		
//		for(int i = 0; i < schools.length; i++)
//		{
//			for(int j = 0; j < schools.length; j++)
//			{
//				distance = location.getDistanceBetween(schools[i].coordinates[0], schools[i].coordinates[1],
//						 schools[j].coordinates[0], schools[j].coordinates[1]);
//				 
//				fw.write(schools[i].schoolName + " distance to " + schools[j].schoolName + ": " + distance + "\n");
//				System.out.println(schools[i].schoolName + " distance to " + schools[j].schoolName + ": " + distance);
//			}
//			
//		}
//		
		fw.close();
		
		//int[] arr = {1,2,3,4,5,6};
		
		/*for(int i = 0; i < arr.length; i++)
		{
			for(int j = i+1; j < arr.length; j++)
			{
				System.out.print((arr[i]+arr[j]) + ", ");
			}
		}*/
	}
}
