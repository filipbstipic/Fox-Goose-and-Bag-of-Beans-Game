
package preliminaries.model;

import java.util.Observable;

/**
 * Aeroplane class used to create the plane
 */
public class Aeroplane extends Observable {

	// Variables of a plane for simulating take off
	// speed should reach 10
	private int speed;
	// it's false if the plane has not crashed and is still on the runway; used
	// to exit the application
	private boolean takeOffAttempt;
	// current coordinates of the plane
	private int x;
	private int y;
	// elevation increases depending on both speed and time
	private int elevation;
	// number of seconds measured to test elevation conditions
	private int time;
	// current status of the plane
	private String status;

	/**
	 * constructor to initialise the plane ( middle of runway)
	 */
	public Aeroplane() {
		this.x = 5;
		this.y = 0;
		this.speed = 0;
		this.elevation = 0;
		this.takeOffAttempt = false;
		this.time = 0;
		this.status = "Seconds: " + time + "\n" + "X:" + x + " Y:" + y + " Speed:" + speed + " Elevation:" + elevation;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * manipulate the x of the plane ( for PlaneControls)
	 */
	public void changeX(int x) {
		this.x = x;
	}

	/**
	 * change the speed ( inside PlaneControls class)
	 */
	public void changeSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * change y(inside tryTakeOff method)
	 */
	private void changeY() {
		this.y += speed;
	}

	/**
	 * return takeOffAttempt ( returns true at the end of the program - either
	 * crashed or made it)
	 */
	public boolean checkTakeOff() {
		return takeOffAttempt;
	}

	// returns a String after one second ( information about the plane at this
	// point)
	public void tryTakeOff() {
		// infinite loop- while the plane is within bounds of the runway
		while (checkTakeOff() == false) {
			// sleep for one second
			waitOneSec();
			movePlane();

			// conditional statement to return string based on successful or
			// unsuccessful flight conditions
			if ((x != 5) && (elevation == 5)) {
				takeOffAttempt = true;
				status = "Take off failed!";
			} else if (y > 100) {
				takeOffAttempt = true;
				status = "Take off failed!";
			} else if ((elevation == 5) && (x == 5)) {
				takeOffAttempt = true;
				status = "Seconds: " + time + "\n" + "X:" + x + " Y:" + y + " Speed:" + speed + " Elevation:"
						+ elevation + "\n" + "Take off successful";
			} else
				status = "Seconds: " + time + "\n" + "X:" + x + " Y:" + y + " Speed:" + speed + " Elevation:"
						+ elevation;

			setChanged();
			notifyObservers();
		}

	}

	/**
	 * pauses the system for one second
	 */
	private void waitOneSec() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * move the plane depending on the speed. if speed is set to maximum,
	 * elevation increases, otherwise elevation decreases
	 */
	private void movePlane() {
		changeY();
		// check if plane elevates of not - depends on speed
		if (speed == 10) {
			time++;
			if (time >= 5) {
				elevation++;
			}
		} else {
			time = 0;
			if (elevation > 0)
				elevation--;
		}
	}

}
