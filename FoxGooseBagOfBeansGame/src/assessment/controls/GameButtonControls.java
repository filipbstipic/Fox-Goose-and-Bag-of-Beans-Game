package assessment.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import assessment.models.*;

public class GameButtonControls implements ActionListener {

	// reference to object
	private Game game;

	public GameButtonControls(Game game) {

		this.game = game;
	}

	/**
	 * The overridden method will fetch the name of the button that has been
	 * clicked and execute the command that is explicitly stated in the name of
	 * the button
	 * 
	 * @override
	 */
	public void actionPerformed(ActionEvent e) {

		// To detect which button has actually been clicked we fetch the widget
		// using getSource() and then getName() to access the desired name
		String s = ((JButton) (e.getSource())).getName();

		// The buttons will only be responsive in the scenario in which it is
		// not the case that the game is over and it is not the case the game
		// has been a success
		if (!game.isGameOver() && !game.isSuccess()) {

			// From here on the if statements and the actions are pretty generic
			// ; we fetch the name of the action and then
			// reach through the game object, which is the model in the MVC
			// scheme, and then execute the command on a desired end object.
			if (s.equals("farmer left")) {

				game.getFarmer().moveLeft();
			}

			else if (s.equals("farmer right")) {

				game.getFarmer().moveRight();
			}

			else if (s.equals("boat right")) {

				game.getBoat().moveRight();
			}

			else if (s.equals("boat left")) {

				game.getBoat().moveLeft();

			}

			else if (s.equals("fox right")) {

				game.getFox().moveRight();
			}

			else if (s.equals("fox left")) {

				game.getFox().moveLeft();
			}

			else if (s.equals("goose right")) {

				game.getGoose().moveRight();
			}

			else if (s.equals("goose left")) {

				game.getGoose().moveLeft();
			}

			else if (s.equals("beans right")) {

				game.getBeans().moveRight();
			}

			else if (s.equals("beans left")) {

				game.getBeans().moveLeft();
			}
		}

	}

}
