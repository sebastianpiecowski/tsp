package komiwojazer;

import java.util.ArrayList;
import java.util.List;

public class NN {
	static List<City> listOfCities = new ArrayList<>();
	static List<Path> nnWay = new ArrayList<>();
	static int totalLength = 0;
	static int lastCity=0;
	public NN(List<City> cityList) {
		listOfCities.addAll(cityList);
		findWay();
		printBestPaths();
	}

	private static void printBestPaths() {
		System.out.println("NN best way: ");
		for (int i = 0; i < nnWay.size(); i++) {
			System.out.println("Path: " + nnWay.get(i).getCity1().getId() + " do " + nnWay.get(i).getCity2().getId()
					+ " length: " + nnWay.get(i).getLength());
		}
		System.out.println("Total length: "+totalLength);
	}

	private static void findWay() {

		int bestPath = 0;
		int connections = 0;
		int cityID = 0;
		City currCity = listOfCities.get(0);
		listOfCities.get(0).setVisit();
		while (connections < listOfCities.size() - 1) {
			double length = 1000;
			for (int i = 0; i < currCity.getWays().size(); i++) {
				if (currCity.getWays().get(i).getCity2().isVisited() == false) {
					if (currCity.getWays().get(i).getLength() < length) {
						length = currCity.getWays().get(i).getLength();
						cityID = currCity.getWays().get(i).getCity2().getId();
						bestPath = i;
					}
				}
			}
			listOfCities.get(cityID).setVisit();
			nnWay.add(currCity.getWays().get(bestPath));
			currCity = listOfCities.get(cityID);
			totalLength += length;
			if(connections==listOfCities.size()-2)
				lastCity=currCity.getWays().get(bestPath).getCity1().getId();
			bestPath = 0;
			cityID = 0;

			connections++;
		}
		currCity=listOfCities.get(lastCity);
		for (int i = 0; i < currCity.getWays().size(); i++) {
			if (currCity.getWays().get(i).getCity2().getId() == 0) {
				nnWay.add(currCity.getWays().get(i));
				totalLength += nnWay.get(nnWay.size()-1).getLength();
			}
		}
	}
}