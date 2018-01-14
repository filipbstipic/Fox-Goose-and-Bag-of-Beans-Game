/**
 * 
 */
package preliminaries.SimulatorMain;

import preliminaries.controller.Controller;
import preliminaries.model.Aeroplane;
import preliminaries.simulatorUI.TakeOffGUI;

/**
 * @author stefan
 *
 */
public class TakeOffSimulatorMain {

	public static void main(String[] args) {

		// We create the objects according to the MVC schema
		Aeroplane plane = new Aeroplane();
		Controller controller = new Controller(plane);
		TakeOffGUI gui = new TakeOffGUI(plane, controller);
		gui.setVisible(true);
		plane.tryTakeOff();
	}
}
