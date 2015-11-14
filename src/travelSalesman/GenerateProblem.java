package travelSalesman;

import java.util.ArrayList;
import java.util.Random;

public class GenerateProblem {

	public ArrayList<City> asymetricProblemGenerator(int numberOfCities){
		Random generator = new Random();
		ArrayList<City> listOfCity=new ArrayList<City>();
		for(int i=0;i<numberOfCities;++i){
			
			ArrayList<Integer> distances= new ArrayList<Integer>();
			
			for(int j=0;j<=numberOfCities;++j){
				distances.add((i != j)?generator.nextInt(20) + 1 : 0);
				System.out.println("Z miasta " + i + " do " + j + " za: " + distances.get(j));
			}
			listOfCity.add(new City(distances,i));
			
		}
		return listOfCity;
	}
	
	
	public ArrayList<City> symetricProblemGenerator(int numberOfCities){
		Random generator = new Random();
		ArrayList<City> listOfCity=new ArrayList<City>();
		for(int i=0;i<numberOfCities;++i){
			
			ArrayList<Integer> distances= new ArrayList<Integer>();
			System.out.println("Z miasta : "+i);
			for(int j=0;j<numberOfCities;++j){
				distances.add((i != j)?((j < i)?listOfCity.get(j).distances.get(i) : generator.nextInt(20) + 1) : 0);
				System.out.println("                  ->"+j+" : "+distances.get(j));
			}
			listOfCity.add(new City(distances,i));
			
		}
		return listOfCity;
	}
}
