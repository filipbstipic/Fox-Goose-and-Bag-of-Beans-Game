package assessment.foxGooseBagOfBeans;

import assessment.controls.GameButtonControls;
import assessment.models.*;
import assessment.view.CrossRiverGUI;

/**
 * 
 * This is the main class. It's used to run the app.
 */
public class FoxGooseBagOfBeans {
	public static void main(String[] args) {

		// We instantiate the Game class and then pass it to the controller and
		// the gui, adhering to the rules of the MVC
		Game game = new Game();

		GameButtonControls controls = new GameButtonControls(game);
		// Alongside with the game class we pass the controls to the gui
		CrossRiverGUI gui = new CrossRiverGUI(controls, game);

		gui.setVisible(true);

	}

}
