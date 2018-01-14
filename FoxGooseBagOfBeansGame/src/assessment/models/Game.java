package assessment.models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game extends Observable implements Observer {
	// The boolean fields gameOver and success will be triggered if the game has
	// ended(the predator ate the prey) or if all the passengers managed to
	// reached the left bank
	private boolean gameOver;
	private boolean success;

	private Farmer farmer;
	private Goose goose;
	private Fox fox;
	private BagOfBeans beans;
	private Boat boat;

	private ArrayList<Passenger> rightBank;
	private ArrayList<Passenger> leftBank;
	private ArrayList<Passenger> availablePassengers;

	/**
	 * We construct the Passenger objects and the Boat object. we initialize the
	 * ArrayLists, add an observers to the Passengers as the Game class.
	 * Initially, the gameOver and success boolean values are set to false
	 */
	public Game() {
		// We initialize all the pieces in the game and pass the boat into their
		// constructor
		boat = new Boat();
		farmer = new Farmer(boat);
		fox = new Fox(boat);
		goose = new Goose(boat);
		beans = new BagOfBeans(boat);
		// We initialize the arrayLists
		rightBank = new ArrayList<Passenger>();
		leftBank = new ArrayList<Passenger>();
		availablePassengers = new ArrayList<Passenger>();

		availablePassengers.add(fox);
		availablePassengers.add(beans);
		availablePassengers.add(farmer);
		availablePassengers.add(goose);
		// at the start of the game all the pieces are positioned at the right
		// bank
		rightBank.add(fox);
		rightBank.add(beans);
		rightBank.add(farmer);
		rightBank.add(goose);
		// The game class will be an observer and an observable object in this
		// implementation
		// The controller will invoke changes in the position of the Passengers
		// and the Game class will listen for the changes so it can add the
		// passenger to the left or the right bank when necessary
		goose.addObserver(this);
		fox.addObserver(this);
		farmer.addObserver(this);
		beans.addObserver(this);
		boat.addObserver(this);
		// The game is neither over nor it has been a success at the start of
		// the game
		gameOver = false;
		success = false;

	}

	/**
	 * Standard accessor method for the gameOver field
	 * 
	 * @return boolean gameOver
	 */
	public boolean isGameOver() {

		return gameOver;
	}

	/**
	 * Standard accessor method for the success field
	 * 
	 * @return boolean success
	 */
	public boolean isSuccess() {

		return success;
	}

	/**
	 * Standard accessor method for the farmer field
	 * 
	 * @return Farmer
	 */
	public Farmer getFarmer() {

		return farmer;
	}

	/**
	 * Standard accessor method for the goose field
	 * 
	 * @return Goose
	 */
	public Goose getGoose() {

		return goose;
	}

	/**
	 * Standard accessor method for the fox field
	 * 
	 * @return Fox
	 */
	public Fox getFox() {

		return fox;
	}

	/**
	 * Standard accessor method for the beans field
	 * 
	 * @return Beans
	 */
	public BagOfBeans getBeans() {

		return beans;
	}

	/**
	 * Standard accessor method for the boat field
	 * 
	 * @return Boat
	 */
	public Boat getBoat() {

		return boat;
	}

	@Override
	public void update(Observable o, Object arg) {
		// The Game listens for the change of the position of the pieces in the
		// game
		// The argument sent will always be a location which we specified as an
		// integer so there is no danger in casting the arg to an int type
		int newLocation = (int) arg;
		// The game only cares if the end condition have been reached. Therefore
		// it doesn't need any information about the position of the boat
		for (Passenger p : availablePassengers) {
			// Because each passenger is of different class we can check if the
			// Observable object that has changed matches the type of any
			// Passenger object in the game
			if (p.getClass() == o.getClass()) {

				// If we indeed reached a matching , we check if the object is
				// currently on the right bank or on the left bank. If his new
				// location is 2 or -2 we move him to the right bank or the left
				// bank respectively
				if (!rightBank.contains(p) && newLocation == 2)
					rightBank.add(p);

				else if ((!leftBank.contains(p) && newLocation == -2))
					leftBank.add(p);

				else if (rightBank.contains(p) && newLocation == 1)
					rightBank.remove(p);

				else if (leftBank.contains(p) && newLocation == -1)
					leftBank.remove(p);

			}

		}
		// Here we check the condition for the game over after the change in
		// location has happened
		// These are pertinent to the rules of the game and to understand this
		// we should again remember the positioning of elements we use that I
		// described in the Passenger class
		if (rightBank.contains(fox) && rightBank.contains(goose)
				&& (farmer.getLocation() == -1 || farmer.getLocation() == -2)) {

			gameOver = true;

		} else if (rightBank.contains(goose) && rightBank.contains(beans)
				&& (farmer.getLocation() == -1 || farmer.getLocation() == -2)) {

			gameOver = true;
		}
		if (leftBank.contains(fox) && leftBank.contains(goose)
				&& (farmer.getLocation() == 2 || farmer.getLocation() == 1)) {

			gameOver = true;

		} else if (leftBank.contains(goose) && leftBank.contains(beans)
				&& (farmer.getLocation() == 2 || farmer.getLocation() == 1)) {

			gameOver = true;

			// Only if all the pieces have managed to reach the left side is the
			// game a success
		} else if (leftBank.contains(farmer) && leftBank.contains(goose) && leftBank.contains(beans)
				&& leftBank.contains(fox)) {

			success = true;
		}
		// Notify the GUI, but this time send the object that has changed
		setChanged();
		notifyObservers(o);

		// These are for testing purposes only
		// System.out.println(rightBank);
		// System.out.println(leftBank);

	}

}
