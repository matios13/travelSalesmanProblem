package travelSalesman;

import java.util.ArrayList;

public class City {
	ArrayList<Integer> distances;
	int cityNumber;
	
	public City(){

	}
	public City(ArrayList<Integer> distances, int citynumber){
		this.distances=distances;
		this.cityNumber=citynumber;
	}
	
	public City(int citynumber){
		this.cityNumber=citynumber;
	}
}
