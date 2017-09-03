package cs360Project1;

import java.util.ArrayList;

public class Classification {
	
	int minimumSize;
	int maximumSize;
	
	ArrayList<School> schools = new ArrayList<School>();
	ArrayList<School> hostSchools = new ArrayList<School>();
	
	int sectionalNum;
	int regionalNum;
	int semiNum;
	
	Sectional[] sectionals;
	Regional[] regionals;
	SemiState[] semis;
	
	
	public Classification(int minSize, int maxSize){
		minimumSize = minSize;
		maximumSize = maxSize;
	}
	//add a school to the class
	public void addSchool(School toAdd){
		schools.add(toAdd);	
	}
	public ArrayList<School> getSchools(){
		return schools;
	}
	public void addHost(School toAdd){
		hostSchools.add(toAdd);	
	}
	public ArrayList<School> getHostSchools(){
		return hostSchools;
	}
	//accessers and mutators
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
	public int getClassSize(){
		return schools.size();
	}
	//for sect,reg,semi setSize, return size, and return the array of schools in sect, etc
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
