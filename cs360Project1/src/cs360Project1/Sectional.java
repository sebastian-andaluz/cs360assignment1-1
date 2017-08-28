package cs360Project1;

public class Sectional extends School {
	
	School[] sectional;
	
	
	public Sectional (School host, School[] memberSchools)
	{
		this.schoolAddress = host.getAddress();
		sectional = memberSchools;
	}
	
	public Sectional ()
	{}
	
	
}
