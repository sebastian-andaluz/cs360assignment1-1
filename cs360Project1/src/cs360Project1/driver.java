package cs360Project1;

import java.io.IOException;

import com.google.maps.errors.ApiException;

public class driver {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		//MainGUI mainWindow = new MainGUI();
		int a = FileCrawler.length();
		
		System.out.println(a);
		
		String[] schoolInput;
		
		schoolInput = FileCrawler.getSchoolArray(a);
		
		
		//System.out.println(input[391]);
		
		School[] schools = SchoolBuilder.getSchools(schoolInput, 392);
		
		for(int i = 0; i < schools.length; i++){if(schools[i].hostSectional == true){System.out.println(schools[i].schoolName);}}
		
		
		//double[] arr = schools[1].addDistanceToHost(SchoolBuilder.hostSectionals);
		
		//for(int i = 0; i < SchoolBuilder.hostSectionals.size(); i++){System.out.println(arr[i]);}
	}
	
	
}
