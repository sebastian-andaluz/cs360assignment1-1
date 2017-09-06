package cs360Project1;

import java.util.ArrayList;

public class SemiState extends Regional
{
	//an arraylist of the schools in the semi
	ArrayList<School> semi = new ArrayList<School>();
	//if there are no sectionals, semis must still exist for the programs logic. Therefore, this will exist, but never be shown to the user
	boolean skippedSemi = false;

	//default constructor
	public SemiState()
	{}
	//constructor that makes the fields of school (name, etc) as those of the host. then adds the host to the semi-state
	public SemiState(School host)
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
		semi.add(host);
	}
	
}
