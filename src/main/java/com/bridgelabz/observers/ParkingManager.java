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
	public String update(boolean isFull) {
		if (isFull) {
			System.out.println(
					"Dear Manager " + "the Parking Space is full. " + "Kindly put out the PARKING LOT FULL Sign ");

			return "full";
		} else {
			System.out.println(
					"Dear Manager " + "the Parking Space is not full. " + "Kindly put out the PARKING LOT FULL Sign ");
			return "not full";

		}
	}

}
