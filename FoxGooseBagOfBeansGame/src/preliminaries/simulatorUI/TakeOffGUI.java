/**
 * Daniel Mannix , Radu-Stefan Muscalu
 */
package preliminaries.simulatorUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import preliminaries.controller.Controller;
import preliminaries.model.Aeroplane;

/**
 * this class is used to create a gui with two sliders, it does not depend on
 * the Aeroplane class.
 */
public class TakeOffGUI extends JFrame implements Observer {
	// widgets of the gui
	private JPanel jpTopPanel;
	private JPanel jpBottomPanel;
	private JSlider jsVerticalSlider;
	private JSlider jsHorizontalSlider;
	private JTextArea jtaPlaneStatus;
	private JScrollPane jspScroll;
	private JButton jbReset;
	private Controller controller;

	// listener object
	private Aeroplane plane;

	/**
	 * constructor, calls method guiSettings to initialise widgets
	 */
	public TakeOffGUI(Aeroplane plane, Controller controller) {
		super("Simulator");
		this.plane = plane;
		this.controller = controller;
		plane.addObserver(this);
		guiSettings();

	}

	private void guiSettings() {
		// initialise widgets
		jpTopPanel = new JPanel();
		jpBottomPanel = new JPanel();
		jsVerticalSlider = new JSlider(JSlider.VERTICAL, 0, 10, 0);

		jsHorizontalSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		jtaPlaneStatus = new JTextArea("  ");
		jspScroll = new JScrollPane(jtaPlaneStatus);
		jbReset = new JButton("Reset");

		// add listeners
		jsVerticalSlider.addChangeListener(controller);
		jsHorizontalSlider.addChangeListener(controller);

		// set the layout
		this.setLayout(new GridLayout(2, 1));
		jpTopPanel.setLayout(new BorderLayout());
		jpBottomPanel.setLayout(new BorderLayout());
		jtaPlaneStatus.setEditable(false);

		// add widgets to frame
		jpTopPanel.add(jspScroll, BorderLayout.CENTER);
		jpBottomPanel.add(jsHorizontalSlider, BorderLayout.NORTH);
		jpBottomPanel.add(jsVerticalSlider, BorderLayout.CENTER);
		jpBottomPanel.add(jbReset, BorderLayout.SOUTH);
		this.add(jpTopPanel);
		this.add(jpBottomPanel);
		this.pack();
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * add a new line to the textArea, move the scroll bar down
	 */
	public void changeText(String s) {
		jtaPlaneStatus.append(s + "\n");
		jtaPlaneStatus.setCaretPosition(jtaPlaneStatus.getDocument().getLength());
	}

	public void update(Observable arg0, Object arg1) {
		changeText(plane.getStatus());

	}

}
