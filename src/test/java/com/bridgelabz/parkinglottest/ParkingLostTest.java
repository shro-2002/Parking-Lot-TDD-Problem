package com.bridgelabz.parkinglottest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bridgelabz.model.Car;
import com.bridgelabz.model.ParkingLot;

class ParkingLostTest {
	ParkingLot parkingLot;
	Car car1;
	Car car2;

	@BeforeEach
	void setUp() throws Exception {
		parkingLot = new ParkingLot(100);
		car1 = new Car("MH-12-1234", "BMW", "White");
		car2 = new Car("MH-12-1235", "Audi TT", "Goodwood Green Pearl");
		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);

	}

	@Test
	void testParkCars() {
		Car car3 = new Car("MH-12-1236", "Acura TLX Type S PMC Edition", "Gotham Gray");
		Car car4 = new Car("MH-12-1237", "Bentley’s Mulliner division", "Fine Brodgar Silver");

		parkingLot.parkCar(car3);
		parkingLot.parkCar(car4);

		parkingLot.printParkedCars();

		assertEquals(4, parkingLot.getParkedCars().size());
	}

	
	@Test
	void testUnparkCars() {
		parkingLot.unparkCar("MH-12-1234");

        assertFalse(parkingLot.getParkedCars().contains(car1));
        assertTrue(parkingLot.getParkedCars().contains(car2));
	}
}
