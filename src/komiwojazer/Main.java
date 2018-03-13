package komiwojazer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static List<City> listOfCities = new ArrayList<>();
	private static List<Path> listOfPaths = new ArrayList<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("Podaj ilosc miast: ");
		int n = sc.nextInt();
		generateCities(n);
		generatePathsBetweenCities(n);
		printPaths();
		//nearest Neighbour algorythm
		long startTime = System.currentTimeMillis();
		new NN(listOfCities);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("NN algorytm time: "+estimatedTime);
		startTime=System.currentTimeMillis();
		//BruteForce algorythm
		BruteForce bf=new BruteForce(listOfCities, n);
		estimatedTime=System.currentTimeMillis()-startTime;
		System.out.println("BruteForce algoryth time:"+estimatedTime);
		
	}
	//generate new city
	private static void generateCities(int n) {
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			City newCity = new City(i, rand.nextInt(20), rand.nextInt(20));
			listOfCities.add(newCity);
		}
	}
	
	//generate in specific city connection to other
	private static void generatePathsBetweenCities(int n) {
		for (int i = 0; i < n; i++) {
			listOfCities.get(i).generateWays(n, listOfCities);
		}
	}
	
	//print path between cities
	private static void printPaths() {
		for (int i = 0; i < listOfCities.size(); i++) {
			for (int j = 0; j < listOfCities.get(i).getWays().size(); j++) {
				System.out.println("City :" + listOfCities.get(i).getWays().get(j).getCity1().getId() + " to "
						+ listOfCities.get(i).getWays().get(j).getCity2().getId() + " len: "
						+ listOfCities.get(i).getWays().get(j).getLength());
			}
		}
	}

}
