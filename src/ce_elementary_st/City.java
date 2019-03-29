package ce_elementary_st;

public class City {
	
	private String city;
	private String state;
	
	public City (String city, String state) {
		this.city = city;
		this.state = state;
	}
	
	@Override
	public String toString()
	{
		return city + "(" + state + ")";
	}

}
