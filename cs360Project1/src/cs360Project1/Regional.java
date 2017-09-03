package cs360Project1;

import java.util.ArrayList;

public class Regional extends Sectional
{
	ArrayList<School> regional = new ArrayList<School>();
	boolean skippedRegional = false;
	public Regional()
	{}
	
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
