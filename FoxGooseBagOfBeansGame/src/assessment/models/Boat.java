/**
 * 
 */
package assessment.models;

import java.util.ArrayList;
import java.util.Observable;

public class Boat extends Observable {

	// it will be 1 if boat is near right bank or -1 if it is near the left bank
	private int location;
	// The boat has an ArrayList of passengers that are currently on board
	private ArrayList<Passenger> passengers;
	// The maximum capacity of any Boat object is 2 passengers
	private static final int CAPACITY = 2;
	// Every time the boat moves 1 is subtracted from the score( which starts as
	// 0)
	private int score;

	/**
	 * Sets the starting location of the Boat equal to 1, score to 0 and creates
	 * an empty ArrayList of Passengers
	 */
	public Boat() {

		passengers = new ArrayList<Passenger>();
		location = 1;
		score = 0;
	}

	/**
	 * This method returns true if the passenger is in the passengers ArrayList
	 * and false if it isn't
	 * 
	 * @param passenger
	 * @return boolean
	 */
	public boolean isOnBoard(Passenger passenger) {

		for (Passenger p : passengers) {
			// It is completely fine for us the use object identity operator
			// instead of equals becuase there is only one object of each type
			// in the game
			if (p == passenger) {

				return true;
			}
		}
		return false;

	}

	/**
	 * returns true if the size of the passengers ArrayList is equal to the
	 * capacity
	 * 
	 * @return boolean
	 */
	public boolean isFull() {

		return passengers.size() == CAPACITY;

	}

	/**
	 * Checks if the passenger in the parameter is near the boat and if the boat
	 * is not full. If it is indeed so the passengers is added to passengers
	 * arrayList
	 * 
	 * @param passenger
	 */
	public void addPassenger(Passenger passenger) {

		if (passenger.isNearBoat() && !this.isFull()) {

			passengers.add(passenger);
		}
	}

	/**
	 * Removes the passenger from the passengers arrayList
	 * 
	 * @param passenger
	 */
	public void removePassenger(Passenger passenger) {

		passengers.remove(passenger);

	}

	/**
	 * Checks if the Farmer type object is on Board. By the rules of the game,
	 * the boat can only move if the Farmer is in the passengers arrayList
	 * 
	 * @return
	 */
	private boolean usable() {

		for (Passenger p : passengers) {
			if (p instanceof Farmer)
				return true;
		}
		return false;
	}

	/**
	 * Returns true if the boat is at location -1 and false if it is not
	 * 
	 * @return boolean
	 */
	public boolean isNearLeftBank() {
		if (location == -1)
			return true;
		else
			return false;
	}

	/**
	 * Returns true if the boat is at location 1 and false if it is not
	 * 
	 * @return boolean
	 */
	public boolean isNearRightBank() {
		if (location == 1)
			return true;
		else
			return false;
	}

	/**
	 * Attempt to move the Boat to the right. Notifies observers is successful.
	 */
	public void moveRight() {
		// First we check if the farmer is on board and if the boat is near the
		// left bank
		if (usable() && this.isNearLeftBank()) {
			// If the condition was satisfied we switch the location to 1. This
			// needs to happen before moving the passengers
			location = 1;
			// We move each passenger on board to the right as well
			for (Passenger p : passengers) {
				p.moveRight();
			}
			score--;
			// Notify observers
			setChanged();
			notifyObservers(location);
		}

	}

	/**
	 * Attempt to move the Boat to the left. Notifies observers is successful.
	 */
	public void moveLeft() {
		// Symmetric to the moveRight method
		if (usable() && this.isNearRightBank()) {
			location = -1;
			for (Passenger p : passengers) {
				p.moveLeft();

			}
			score--;
			setChanged();
			notifyObservers(location);
		}

	}

	/**
	 * Accessor method for the score field
	 * 
	 * @return int
	 */
	public int getScore() {

		return score;
	}

	/**
	 * Accessor method for the location field
	 * 
	 * @return int
	 */
	public int getLocation() {

		return location;
	}
}
