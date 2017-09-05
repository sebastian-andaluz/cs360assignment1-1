package cs360Project1;

import java.util.ArrayList;

public class Classification {
	
	int minimumSize;
	int maximumSize;
	
	ArrayList<School> schools = new ArrayList<School>();
	ArrayList<School> sectHostSchools = new ArrayList<School>();
	ArrayList<School> regHostSchools = new ArrayList<School>();
	ArrayList<School> semiHostSchools = new ArrayList<School>();
	
	int sectionalNum;
	int regionalNum;
	int semiNum;
	
	Sectional[] sectionals;
	Regional[] regionals;
	SemiState[] semis;
	
	//Constructor for a Classification
	public Classification(int minSize, int maxSize){
		minimumSize = minSize;
		maximumSize = maxSize;
	}
	
	//add and get schools in the class
	public void addSchool(School toAdd){
		schools.add(toAdd);	
	}
	public ArrayList<School> getSchools(){
		return schools;
	}
	//add and get sectional host schools
	public void addSectionalHost(School toAdd){
		sectHostSchools.add(toAdd);	
	}
	public ArrayList<School> getSectionalHostSchools(){
		return sectHostSchools;
	}
	//add and get regional host schools
	public void addRegionalHost(School toAdd){
		regHostSchools.add(toAdd);	
	}
	public ArrayList<School> getRegionalHostSchools(){
		return regHostSchools;
	}
	//add and get semi-state host schools
	public void addSemiStateHost(School toAdd){
		semiHostSchools.add(toAdd);	
	}
	public ArrayList<School> getSemiStateHostSchools(){
		return semiHostSchools;
	}
	//mutate minimum and maximum enrollment size of a class
	public void setMinimum(int newMin){
		minimumSize = newMin;
	}
	public int getMinimum(){
		return minimumSize;
	}
	public void setMaximum(int newMax){
		maximumSize = newMax;
	}
	public int getMaximum(){
		return maximumSize;
	}
	//returns the number of schools in the class
	public int getClassSize(){
		return schools.size();
	}
	//for sectional setSize, return size, and return the array of schools in sect
	public void setSectionalNum(int size){
		sectionalNum = size;
		sectionals = new Sectional[size];
	}
	public int getSectionalNum(){
		return sectionalNum;
	}
	public Sectional[] getSectionals(){
		return sectionals;
	}
	//for regional setSize, return size, and return the array of schools in sect
	public void setRegionalNum(int size){
		regionalNum = size;
		regionals = new Regional[size];
	}
	public int getRegionalNum(){
		return regionalNum;
	}
	public Sectional[] getRegionals(){
		return regionals;
	}
	//for semi-state setSize, return size, and return the array of schools in sect
	public void setSemiNum(int size){
		semiNum = size;
		semis = new SemiState[size];
	}
	public int getSemiNum(){
		return semiNum;
	}
	public Sectional[] getsemis(){
		return semis;
	}	
}
