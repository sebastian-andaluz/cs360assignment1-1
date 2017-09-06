package cs360Project1;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class Mapping {
	
	//Takes String and uses Google search to find the latitude and longitude of that location
	public static double[] getCoordinates(String schoolName) throws ApiException, InterruptedException, IOException{
		//array latlon will have the format [lat, lon]
		double[] latlng = new double[2];
		//queries Google API for latitude/longitude of school given
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBgMiAoxARqgj3jtc1jZz6qoUqP_7gce2c").build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,schoolName).await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//assign the returned results into the latlng array
		latlng[0] = results[0].geometry.location.lat;
		latlng[1] = results[0].geometry.location.lng;
		//return the array of latitude/longitude
		return latlng;
	}
	//takes an array of size 2 that contains a latitude and longitude as parameters and returns the distance between them
	public static double getDistanceBetween(double lat1, double lng1, double lat2, double lng2){
		double R = 6371; // radius of the earth, distance is in kilometers
		//calculates radians
		double radians = 3.14159/180;
		//get the latitude and longitude in radians
		double dLat = (radians*lat2 - radians*lat1);
		double dLon = (radians*lng2-radians*lng1);
		lat1 = (lat1)*radians;
		lat2 = (lat2)*radians;
		//uses Haversign formula to calculate distance
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +Math.cos((lat1)) * Math.cos((lat2)) 
			      * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		//second part of Haversign
        	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		//third part of Haversign
        	double distance = R * c;
        	return distance;
	}
}
