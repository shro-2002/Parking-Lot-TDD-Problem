package com.bridgelabz.parkinglottest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bridgelabz.enums.CarType;
import com.bridgelabz.enums.Driver;
import com.bridgelabz.model.Car;
import com.bridgelabz.model.ParkingAttendant;
import com.bridgelabz.model.ParkingLot;
import com.bridgelabz.model.PoliceDepartment;

class ParkingAttendantTest {
	PoliceDepartment policeDepartment;
	ParkingLot parkingLot1;
	ParkingLot parkingLot2;
	ParkingAttendant parkingAttendant;

	/*
	 * @Description - sets up the parking lots and parking attendant
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@BeforeEach
	void setUp() throws Exception {
		parkingLot1 = new ParkingLot(5);
		parkingLot2 = new ParkingLot(3);

		parkingAttendant = new ParkingAttendant(List.of(parkingLot1, parkingLot2), "Grayson");
		policeDepartment = new PoliceDepartment(parkingAttendant);
	}

	/*
	 * @Description - tests the large car parking method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testLargeParkingCar() {
		System.out.println("Test Direct Large Car");
		Car largeCar = new Car("WB-12-1238", "Mercedes-Benz GLS", "Black", CarType.LARGE, "A1");

		parkingAttendant.directLargeCar(largeCar, Driver.Handicapped);

		assertTrue(parkingLot1.getParkedCars().contains(largeCar) || parkingLot2.getParkedCars().contains(largeCar));

		System.out.println();
	}

	/*
	 * @Description - tests the location of parked cars by color method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testGetLocationOfParkedWhiteCars() {
		System.out.println("Test Get Location of Parked White Cars");
		Car whiteCar1 = new Car("WB-12-1234", "Toyota", "White", CarType.SMALL, "A1");
		Car whiteCar2 = new Car("WB-12-5678", "Honda", "White", CarType.LARGE, "A2");

		parkingLot1.parkCar(whiteCar1, Driver.Handicapped);
		parkingLot2.parkCar(whiteCar2, Driver.Non_HandiCapped);

		List<String> locations = policeDepartment.getLocationOfParkedColorCars("White");

		assertTrue(locations.contains("Parking Lot " + parkingLot1.hashCode()));
		assertTrue(locations.contains("Parking Lot " + parkingLot2.hashCode()));

		assertEquals(2, locations.size());

		System.out.println();
	}

	/*
	 * @Description - tests the location of parked cars by color and brand method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testGetLocationOfParkedBlueToyotaCars() {
		System.out.println("Test Get Location of Parked Blue Toyota Cars");
		Car blueToyotaCar1 = new Car("WB-12-1234", "Toyota", "Blue", CarType.SMALL, "A1");
		Car blueToyotaCar2 = new Car("WB-12-5678", "Toyota", "Blue", CarType.LARGE, "A2");

		parkingLot1.parkCar(blueToyotaCar1, Driver.Handicapped);
		parkingLot2.parkCar(blueToyotaCar2, Driver.Non_HandiCapped);

		List<String> locations = policeDepartment.getLocationOfParkedColorAndBrandCars("Blue", "Toyota");

		assertTrue(
				locations.contains(parkingAttendant.parkingAttendantName + " Parking Lot " + parkingLot1.hashCode()));
		assertTrue(
				locations.contains(parkingAttendant.parkingAttendantName + " Parking Lot " + parkingLot2.hashCode()));

		assertEquals(2, locations.size());

		System.out.println();
	}

	/*
	 * @Description - tests the location of parked cars by brand method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testGetLocationOfParkedBMW() {
		System.out.println("Test Get Location of Parked BMW Cars");

		Car bmwCar1 = new Car("WB-12-1234", "BMW", "White", CarType.SMALL, "A1");
		Car bmwCar2 = new Car("WB-12-5678", "BMW", "Blue", CarType.MEDIUM, "A2");

		parkingLot1.parkCar(bmwCar1, Driver.Handicapped);
		parkingLot2.parkCar(bmwCar2, Driver.Non_HandiCapped);

		List<String> locations = policeDepartment.getLocationOfParkedBrandCars("BMW");

		assertEquals(2, locations.size());
		assertTrue(locations
				.contains("Parking Lot " + parkingLot1.hashCode() + ", License Plate: " + bmwCar1.getLicensePlate()));
		assertTrue(locations
				.contains("Parking Lot " + parkingLot2.hashCode() + ", License Plate: " + bmwCar2.getLicensePlate()));

	}

	/*
	 * @Description - tests the cars parked in the last 30 minutes method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testGetCarsParkedLast30Minutes() {
		System.out.println("Test Get Cars Parked in the Last 30 Minutes");

		Car car1 = new Car("WB-12-1234", "Toyota", "White", CarType.SMALL, "A1");
		Car car2 = new Car("WB-12-5678", "Honda", "Blue", CarType.MEDIUM, "A2");
	
		parkingLot1.parkCar(car1, Driver.Handicapped);
		parkingLot2.parkCar(car2, Driver.Non_HandiCapped);
	

		parkingAttendant.waitMinutes(30);

		List<String> locations = policeDepartment.getCarsParkedLast30Minutes();

		assertEquals(2, locations.size());
		assertTrue(locations
				.contains("License Plate: " + car1.getLicensePlate() + ", Parking Lot: " + parkingLot1.hashCode()));
		assertTrue(locations
				.contains("License Plate: " + car2.getLicensePlate() + ", Parking Lot: " + parkingLot2.hashCode()));
	}

	@Test
	void testCarsParkedinRowBorD(){
		System.out.println("Test Cars Parked in Row B or D");
		Car car1 = new Car("WB-12-1234", "Toyota", "White", CarType.SMALL, "B1");
		Car car2 = new Car("WB-12-5678", "Honda", "Blue", CarType.SMALL, "D2");


		parkingLot1.parkCar(car1, Driver.Handicapped);
		parkingLot2.parkCar(car2, Driver.Handicapped);


		List<String> locations = policeDepartment.smallHandicapCarsOnRowsBorD();

		assertEquals(2, locations.size());

		assertTrue(locations.contains("Parking Lot: " + parkingLot1.hashCode() + ", License Plate: " + car1.getLicensePlate()));
		assertTrue(locations.contains("Parking Lot: " + parkingLot2.hashCode() + ", License Plate: " + car2.getLicensePlate()));

		
	}

}
