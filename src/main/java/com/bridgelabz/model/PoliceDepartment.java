package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.List;

public class PoliceDepartment {
	private ParkingAttendant parkingAttendant;

	public PoliceDepartment(ParkingAttendant parkingAttendant) {
		this.parkingAttendant = parkingAttendant;
	}

	public List<String> getLocationOfParkedColorCars(String color) {
		return parkingAttendant.locationOfParkedCarsbyColor(color);
	}

	public List<String> getLocationOfParkedColorAndBrandCars(String color, String brand) {
		List<String> result = new ArrayList<>();
		List<String> locations = parkingAttendant.locationOfParkedCarsbyBrandaColor(color, brand);
		String name= parkingAttendant.parkingAttendantName;
		for (String location : locations) {
			result.add(name + " " + location);
		}
		return result;

	}
	
    public List<String> getLocationOfParkedBrandCars(String brand) {
        return parkingAttendant.locationOfParkedCarsByBrand(brand);
    }
    
    public List<String> getCarsParkedLast30Minutes() {
        return parkingAttendant.carsParkedLast30Minutes();
    }

	public List<String> smallHandicapCarsOnRowsBorD(){
		return parkingAttendant.getSmallHandicapCarsOnRowsBorD();
	}

}
