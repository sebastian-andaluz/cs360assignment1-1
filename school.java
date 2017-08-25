
public class school {
	String schoolName;
	String schoolAddress;
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
	double [] distanceToSectionalHost;
	
	//Constructor for Schools using information from text file
	public school(String name, int newEnrollment, boolean boyExists, 
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
	public void sectHostDistances(school[] sectionalHosts){
		distanceToSectionalHost = new double[sectionalHosts.length];
		for (int counter = 0; counter <= sectionalHosts.length; counter++){
			//TODO: Needs calcDistanceMethod
			int distance = 0;
			//distance = distanceMethod(school, hostSchool);
			distanceToSectionalHost[counter] = distance;
			
		}
	}
	//address accessers
	public void setAddress(String address){
		schoolAddress = address;
	}
	public String getAddress(){
		return schoolAddress;
	}
}