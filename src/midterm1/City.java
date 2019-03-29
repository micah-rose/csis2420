package midterm1;

public class City implements Comparable<City>{
	
	private final String name;
	private final int population;
	private final int area;
	//private int density;
	
	public City(String name, int population, int area) {
		this.name = name;
		this.population = population;
		this.area = area;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public int getArea() {
		return area;
	}

	@Override
	public String toString() {
		return String.format("%-15s density: %.1f", name, (double)population/area);
	}

	public int compareTo(City compareCity) {
		int compareDensity = ((City) compareCity).getPopulation()/((City) compareCity).getArea(); 
		
		//ascending order
		return (this.population/this.area) - compareDensity;
		
		//descending order
		//return compareDensity - (this.population/this.area);
	}
}