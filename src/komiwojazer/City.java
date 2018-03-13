package komiwojazer;

import java.util.ArrayList;
import java.util.List;

public class City {
	private int id;
	private int xCity;
	private int yCity;
	private List<Path> ways = new ArrayList<>();
	private boolean visited;

	public City(int id, int x, int y) {
		this.id = id;
		xCity = x;
		yCity = y;
		visited = false;
	}

	public void generateWays(int n, List<City> listOfCities) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j || i>j) {
					if (listOfCities.get(i).getCity().equals(this)) {
						Path path = new Path(listOfCities.get(i).getCity(), listOfCities.get(j).getCity(),
								getLengthBetweenCities(listOfCities.get(i).getCity(), listOfCities.get(j).getCity()));
						ways.add(path);
					}
				}
			}
		}
	}

	private static double getLengthBetweenCities(City c1, City c2) {
		double length = Math.pow(c1.getxCity() - c2.getxCity(), 2) + Math.pow(c1.getyCity() - c2.getyCity(), 2);
		return length;
	}

	public City getCity() {
		return this;
	}

	public void setVisit() {
		visited = !visited;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getxCity() {
		return xCity;
	}

	public void setxCity(int xCity) {
		this.xCity = xCity;
	}

	public int getyCity() {
		return yCity;
	}

	public void setyCity(int yCity) {
		this.yCity = yCity;
	}

	public boolean isVisited() {
		return visited;
	}
	public Path getPath(City city) {
		for (int i=0; i<ways.size(); i++) {
			if(ways.get(i).getCity2().equals(city))
				return ways.get(i);
		}
		return null;
	}
	public List<Path> getWays() {
		return ways;
	}

	public void setWays(List<Path> ways) {
		this.ways = ways;
	}
}
