package com.bridgelabz.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingAttendant {

	List<ParkingLot> parkingLots = new ArrayList<>();
	
    public ParkingAttendant(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

	public ParkingAttendant() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * @Description - parks the car in the parking lot
	 * 
	 * @param - car - car to be parked
	 * 
	 * @return - none
	 */
	public void parkCar(ParkingLot parklot, Car car, Driver driver) {

		parklot.parkCar(car, driver);

	}

	public void directLargeCar(Car car, Driver driver) {

		ParkingLot selectedParkingLot = parkingLots.stream()
				.max(Comparator.comparingInt(ParkingLot::getFreeSpaces))
				.orElse(null);

		if (selectedParkingLot != null) {
			parkCar(selectedParkingLot, car, driver);
			System.out.println("Large car directed to Parking Lot with capacity " + selectedParkingLot.capacity);
		} else {
			System.out.println("No suitable parking lot found for the large car.");
		}

	}

}
