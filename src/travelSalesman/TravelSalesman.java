package travelSalesman;

import java.util.ArrayList;

public class TravelSalesman {
	static int NUMBER_OF_CITIES = 4;
	public static final String ANSI_GREEN = "\u001B[32m";
	public static void main(String[] args) {
		GenerateProblem problemGenerator = new GenerateProblem();
		ArrayList<City> listOfCities = problemGenerator.symetricProblemGenerator(NUMBER_OF_CITIES);
		ArrayList<City> listOfCitiesForBruteForce = (ArrayList<City>) listOfCities.clone();
		System.out.print("   ");
		for (int i = 0; i < NUMBER_OF_CITIES; i++) {
			System.out.print("| ");
			if(i<10)
				System.out.print(" ");
			System.out.print(i+" ");
		}
		System.out.println("|");
		for (int i = 0; i < NUMBER_OF_CITIES; i++) {
			System.out.print("______");
		}
		System.out.println();
		for (int i = 0; i < NUMBER_OF_CITIES; i++) {
			City city = listOfCities.get(i);
			if(city.cityNumber<10)
				System.out.print(" ");
			System.out.print(city.cityNumber+" | ");
			for (int j = 0; j < city.distances.size(); j++) {
				if(city.distances.get(j)<10)
					System.out.print(" ");
				System.out.print(city.distances.get(j)+" | ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------------Wy¿a¿anie-----------------------------------------------");
		SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(listOfCities);
		long timeStart = System.currentTimeMillis();
		simulatedAnnealing.runSimulatedAnnealing();
		long timeEnd =System.currentTimeMillis();
		
		System.out.println("Czas symulowanego wy¿arzania: " + (timeEnd-timeStart));
		System.out.println("Dlugosc drogi: " + simulatedAnnealing.getBestSolutionLength());
		System.out.println("Sciezka: "+simulatedAnnealing.bestRoadToString());
		System.out.println("-------------------------------------BruteForce-----------------------------------------------");
		BruteForce bruteForce = new BruteForce();
		timeStart = System.currentTimeMillis();
		System.out.println("Postêp:\n__________________________________________________");
		timeStart = System.currentTimeMillis();
		Route route = bruteForce.calculateBruteForce(listOfCitiesForBruteForce);
		timeEnd = System.currentTimeMillis();
		long wholeTime = (timeEnd - timeStart);
		System.out.print("\n\n czas : " + wholeTime
				+ "\n koszt drogi :" + route.lenght + "\n Droga : ");
		for (int j = 0; j < route.listOfCity.size(); j++) {
			if (j != 0)
				System.out.print("->");
			System.out.print(route.listOfCity.get(j));
		}
	}

}
