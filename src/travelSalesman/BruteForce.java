package travelSalesman;

import java.util.ArrayList;

public class BruteForce {
	ArrayList<City> listOfCities;

	public Route calculateBruteForce(ArrayList<City> citiesList) {

		long numberOfPerms = numberOfPermCalculator(citiesList.size() - 1);
		listOfCities = citiesList;
		Route route = new Route();
		route.lenght = 2147483647;
		int oneOfFifty = (int)((double)numberOfPerms/50.0);
		for (long i = 0; i < numberOfPerms; ++i) {

			int lenght = 0;
			if (oneOfFifty!=0 &&i % oneOfFifty == 0) {
				System.out.print(">");
			}

			for (int j = 0; j < listOfCities.size(); j++) {

				int beginCityNumber = listOfCities.get(j).cityNumber;

				if (j == listOfCities.size() - 1) {
					int distance;
					distance= listOfCities.get(j).distances.get(0);
					lenght +=distance;
				} else {
					int endCity = listOfCities.get(j+1).cityNumber;
					int distance;
					distance= listOfCities.get(j).distances.get(endCity);
					lenght +=distance;

				}
			}
			System.out.println();
			if (lenght < route.lenght) {
				route.lenght = lenght;
				route.listOfCity = new ArrayList<Integer>();
				for (int j = 0; j < listOfCities.size(); j++) {
					route.listOfCity.add(listOfCities.get(j).cityNumber);
				}
			}

			getNextPerm();
		}
		return route;
	}

	void getNextPerm() {
		int N = listOfCities.size();
		int i = N - 1;
		while (listOfCities.get(i - 1).cityNumber >= listOfCities.get(i).cityNumber)
			i = i - 1;

		int j = N;
		while (listOfCities.get(j - 1).cityNumber <= listOfCities.get(i - 1).cityNumber)
			j = j - 1;

		swap(i - 1, j - 1);

		i++;
		j = N;
		while (i < j) {
			swap(i - 1, j - 1);
			i++;
			j--;
		}
	}

	void swap(int x, int y) {
		City cityY = listOfCities.get(y);
		City cityX = listOfCities.get(x);
		int tempNumber = cityX.cityNumber;
		ArrayList<Integer> tempList = cityX.distances;
		cityX.cityNumber = cityY.cityNumber;
		cityX.distances = cityY.distances;
		cityY.cityNumber = tempNumber;
		cityY.distances = tempList;
	}

	long numberOfPermCalculator(long number) {

		if (number < 1)
			return 1;
		else
			return number * numberOfPermCalculator(number - 1);

	}
}
