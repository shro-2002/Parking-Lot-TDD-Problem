package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description - PoliceDepartment class to get the location of the parked cars for investigation
 * @Properties - parkingAttendant - parking attendant object
 * 
 */
public class PoliceDepartment {
	private ParkingAttendant parkingAttendant;

	/*
	 * @Description - Constructor to initialize the parking attendant
	 * 
	 * @param - parkingAttendant - parking attendant object
	 * 
	 * @return - none
	 */
	public PoliceDepartment(ParkingAttendant parkingAttendant) {
		this.parkingAttendant = parkingAttendant;
	}

	/*
	 * @Description - gets the location of the parked car by color
	 * 
	 * @param - color - color of the car
	 * 
	 * @return - list of locations of the parked cars by color
	 */
	public List<String> getLocationOfParkedColorCars(String color) {
		return parkingAttendant.locationOfParkedCarsbyColor(color);
	}

	/*
	 * @Description - gets the location of the parked car by color and brand
	 * 
	 * @param - color - color of the car brand - brand of the car
	 * 
	 * @return - list of locations of the parked cars by color and brand
	 */
	public List<String> getLocationOfParkedColorAndBrandCars(String color, String brand) {
		List<String> result = new ArrayList<>();
		List<String> locations = parkingAttendant.locationOfParkedCarsbyBrandaColor(color, brand);
		String name = parkingAttendant.parkingAttendantName;
		for (String location : locations) {
			result.add(name + " " + location);
		}
		return result;

	}

	/*
	 * @Description - gets the location of the parked car by brand
	 * 
	 * @param - brand - brand of the car
	 * 
	 * @return - list of locations of the parked cars by brand
	 * 
	 */
	public List<String> getLocationOfParkedBrandCars(String brand) {
		return parkingAttendant.locationOfParkedCarsByBrand(brand);
	}

	/*
	 * @Description - gets the location of the cars parked in last 30 minutes
	 * 
	 * @param - none
	 * 
	 * @return - list of locations of the cars parked in last 30 minutes
	 * 
	 */

	public List<String> getCarsParkedLast30Minutes() {
		return parkingAttendant.carsParkedLast30Minutes();
	}

	/*
	 * @Description - gets the location of the cars parked in rows B or D in parking lot
	 * 
	 * @param - none
	 * 
	 * @return - list of locations of the cars parked in rows B or D in parking lot
	 */
	public List<String> smallHandicapCarsOnRowsBorD() {
		return parkingAttendant.getSmallHandicapCarsOnRowsBorD();
	}

}
