package com.bridgelabz.parkinglottest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bridgelabz.model.Car;
import com.bridgelabz.model.CarType;
import com.bridgelabz.model.Driver;
import com.bridgelabz.model.ParkingAttendant;
import com.bridgelabz.model.ParkingLot;
import com.bridgelabz.observers.AirportSecurity;
import com.bridgelabz.observers.ParkingManager;
import com.bridgelabz.observers.ParkingObservers;

/*
 * @Description - Test cases for ParkingLot class
 * 
 * @Properties - parkingLot - object of ParkingLot class
 * 				manager - object of ParkingManager class
 * 				car - object of Car class

 * 
 */
class ParkingLotTest {
	ParkingLot parkingLot;
	ParkingManager manager;
	ParkingObservers airportSecurity;
	Car car1;
	Car car2;

	/*
	 * @Description - sets up the objects for testing
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@BeforeEach
	void setUp() throws Exception {
		parkingLot = new ParkingLot(3);// Create a parking lot with capacity 3
		manager = new ParkingManager();// Create a parking manager
		airportSecurity = new AirportSecurity();
		parkingLot.addObservers(manager);// Add the manager to the list of observers
		parkingLot.addObservers(airportSecurity);// Add the airport security to the list of observers

		// Create two cars
		System.out.println();
		car1 = new Car("WB-12-1234", "BMW", "White", CarType.SMALL);
		car2 = new Car("WB-12-1235", "Audi TT", "Goodwood Green Pearl",CarType.SMALL);

		// Park the cars
		parkingLot.parkCar(car1,Driver.Handicapped);
		parkingLot.parkCar(car2,Driver.Non_HandiCapped);

	}

	/*
	 * @Description - tests the parkCar() method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testParkCars() {
		System.out.println("Test Park Cars");
		Car car3 = new Car("WB-12-1236", "Acura TLX Type S PMC Edition", "Gotham Gray",CarType.SMALL);
		Car car4 = new Car("WB-12-1237", "Bentley’s Mulliner division", "Fine Brodgar Silver",CarType.SMALL);

		parkingLot.parkCar(car3,Driver.Handicapped);
		parkingLot.parkCar(car4,Driver.Non_HandiCapped);
		assertEquals(3, parkingLot.getParkedCars().size());
		System.out.println();
	}

	/*
	 * @Description - tests the unparkCar() method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testUnparkCars() {
		System.out.println("Test Unpark Cars");
		parkingLot.unparkCar("WB-12-1234");

		assertFalse(parkingLot.getParkedCars().contains(car1));
		assertTrue(parkingLot.getParkedCars().contains(car2));
		System.out.println();
	}

	/*
	 * @Description - tests the notifyObservers() method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testNotifyObservers() {
		System.out.println("Test Notify Observers");
		Car car3 = new Car("WB-12-1236", "Acura TLX Type S PMC Edition", "Gotham Gray",CarType.SMALL);
		Car car4 = new Car("WB-12-1237", "Bentley’s Mulliner division", "Fine Brodgar Silver",CarType.SMALL);

		parkingLot.parkCar(car3,Driver.Handicapped);
		parkingLot.parkCar(car4, Driver.Non_HandiCapped);

		assertEquals("full", manager.update(parkingLot.isFull()));
		assertEquals("full", airportSecurity.update(parkingLot.isFull()));

		System.out.println();

	}

	/*
	 * @Description - tests the parkCar() method by attendant
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testParkCarbyAttendant() {
		System.out.println("Test Park Car by Attendant");
		Car car3 = new Car("WB-12-1236", "Acura TLX Type S PMC Edition", "Gotham Gray", CarType.SMALL);
		Car car4 = new Car("WB-12-1237", "Bentley’s Mulliner division", "Fine Brodgar Silver", CarType.MEDIUM);
		ParkingAttendant attendant = new ParkingAttendant();

		attendant.parkCar(parkingLot, car3,Driver.Handicapped);
		attendant.parkCar(parkingLot, car4, Driver.Non_HandiCapped);

		assertEquals(3, parkingLot.getParkedCars().size());
		System.out.println();
	}

	/*
	 * @Description - tests the findCar() method using license plate
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testFindCar() {
		System.out.println("Test Find Car");

		Car foundCar = parkingLot.findCar("WB-12-1234");
		assertNotNull(foundCar);
		assertEquals("BMW", foundCar.getBrand());
	}

	/*
	 * @Description - tests the chargeParking() method
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Test
	void testChargeParking() {
		System.out.println("Test Charge Parking");

		parkingLot.parkCar(car2, Driver.Non_HandiCapped);
		parkingLot.unparkCar("WB-12-1235");

		assertEquals(20, manager.chargeParking(car2));
		System.out.println();

	}
}
