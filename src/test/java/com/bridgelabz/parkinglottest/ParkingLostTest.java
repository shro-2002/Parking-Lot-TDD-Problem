package com.bridgelabz.parkinglottest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bridgelabz.model.Car;
import com.bridgelabz.model.ParkingLot;

class ParkingLostTest {
	ParkingLot parkingLot;

	@BeforeEach
	void setUp() throws Exception {
		parkingLot = new ParkingLot(100);

	}

	@Test
	void testParkCars() {
		Car car1 = new Car("MH-12-1234", "BMW", "White");
		Car car2 = new Car("MH-12-1235", "Audi TT", "Goodwood Green Pearl");

		parkingLot.parkCar(car1);
		parkingLot.parkCar(car2);

		parkingLot.printParkedCars();

		assertEquals(2, parkingLot.getParkedCars().size());
	}

}
