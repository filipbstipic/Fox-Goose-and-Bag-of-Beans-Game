/**
 * 
 */
package assessment.models;

import java.util.Observable;

/**
 * This abstract class has been made to be extended by an object that wishes to
 * travel on the boat
 *
 */
public abstract class Passenger extends Observable {

	// Each object that extends the Passenger class has a special location which
	// can be either 2 (denoting right bankside), 1 (denoting right side of the
	// river)
	// -1 (denoting left side of the river) and -2 (denoting left bankside)
	protected int location;
	// Each time we construct an object of subclass of the super class Passenger
	// we will need to specify the Boat by which the Passenger will travel
	protected Boat boat;

	/**
	 * Sets the location to 2
	 * 
	 * @param boat
	 */
	public Passenger(Boat boat) {

		this.boat = boat;
		// The initial position of every object when constructed is 2( denoting
		// right bankside)
		location = 2;
	}

	/**
	 * This method attempts to move a passenger to the left. If the moving was
	 * successful the location of the passenger is changed and the Observers are
	 * notified
	 */
	public void moveLeft() {

		// There are 3 specific scenarios under which the passenger can move to
		// the left. The first one occurs if the passenger is on the right bank,
		// near the boat and the boat is not full
		if (this.isOnRightBank() && this.isNearBoat() && !(boat.isFull())) {

			boat.addPassenger(this);
			location = 1;
		}
		// The second one occurs if the the passenger is near the left bank
		else if (this.isNearLeftBank()) {
			boat.removePassenger(this);
			location = -2;

		}
		// And the third occurs if the Passenger is on board of the boat and the
		// boat has decided to move. In that scenario the passenger will also
		// change the location to be equal to the that of the boat
		else if (boat.isOnBoard(this) && boat.getLocation() != this.getLocation()) {

			location = -1;
		}
		// notify observers with the new location
		setChanged();
		notifyObservers(location);

	}

	/**
	 * This method attempts to move a passenger to the right. If the moving was
	 * successful the location of the passenger is changed and the Observers are
	 * notified
	 */
	public void moveRight() {

		// There are 3 specific scenarios under which the passenger can move to
		// the right. The first one occurs if the passenger is on the left bank,
		// near the boat and the boat is not full
		if (this.isOnLeftBank() && this.isNearBoat() && !(boat.isFull())) {

			boat.addPassenger(this);
			location = -1;
		}
		// The second one occurs if the the passenger is near the right bank
		else if (this.isNearRightBank()) {

			boat.removePassenger(this);
			location = 2;

		}
		// And the third occurs if the Passenger is on board of the boat and the
		// boat has decided to move. In that scenario the passenger will also
		// change the location to be equal to the that of the boat
		else if (boat.isOnBoard(this) && boat.getLocation() != this.getLocation()) {

			location = 1;
		}
		setChanged();
		notifyObservers(location);
	}

	/**
	 * This method checks if the passenger is near the boat. It will return true
	 * if the passenger is on the left bank and the boat is near the left bank
	 * or when the passenger is on the right bank and the boat is near the right
	 * bank
	 * 
	 * @return boolean
	 */
	public boolean isNearBoat() {

		if ((this.isOnLeftBank() && boat.isNearLeftBank()) || (this.isOnRightBank() && boat.isNearRightBank()))

			return true;
		else {
			return false;
		}
	}
	// We've included these methods to increase the readability of the code. At
	// the begging of this file I have explained how the absolute integer
	// location translate
	// to certain position of the map.

	/**
	 * If the location of the passenger is 1 return true
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
	 * If the location of the passenger is -1 return true
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
	 * If the location of the passenger is -2 return true
	 * 
	 * @return boolean
	 */
	public boolean isOnLeftBank() {

		if (location == -2)
			return true;
		else
			return false;
	}

	/**
	 * If the location of the passenger is 2 return true
	 * 
	 * @return boolean
	 */
	public boolean isOnRightBank() {

		if (location == 2)
			return true;
		else
			return false;
	}

	/**
	 * Returns the absolute position of the passenger(-2,-1,1 or 2)
	 * 
	 * @return int
	 */
	public int getLocation() {

		return location;
	}

}
