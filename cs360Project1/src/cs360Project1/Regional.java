package cs360Project1;

import java.util.ArrayList;

public class Regional extends Sectional
{
	//an arraylist of the schools that are regional hosts
	ArrayList<School> regional = new ArrayList<School>();
	//if there are no regionals, this means this regional exists only to fulfill the programs structure, but is invisible to the user
	boolean skippedRegional = false;
	//default constructor for Regional
	public Regional()
	{}
	//constructor that instantiates the information(name, etc) as that of the host & adds host to own regional
	public Regional(School host)
	{
		this.schoolName = host.getName();
		this.enrollment = host.getEnrollment();
		this.classification = host.getClassification();
		this.boyTeam = host.getBoyTeam();
		this.girlTeam = host.getGirlTeam();
		
		int sectionalNumber = host.getSectionalNumber();
		int regionalNumber = host.getRegionalNumber();
		int semiNumber = host.getSemiNumber();
		//set the coordinates of the sectional to those of the host school
		coordinates[0] = host.getLat();
		coordinates[1] = host.getLng();
		
		//adds host to its own sectional
		regional.add(host);
	}
	
	
}
