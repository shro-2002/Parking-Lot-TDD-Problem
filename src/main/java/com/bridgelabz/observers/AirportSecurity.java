package com.bridgelabz.observers;

/*
 * @Description -AirportSecurity class to notify the security staff about parking lot status
 * 
 * @Methods - update()
 */
public class AirportSecurity implements ParkingObservers {

	/*
	 * @Description - Update the security staff about parking lot status
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	@Override
	public String update(boolean isFull) {
		if (isFull) {
			System.out.println("Airport Security: The Parking Lot is full. Redirecting security staff.");
			return "full";
		} else {
			System.out.println("Airport Security: The Parking Lot is not full. Security staff not required.");
			return "not full";
		}
	}

}
