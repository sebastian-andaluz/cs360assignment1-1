package cs360Project1;
import java.util.ArrayList;

import com.google.*;
import com.google.maps.GeoApiContext;

public class School {
	String schoolName;

	int enrollment;	//Enrollment size of school
	int classification;
	//T/F There exists a boys/girls team
	boolean boyTeam;
	boolean girlTeam;
	//T/F Willing to Host on Level
	boolean hostSectional;
	boolean hostRegional;
	boolean hostSemi;
	//Organizational number of sect/reg/semi school assigned to
	int sectionalNumber;
	int regionalNumber;
	int semiNumber;
	//array of distances to hosts of sectional
	////////double [] distanceToSectionalHost;
	ArrayList<Double> distanceToSectionalHost = new ArrayList<Double>();
	
	double[] coordinates = new double[2];
	
	
	//default constructor
	public School(){
	}
	
	//Constructor for Schools using information from text file
	public School(String name, int newEnrollment, boolean boyExists, 
			boolean girlExists, boolean sectionalHost, boolean regionalHost, boolean semiHost){
		name = schoolName;
		enrollment = newEnrollment;
		boyTeam = boyExists;
		girlTeam = girlExists;
		hostSectional = sectionalHost;
		hostRegional = regionalHost;
		hostSemi = semiHost;
	}
	//TODO: Need Distance
	public void sectHostDistances(ArrayList<School> sectionalHosts){//School[] sectionalHosts){
		//distanceToSectionalHost = new double[sectionalHosts.length];
		distanceToSectionalHost = new ArrayList<Double>(sectionalHosts.size());
		for (int counter = 0; counter <= sectionalHosts.size(); counter++){
			//TODO: Needs calcDistanceMethod
			double distance = 0;
			//distance = distanceMethod(school, hostSchool);
			////distanceToSectionalHost[counter] = distance;
			
			distanceToSectionalHost.add(counter, distance);
		}
	}
	/*
	 * Working method of distance to Host
	 */
	public double[] addDistanceToHost(School[] hostSchools)
	{
		double[] distances = new double[hostSchools.length];
		
		for(int j = 0; j < hostSchools.length; j++)
		{
			distances[j] = Mapping.getDistanceBetween(this.getLat(), this.getLng(), 
					hostSchools[j].getLat(), hostSchools[j].getLng());
		}
		return distances;
	}
	
	public void setName(String newName) {
		schoolName = newName;
	}
	
	public String getName() {
		return schoolName;
	}

	public void setEnrollment(int newEnrollment) {
		enrollment = newEnrollment;
	}
	public int getEnrollment() {
		return enrollment;
	}
	public void setClassification(int[] ranges) {
		for (int x=0; x<ranges.length; x++) {
			if (enrollment <= ranges[x])
			{
				classification = x + 1;
				return;
			}
		}
	}
	public int getClassification() {
		return classification;
	}
	public void setBoyTeam(boolean newTeam) {
		boyTeam = newTeam;
	}
	public boolean getBoyTeam() {
		return boyTeam;
	}
	public void setGirlTeam(boolean newTeam) {
		girlTeam = newTeam;
	}
	public boolean getGirlTeam() {
		return girlTeam;
	}
	public void setWillHostSectional(boolean willing){
		hostSectional = willing;
	}
	public boolean getWillHostSectional(){
		return hostSectional;
	}
	public void setWillHostRegional(boolean willing){
		hostRegional = willing;
	}
	public boolean getWillHostRegional(){
		return hostRegional;
	}
	public void setWillHostSemi(boolean willing){
		hostSemi = willing;
	}
	public boolean getWillHostSemi(){
		return hostSemi;
	}
	public void setSectionalNumber(int number) {
		sectionalNumber = number;
	}
	public int getSectionalNumber() {
		return sectionalNumber;
	}
	public void setRegionalNumber(int number) {
		regionalNumber = number;
	}
	public int getRegionalNumber() {
		return regionalNumber;
	}
	public void setSemiNumber(int number) {
		semiNumber = number;
	}
	public int getSemiNumber() {
		return semiNumber;
	}
	public void setSectionalDistances(ArrayList<Double> distances) {
		for (int x = 0; x < distances.size(); x++) {
			//distanceToSectionalHost[x] = distances[x];
			distanceToSectionalHost.set(x, distances.get(x));
		}
	}
	public ArrayList<Double> getSectionalDistances() {
		return distanceToSectionalHost;
	}
	//returns the distance to host school in position x
	public double getSectionalDistances(int x)
	{
		return distanceToSectionalHost.get(x);
	}
	
	public void setCoordinates(double[] coordinates)
	{
		for(int i = 0; i < coordinates.length; i++)
		{
			this.coordinates[i] = coordinates[i];
		}
	}
	
	public double[] getCoordinates()
	{
		return coordinates;
	}
	
	public void setLat(double lat)
	{
		coordinates[0] = lat;
	}
	
	public void setLng(double lng)
	{
		coordinates[1] = lng;
	}
	
	public double getLat()
	{
		return coordinates[0];
	}
	
	public double getLng()
	{
		return coordinates[1];
	}

}