package com.bridgelabz.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.bridgelabz.enums.Driver;

public class ParkingAttendant {

	List<ParkingLot> parkingLots = new ArrayList<>();
	public String parkingAttendantName;

	public ParkingAttendant(List<ParkingLot> parkingLots, String parkingAttendantName) {
		this.parkingLots = new ArrayList<>(parkingLots);
		this.parkingAttendantName = parkingAttendantName;
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

	public List<String> getLocationOfParkedCarsbyColor(String color) {
		List<String> locations = new ArrayList<>();
		for (ParkingLot parkingLot : parkingLots) {
			locations.addAll(parkingLot.getLocationOfParkedbyColor(color));
		}
		return locations;
	}

	public List<String> getLocationOfParkedCarsbyBrandaColor(String color, String brand) {
		List<String> locations = new ArrayList<>();
		for (ParkingLot parkingLot : parkingLots) {
			locations.addAll(parkingLot.getLocationOfParkedbyColorAndBrand(color, brand));
		}
		return locations;
	}

	public List<String> getLocationOfParkedCarsByBrand(String brand) {
		List<String> locations = new ArrayList<>();
		for (ParkingLot parkingLot : parkingLots) {
			locations.addAll(parkingLot.getLocationOfParkedCarsByBrand(brand));
		}
		return locations;
	}

	public void directLargeCar(Car car, Driver driver) {

		ParkingLot selectedParkingLot = parkingLots.stream().max(Comparator.comparingInt(ParkingLot::getFreeSpaces))
				.orElse(null);

		if (selectedParkingLot != null) {
			parkCar(selectedParkingLot, car, driver);
			System.out.println("Large car directed to Parking Lot with capacity " + selectedParkingLot.capacity);
		} else {
			System.out.println("No suitable parking lot found for the large car.");
		}

	}

	public List<String> carsParkedLast30Minutes() {
		List<String> carsParkedLast30Minutes = new ArrayList<>();
		LocalTime currentTime = LocalTime.now();

		for (ParkingLot parkingLot : parkingLots) {
			for (Car car : parkingLot.getParkedCars()) {
				if (Duration.between(car.getParkTime(), currentTime).toMinutes() <= 30) {
					carsParkedLast30Minutes
							.add("License Plate: " + car.getLicensePlate() + ", Parking Lot: " + parkingLot.hashCode());
				}
			}
		}

		return carsParkedLast30Minutes;
	}

}
