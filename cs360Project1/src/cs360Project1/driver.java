package cs360Project1;

import java.io.IOException;

import com.google.maps.errors.ApiException;

public class driver {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		//MainGUI mainWindow = new MainGUI();
		int a = FileCrawler.length();
		int secNumber = 32;
		
		System.out.println(a);
		
		String[] schoolInput;
		
		schoolInput = FileCrawler.getSchoolArray(a);
		
		
		//System.out.println(input[391]);
		//TODO: make array of sectionals
		School[] schools = SchoolBuilder.getSchools(schoolInput, 392);
		
		int counter = 0;
		Sectional[] testSec = new Sectional[32];
		
		for(int i = 0; i < schools.length; i++)
		{
			if(schools[i].hostSectional == true)
			{
				//System.out.println(schools[i].schoolName);
				testSec[counter] = new Sectional(schools[i]);
				counter++;
			}
			
		}
		
		SectionalSort sort = new SectionalSort();
		
		sort.SectionalSort(schools, testSec, 32);
		int sect = 31;
		for(int j = 0; j < testSec[sect].sectional.size(); j++)
		{
			System.out.print(testSec[sect].sectional.get(j).schoolName);
			System.out.println(" distance: " + Mapping.getDistanceBetween(testSec[sect].sectional.get(j).getLat(), testSec[sect].sectional.get(j).getLng(),
					testSec[sect].getLat(), testSec[sect].getLng()));
		}
		
		
		//double[] arr = schools[1].addDistanceToHost(SchoolBuilder.hostSectionals);
		
		//for(int i = 0; i < SchoolBuilder.hostSectionals.size(); i++){System.out.println(arr[i]);}
	}
	
	
}
