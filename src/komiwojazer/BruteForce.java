package komiwojazer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BruteForce {
	private static List<City> listOfCities = new ArrayList<>();
	private static List<List<Integer>> hamiltonCircuits = new ArrayList<>();
	private static List<Double> lengthOfWay = new ArrayList<>();
	private static List<List<Path>> pathsFromPermutations = new ArrayList<>();
	private static List<Path> bfWay;
	private int idOfBestWay;
	private int idOfWorstWay;

	public BruteForce(List<City> list, int n) {
		bfWay = new ArrayList<>(); //bruteForce ways (length)
		this.listOfCities.addAll(list);
		Permutation perm = new Permutation(n);
		//getting permutations from Permutation.class
		hamiltonCircuits.addAll(perm.getHamiltonCycles());
		//creating paths from permutations
		createPathsForPermutations();
		findBestWay();
		//print for BruteForce algorithm
		System.out.println("Brute Force best way:");
		System.out.println("Path: ");
		for (int i = 0; i < hamiltonCircuits.get(idOfBestWay).size(); i++) {
			System.out.print(hamiltonCircuits.get(idOfBestWay).get(i) + "-");
		}
		System.out.println("total length: " + lengthOfWay.get(idOfBestWay));
		System.out.println("Worst way: ");
		System.out.println("Path: ");
		for (int i = 0; i < hamiltonCircuits.get(idOfWorstWay).size(); i++) {
			System.out.print(hamiltonCircuits.get(idOfWorstWay).get(i) + "-");
		}
		System.out.println("total length: " + lengthOfWay.get(idOfWorstWay));
	}
	private void findBestWay() {
		for (int i = 0; i < pathsFromPermutations.size(); i++) {
			double totalLength = 0;
			for (int j = 0; j < pathsFromPermutations.get(i).size(); j++) {
				totalLength += pathsFromPermutations.get(i).get(j).getLength();
			}
			lengthOfWay.add(totalLength);
		}
		double min = lengthOfWay.get(0);
		double max = lengthOfWay.get(0);
		for (int i = 1; i < lengthOfWay.size(); i++) {
			if (lengthOfWay.get(i) < min) {
				min = lengthOfWay.get(i);
				idOfBestWay = i;
			}
			if (lengthOfWay.get(i) > max) {
				max = lengthOfWay.get(i);
				idOfWorstWay = i;
			}
		}
	}
	private void printAllWay() {
		for (int i = 0; i < pathsFromPermutations.size(); i++) {
			for (int j = 0; j < pathsFromPermutations.get(i).size(); j++) {
				System.out.print("Path: " + pathsFromPermutations.get(i).get(j).getCity1().getId());
				System.out.print(" do: " + pathsFromPermutations.get(i).get(j).getCity2().getId());
				System.out.println(" length: " + pathsFromPermutations.get(i).get(j).getLength());
			}
		}
	}
	private void createPathsForPermutations() {
		for (int i = 0; i < hamiltonCircuits.size(); i++) {
			bfWay.clear();
			for (int j = 0; j < hamiltonCircuits.get(i).size() - 1; j++) {
				Path path = listOfCities.get(hamiltonCircuits.get(i).get(j))
						.getPath(listOfCities.get(hamiltonCircuits.get(i).get(j + 1)));
				bfWay.add(path);
			}
			pathsFromPermutations.add(bfWay);
			
		}
	}

}
