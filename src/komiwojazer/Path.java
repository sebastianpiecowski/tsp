package komiwojazer;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private City city1;
	private City city2;
	double length;

	public Path(City city1, City city2, double length) {
		this.city1 = city1;
		this.city2 = city2;
		this.length = length;
	}

	public City getCity1() {
		return city1;
	}

	public City getCity2() {
		return city2;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
}
