package cs360Project1;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class testClass {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException 
	{
		//double[] arr = Mapping.getCoordinates("Evansville Mater Dei High School");
		
		System.out.println("" + Mapping.getDistanceBetween(40.7128, -74.0059, 34.052235, -118.243683));
		System.out.println("" + Mapping.DistanceBetweenSchools(40.7128, -74.0059, 34.052235, -118.243683));
		
		

	}
	

}
