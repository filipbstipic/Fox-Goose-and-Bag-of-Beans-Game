
package assessment.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import assessment.controls.GameButtonControls;
import assessment.models.*;

/**
 * @author stefan This class is used to create the GUI for our game.
 */
public class CrossRiverGUI extends JFrame implements Observer {

	// model references
	private Game game;
	private GameButtonControls controls;
	// The panels
	private CustomePanel waterPanel;
	private JPanel jpNWPanel;
	private JPanel jpNEPanel;
	private JPanel jpSEPanel;
	private JPanel jpSWPanel;
	private JPanel controlsPanel;
	private CustomePanel eastBank;
	private CustomePanel westBank;

	// JLabels
	private JLabel jlBoat;
	private JLabel jlFarmer;
	private JLabel jlGoose;
	private JLabel jlBeans;
	private JLabel jlFox;

	/**
	 * Constructor of this class
	 */
	public CrossRiverGUI(GameButtonControls controls, Game game) {

		// Because the class extends JFrame first we call the constructor
		super("Fox Goose and Bag of Beans");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.controls = controls;
		this.game = game;
		// The game is the model in the MVC schema so we set the observer of the
		// game to the GUI( this class)
		game.addObserver(this);
		// Further on we construct the widgets
		initWidgets(controls);

	}

	/**
	 * This method initializes the widgets and sets the layout. It is only
	 * called inside the constructor, so it should be private.
	 */
	private void initWidgets(GameButtonControls controls) {

		// initialize panels
		controlsPanel = new JPanel();
		waterPanel = new CustomePanel("water.png");
		eastBank = new CustomePanel("grass.png");
		westBank = new CustomePanel("grass.png");
		jpSEPanel = new JPanel();
		jpSWPanel = new JPanel();
		jpNWPanel = new JPanel();
		jpNEPanel = new JPanel();

		// init JLabels for Buttons
		JLabel jlControlFarmer = new JLabel("  Farmer:");
		JLabel jlControlBoat = new JLabel("  Boat:");
		JLabel jlControlFox = new JLabel("  Fox:");
		JLabel jlControlBeans = new JLabel("  Beans:");
		JLabel jlControlGoose = new JLabel("  Goose:");

		// We create the required buttons. We set the name of every button
		// because it will be a method of identification from the controller
		// class as to determine which button was clicked
		JButton jbFarmerL = new JButton("<");
		jbFarmerL.setName("farmer left");
		JButton jbFarmerR = new JButton(">");
		jbFarmerR.setName("farmer right");

		JButton jbBoatL = new JButton("<");
		jbBoatL.setName("boat left");
		JButton jbBoatR = new JButton(">");
		jbBoatR.setName("boat right");

		JButton jbFoxL = new JButton("<");
		jbFoxL.setName("fox left");
		JButton jbFoxR = new JButton(">");
		jbFoxR.setName("fox right");

		JButton jbGooseL = new JButton("<");
		jbGooseL.setName("goose left");
		JButton jbGooseR = new JButton(">");
		jbGooseR.setName("goose right");

		JButton jbBeansL = new JButton("<");
		jbBeansL.setName("beans left");
		JButton jbBeansR = new JButton(">");
		jbBeansR.setName("beans right");

		// init ImageIcons and resize them
		ImageIcon iiFox = new ImageIcon(getClass().getResource("fox.png"));
		iiFox = resizeImage(iiFox.getImage(), 70, 70);

		ImageIcon iiGoose = new ImageIcon(getClass().getResource("goose.png"));
		iiGoose = resizeImage(iiGoose.getImage(), 70, 70);

		ImageIcon iiBeans = new ImageIcon(getClass().getResource("beans.png"));
		iiBeans = resizeImage(iiBeans.getImage(), 50, 35);

		ImageIcon iiFarmer = new ImageIcon(getClass().getResource("farmer.png"));
		iiFarmer = resizeImage(iiFarmer.getImage(), 100, 100);

		ImageIcon iiBoat = new ImageIcon(getClass().getResource("boat.png"));
		iiBoat = resizeImage(iiBoat.getImage(), 200, 200);

		// init JLabels with images
		jlFarmer = new JLabel(iiFarmer);
		jlBoat = new JLabel(iiBoat);
		jlBeans = new JLabel(iiBeans);
		jlGoose = new JLabel(iiGoose);
		jlFox = new JLabel(iiFox);

		// set layouts of the panels on which the pieces will be moving to
		// FlowLayout
		jpNWPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		jpNEPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		jpSWPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		jpSEPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

		// The frame itself is a border layout type
		this.setLayout(new BorderLayout());
		controlsPanel.setLayout(new FlowLayout());
		// While the east bank, west bank and the water panels are gridLayouts
		eastBank.setLayout(new GridLayout(4, 1));
		westBank.setLayout(new GridLayout(4, 1));
		waterPanel.setLayout(new GridLayout(2, 2));

		// add contents to the right place: water in the centre, east bank, west
		// bank, controls in the south,
		// the water is composed of 4 panels : nw, ne, se, sw
		waterPanel.add(jpNWPanel);
		waterPanel.add(jpNEPanel);
		waterPanel.add(jpSWPanel);
		waterPanel.add(jpSEPanel);
		// We add the panels to the appropriate positions
		add(waterPanel, BorderLayout.CENTER);
		add(controlsPanel, BorderLayout.SOUTH);
		add(eastBank, BorderLayout.EAST);
		add(westBank, BorderLayout.WEST);

		// the 4 panels on the water(NW, NE, SW, SE) are transparent
		jpNWPanel.setOpaque(false);
		jpNEPanel.setOpaque(false);
		jpSWPanel.setOpaque(false);
		jpSEPanel.setOpaque(false);

		// put characters ( JLabels) on the right bank ( eastBank)
		eastBank.add(jlFox);
		eastBank.add(jlGoose);
		eastBank.add(jlBeans);
		eastBank.add(jlFarmer);
		jpNEPanel.add(jlBoat);

		// make the river banks visible even if they're empty
		westBank.setPreferredSize(new Dimension(100, 100));
		eastBank.setPreferredSize(new Dimension(100, 100));

		// add widgets to the controlsPanel <Label ButtonLeft ButtonRigt>
		controlsPanel.add(jlControlBoat);
		controlsPanel.add(jbBoatL);
		controlsPanel.add(jbBoatR);

		controlsPanel.add(jlControlFarmer);
		controlsPanel.add(jbFarmerL);
		controlsPanel.add(jbFarmerR);

		controlsPanel.add(jlControlFox);
		controlsPanel.add(jbFoxL);
		controlsPanel.add(jbFoxR);

		controlsPanel.add(jlControlGoose);
		controlsPanel.add(jbGooseL);
		controlsPanel.add(jbGooseR);

		controlsPanel.add(jlControlBeans);
		controlsPanel.add(jbBeansL);
		controlsPanel.add(jbBeansR);

		// The controller object controls will be the listener of all the
		// buttons in the GUI
		jbBoatL.addActionListener(controls);
		jbBoatR.addActionListener(controls);
		jbFarmerL.addActionListener(controls);
		jbFarmerR.addActionListener(controls);
		jbFoxL.addActionListener(controls);
		jbFoxR.addActionListener(controls);
		jbGooseL.addActionListener(controls);
		jbGooseR.addActionListener(controls);
		jbBeansL.addActionListener(controls);
		jbBeansR.addActionListener(controls);

		// find a sensible size
		pack();

	}

	/**
	 * This method is used to resize the images that are used as icons for the
	 * JLabels.
	 * 
	 * @param image,
	 *            width, height
	 * @return ImageIcon
	 */
	private ImageIcon resizeImage(Image image, int width, int heigth) {

		image = image.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

	/**
	 * Updates the position of the images on the panels and sets the title
	 * depending on the information stored in the Game class
	 */
	@Override
	public void update(Observable obj, Object object) {

		JPanel jpMoveToPanel;
		// The game class will send an object that has been changed as an
		// argument. First we check if it is a Passenger type object
		if (object instanceof Passenger) {
			// If the object is a Passenger type then we access its current
			// location and set the panel that the image is to be moved equal to
			// the corresponding panel to which we move the images with that
			// location
			// For example location 1 corresponds to jpSE
			if (((Passenger) object).getLocation() == 2)
				jpMoveToPanel = eastBank;

			else if (((Passenger) object).getLocation() == 1) {
				jpMoveToPanel = jpSEPanel;
			}

			else if (((Passenger) object).getLocation() == -1)
				jpMoveToPanel = jpSWPanel;

			else
				jpMoveToPanel = westBank;
			// Then we check what particular class the object is an instance of.
			// This is necessary to determine to which panel the JLabel will
			// move
			if (object instanceof Farmer) {
				jpMoveToPanel.add(jlFarmer);
			}

			else if (object instanceof Fox) {
				jpMoveToPanel.add(jlFox);
			}

			else if (object instanceof Goose) {
				jpMoveToPanel.add(jlGoose);
			}

			else if (object instanceof BagOfBeans) {
				jpMoveToPanel.add(jlBeans);
			}
		} else {
			// If the object is not a type Passenger then it is obviously a Boat
			// type( objects have to belong to one of these 2 classes)
			// The boat has only 2 possible locations so we check if the
			// location of the boat is equal to -1
			if (((Boat) object).getLocation() == -1)
				jpNWPanel.add(jlBoat);
			// If is is not -1 then it has to be 1 so we add the JLabel with the
			// Boat image to the corresponding panel
			else
				jpNEPanel.add(jlBoat);
		}
		// after the pieces have moved we check the terminating condition
		// so we can set the title with the appropriate annotation
		if (game.isGameOver()) {
			setTitle("Game over: Predator ate prey");

		} else if (game.isSuccess()) {
			setTitle("Success!");

		} else {
			setTitle("Your current score is:" + game.getBoat().getScore());
		}
		// update Panel UIs ( show new view)
		waterPanel.updateUI();
		eastBank.updateUI();
		westBank.updateUI();
	}

}
