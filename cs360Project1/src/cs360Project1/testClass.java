package cs360Project1;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class testClass {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDgpj6yqpntebQXU_4wTTNqNwoq4X5gz-I").build();
			GeocodingResult[] results =  GeocodingApi.geocode(context,"NorthSide High School").await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(results[0].addressComponents));	
			
			School blah = new School();
	}

}