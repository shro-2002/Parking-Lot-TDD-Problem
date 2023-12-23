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
		Car largeCar = new Car("WB-12-1238", "Mercedes-Benz GLS", "Black", CarType.LARGE);

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
		Car whiteCar1 = new Car("WB-12-1234", "Toyota", "White", CarType.SMALL);
		Car whiteCar2 = new Car("WB-12-5678", "Honda", "White", CarType.LARGE);

		parkingLot1.parkCar(whiteCar1, Driver.Handicapped);
		parkingLot2.parkCar(whiteCar2, Driver.Non_HandiCapped);

		List<String> locations = policeDepartment.getLocationOfParkedColorCars("White");

		for (String location : locations)
			assertNotNull(location);

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
		Car blueToyotaCar1 = new Car("WB-12-1234", "Toyota", "Blue", CarType.SMALL);
		Car blueToyotaCar2 = new Car("WB-12-5678", "Toyota", "Blue", CarType.LARGE);

		parkingLot1.parkCar(blueToyotaCar1, Driver.Handicapped);
		parkingLot2.parkCar(blueToyotaCar2, Driver.Non_HandiCapped);

		List<String> locations = policeDepartment.getLocationOfParkedColorAndBrandCars("Blue", "Toyota");

		for (String location : locations)
			assertNotNull(location);

		assertEquals(2, locations.size());

		System.out.println();
	}
}
