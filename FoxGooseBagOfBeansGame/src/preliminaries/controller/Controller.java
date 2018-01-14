package preliminaries.controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import preliminaries.model.Aeroplane;

public class Controller implements ChangeListener {

	private Aeroplane plane;

	/**
	 * Sets the plane in the field equal to the Aeroplane passed as the
	 * parameter
	 * 
	 * @param plane
	 */
	public Controller(Aeroplane plane) {

		this.plane = plane;
	}

	/**
	 * The method is inherited from the interface. We check which slider has
	 * been changed and then proceed with corresponding action
	 * 
	 * @override
	 */
	public void stateChanged(ChangeEvent e) {
		// We cast the source of the event as a JSlider and place it inside a
		// variable
		JSlider jslider = (JSlider) (e.getSource());
		// If the JSLider that has changed is the vertical one
		if (jslider.getOrientation() == JSlider.VERTICAL) {
			// Then we change the change the speed of the plane so that is equal
			// the current position on the jslider
			plane.changeSpeed(jslider.getValue());

		}
		// If the jslider is horizontal
		else if (jslider.getOrientation() == JSlider.HORIZONTAL) {
			// We change the x position of the plane so that it's equal to the
			// new value
			plane.changeX(jslider.getValue());
		}

	}

}
