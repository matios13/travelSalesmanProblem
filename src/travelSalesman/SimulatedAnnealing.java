package travelSalesman;

import static java.lang.Math.E;
import static java.lang.Math.pow;
import static java.lang.Math.random;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {

	private ArrayList<City> bestSolution;
	private ArrayList<City> analizedSolution;
	private double actualTemperature = 100000.0;
	private double coolingTempo = 0.9999;
	private double minimalTemperature = 0.0001;

	Random generator = new Random();

	public SimulatedAnnealing(ArrayList<City> initialSolution) {
		this.analizedSolution = initialSolution;
		this.bestSolution = new ArrayList<City>(analizedSolution);
	}

	private double routeLength(ArrayList<City> route) {

		double length = 0;
		int size = route.size();

		for (int i = 0; i < size - 1; i++) {
			length += route.get(i).distances.get(route.get(i + 1).cityNumber);
		}
		
		length += route.get(size - 1).distances.get(route.get(0).cityNumber);
		
		return length;
	}

	private double probability() {
		double result = pow(
				E,
				-((routeLength(analizedSolution) - routeLength(bestSolution)) / actualTemperature));
		
		return result;
	}

	public void runSimulatedAnnealing() {

		while (actualTemperature > minimalTemperature) {

			swapTwoRandomly();

			if (routeLength(analizedSolution) < routeLength(bestSolution)) {
				bestSolution = new ArrayList<City>(analizedSolution);
			} else if (random() < probability()) {
				bestSolution = new ArrayList<City>(analizedSolution);
			}

			actualTemperature *= coolingTempo;
		}
	}

	private void swapTwoRandomly() {
		int size = bestSolution.size();
		int x = generator.nextInt(size);
		int y;

		do {
			y = generator.nextInt(size);
		} while (x == y);

		City tmp = analizedSolution.get(x);
		analizedSolution.set(x, analizedSolution.get(y));
		analizedSolution.set(y, tmp);
	}

	public ArrayList<City> getBestSolution() {
		for (int j = 0; j < bestSolution.size() - 1; j++) {
			System.out.print(bestSolution.get(j).cityNumber + "-(" + bestSolution.get(j).distances.get(bestSolution.get( j + 1).cityNumber) + ")->");
		}
		
		System.out.println(bestSolution.get(bestSolution.size() - 1).distances.get(bestSolution.get(0).cityNumber));
		return bestSolution;
	}
	
	public double getBestSolutionLength() {
		return routeLength(bestSolution);
	}
	public String bestRoadToString(){
		String out="";
		for(City c : bestSolution){
			out+=c.cityNumber+"->";
		}
		out+=bestSolution.get(0).cityNumber;
		return out;
	}
}
