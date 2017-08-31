package cs360Project1;

import java.util.ArrayList;

public class Sectional extends School {
	
	ArrayList<School> sectional = new ArrayList<School>();
	int counter = 0;
	
	public Sectional (School host)
	{
		//set the inherited qualities with the qualities of the host school
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
		sectional.add(host);
	}
	
	public Sectional ()
	{}
	
	
}
