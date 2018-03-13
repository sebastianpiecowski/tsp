package komiwojazer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutation {
	private List<List<Integer>> listOfPermutations;
	private int[] input;

	public Permutation(int n)  {
		listOfPermutations = new ArrayList<>();
		input=new int[n];
		for(int i=0; i<input.length; i++) {
			input[i]=i;
		}
		listOfPermutations = permute(input);
		cleanList();
		addEndPoint();
	}

	private void print() {
		System.out.println("START");
		for (int i=0; i<listOfPermutations.size(); i++) {
			for (int j=0; j<listOfPermutations.get(i).size(); j++)
				System.out.print(listOfPermutations.get(i).get(j));
			System.out.println();
		}
		System.out.println("END");
	}
	
	private List<List<Integer>> permute(int[] num) {
		List<List<Integer>> result = new ArrayList<>();
		if (num == null || num.length < 0)
			return result;
		int len = num.length;
		List visited = new LinkedList<>();
		dfsList(len, num, visited, result);
		return result;
	}

	private void dfsList(int len, int[] num, List visited, List<List<Integer>> result) {
		if (visited.size() == len) {
			result.add(new ArrayList<>(visited));
			return;
		}
		for (int i = 0; i < len; i++) {
			if (!visited.contains(num[i])) {
				visited.add(num[i]);
				dfsList(len, num, visited, result);
				visited.remove(visited.size() - 1);
			}
		}
	}
	private void cleanList() {
		List<Integer> temporary=new ArrayList<>();
		for (int i=0; i<listOfPermutations.size(); i++) {
			temporary=listOfPermutations.get(i);
			for (int start = 0, end = temporary.size() - 1; start <= end; start++, end--) {
			    int aux = temporary.get(start);
			    temporary.set(start, temporary.get(end));
			    temporary.set(end, aux);
			}
			if(listOfPermutations.contains(temporary)) {
				listOfPermutations.remove(listOfPermutations.indexOf(temporary));
			}
		}
	}
	private void addEndPoint() {
		for (int i=0; i<listOfPermutations.size(); i++) {
			listOfPermutations.get(i).add(listOfPermutations.get(i).get(0));
		}
	}
	public List<List<Integer>> getHamiltonCycles() {
		return listOfPermutations;
	}
}