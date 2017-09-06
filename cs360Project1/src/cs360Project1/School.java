package cs360Project1;
import java.util.ArrayList;

import com.google.*;
import com.google.maps.GeoApiContext;

public class School {
	String schoolName; //holds the schools name

	int enrollment;	//Enrollment size of school
	int classification; //stores the classification of the school
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
	//TODO:remove double [] distanceToSectionalHost;
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
	//TODO:REMOVE? Need Distance
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
	//mutate and access Name
	public void setName(String newName) {
		schoolName = newName;
	}
	public String getName() {
		return schoolName;
	}
	//mutate and access Enrollment
	public void setEnrollment(int newEnrollment) {
		enrollment = newEnrollment;
	}
	public int getEnrollment() {
		return enrollment;
	}
	//mutate and access Classification
	//TODO: REMOVE?
	public void setClassification(int[] ranges) {
		for (int x=0; x<ranges.length; x++) {
			if (enrollment <= ranges[x])
			{
				classification = x + 1;
				return;
			}
		}
	}
	public void setClassification(int newClassification)
	{
		classification = newClassification;
	}
	public int getClassification() {
		return classification;
	}
	//mutate and access BoyTeam (if one exists/not)
	public void setBoyTeam(boolean newTeam) {
		boyTeam = newTeam;
	}
	public boolean getBoyTeam() {
		return boyTeam;
	}
	//mutate and access GirlTeam (if one exists/not)
	public void setGirlTeam(boolean newTeam) {
		girlTeam = newTeam;
	}
	public boolean getGirlTeam() {
		return girlTeam;
	}
	//mutate and access willingness to host sectional
	public void setWillHostSectional(boolean willing){
		hostSectional = willing;
	}
	public boolean getWillHostSectional(){
		return hostSectional;
	}
	//mutate and access willingness to host regional
	public void setWillHostRegional(boolean willing){
		hostRegional = willing;
	}
	public boolean getWillHostRegional(){
		return hostRegional;
	}
	//mutate and access willingness to host semi-state
	public void setWillHostSemi(boolean willing){
		hostSemi = willing;
	}
	public boolean getWillHostSemi(){
		return hostSemi;
	}
	//mutate and access the identification number for assigned sectional
	public void setSectionalNumber(int number) {
		sectionalNumber = number;
	}
	public int getSectionalNumber() {
		return sectionalNumber;
	}
	//mutate and access the identification number for assigned regional
	public void setRegionalNumber(int number) {
		regionalNumber = number;
	}
	public int getRegionalNumber() {
		return regionalNumber;
	}
	//mutate and access the identification number for assigned semi-state
	public void setSemiNumber(int number) {
		semiNumber = number;
	}
	public int getSemiNumber() {
		return semiNumber;
	}
	//instantiate the ArrayList of distances to sectional hosts
	public void setSectionalDistances(ArrayList<Double> distances) {
		for (int x = 0; x < distances.size(); x++) {
			distanceToSectionalHost.set(x, distances.get(x));
		}
	}
	//access the ArrayList of distances to sectional hosts
	public ArrayList<Double> getSectionalDistances() {
		return distanceToSectionalHost;
	}
	//returns the distance to host school in position x
	public double getSectionalDistances(int x)
	{
		return distanceToSectionalHost.get(x);
	}
	//mutate and access the latitude & longitude of the school
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
	//mutate and access the latitude of the school
	public void setLat(double lat)
	{
		coordinates[0] = lat;
	}
	public double getLat()
	{
		return coordinates[0];
	}
	//mutate and access the longitude of the school
	public void setLng(double lng)
	{
		coordinates[1] = lng;
	}
	public double getLng()
	{
		return coordinates[1];
	}
}