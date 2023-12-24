package com.bridgelabz.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.bridgelabz.enums.CarType;
import com.bridgelabz.enums.Driver;

/*
 * @Description - ParkingAttendant class to park cars and get the location of the parked cars
 * @Properties - parkingLots - list of parking lots
 * 				parkingAttendantName - name of the parking attendant
 */
public class ParkingAttendant {

	List<ParkingLot> parkingLots = new ArrayList<>();
	public String parkingAttendantName;

	/*
	 * @Description - Constructor to initialize the parking lots and parking
	 * attendant name
	 * 
	 * @param - parkingLots - list of parking lots parkingAttendantName - name of
	 * the parking attendant
	 * 
	 * @return - none
	 */
	public ParkingAttendant(List<ParkingLot> parkingLots, String parkingAttendantName) {
		this.parkingLots = new ArrayList<>(parkingLots);
		this.parkingAttendantName = parkingAttendantName;
	}

	/*
	 * @Description - Default Constructor
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
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

	/*
	 * @Description - gets the location of the parked car by color
	 * 
	 * @param - color - color of the car
	 * 
	 * @return - list of locations of the parked cars by color
	 */
	public List<String> locationOfParkedCarsbyColor(String color) {
		List<String> locations = new ArrayList<>();
		for (ParkingLot parkingLot : parkingLots) {
			locations.addAll(parkingLot.getLocationOfParkedbyColor(color));
		}
		return locations;
	}

	/*
	 * @Description - gets the location of the parked car by color and brand
	 * 
	 * @param - color - color of the car brand - brand of the car
	 * 
	 * @return - list of locations of the parked cars by color and brand
	 */
	public List<String> locationOfParkedCarsbyBrandaColor(String color, String brand) {
		List<String> locations = new ArrayList<>();
		for (ParkingLot parkingLot : parkingLots) {
			locations.addAll(parkingLot.getLocationOfParkedbyColorAndBrand(color, brand));
		}
		return locations;
	}

	/*
	 * @Description - gets the location of the parked car by brand
	 * 
	 * @param - brand - brand of the car
	 * 
	 * @return - list of locations of the parked cars by brand
	 */
	public List<String> locationOfParkedCarsByBrand(String brand) {
		List<String> locations = new ArrayList<>();
		for (ParkingLot parkingLot : parkingLots) {
			locations.addAll(parkingLot.getLocationOfParkedCarsByBrand(brand));
		}
		return locations;
	}

	/*
	 * @Description - directs the large car to the parking lot with maximum free
	 * spaces
	 * 
	 * @param - car - car to be parked
	 * 
	 * @return - none
	 */
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

	/*
	 * @Description - gets the cars parked in the last 30 minutes
	 * 
	 * @param - none
	 * 
	 * @return - list of cars parked in the last 30 minutes
	 */
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

	/*
	 * @Description - waits for the given number of minutes
	 * 
	 * @param - minutes - number of minutes to wait
	 * 
	 * @return - none
	 */
	public void waitMinutes(int minutes) {
		try {
			Thread.sleep(minutes * 60 * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/*
	 * @Description - gets the small cars parked on rows B and D
	 * 
	 * @param - none
	 * 
	 * @return - list of small cars parked on rows B and D
	 */
	public List<String> getSmallHandicapCarsOnRowsBorD() {
		List<String> result = new ArrayList<>();

		for (ParkingLot parkingLot : parkingLots) {
			for (Car car : parkingLot.getParkedCars()) {
				if (car.getSize() == CarType.SMALL
						&& (car.getCarRow().charAt(0) == 'B' || car.getCarRow().charAt(0) == 'D')) {
					result.add("Parking Lot: " + parkingLot.hashCode() + ", License Plate: " + car.getLicensePlate());
				}
			}

		}
		return result;
	}

}
