package com.bridgelabz.observers;

/*
 * @Description -Notify the manager about parking lot status
 * 
 * @Methods - update() - notifies the manager about parking lot status
 * 
 */
public class ParkingManager implements ParkingObservers {

	@Override

	/*
	 * @Description - Update the manager about parking lot status
	 * 
	 * @param - none
	 * 
	 * @return - none
	 */
	public void update() {
		System.out
				.println("Dear Manager " + "the Parking Space is full. " + "Kindly put out the PARKING LOT FULL Sign ");
	}

	public String getStatus() {
		return "full";
	}

}
