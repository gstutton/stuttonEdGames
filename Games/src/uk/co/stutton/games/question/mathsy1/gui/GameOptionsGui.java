package uk.co.stutton.games.question.mathsy1.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import uk.co.stutton.games.GameMenu;
import uk.co.stutton.games.options.CustomisableGame;
import uk.co.stutton.games.options.Level;

public class GameOptionsGui extends GameGui implements ActionListener{
	
	private JFrame frame;
	private JPanel mainPanel;
	private Font bigFont;
	private CustomisableGame game;
	private GameGui gui;
	
	public GameOptionsGui(CustomisableGame game, GameGui gui) {
		super();
		this.game = game;
		this.gui = gui;
	}

	@Override
	public void buildGui() {
		frame = new JFrame("Choose options");
		mainPanel = new JPanel();
		bigFont = new Font("Comic Sans MS", Font.BOLD, 24);

		JScrollPane qScroller = new JScrollPane(mainPanel);
		qScroller
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		qScroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		buildGameOptions();
		frame.setJMenuBar(new GameMenu().buildMenu());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(640, 500);
		frame.setVisible(true);
	}

	private void buildGameOptions() {
	    //Create the radio buttons.
		JPanel level = new JPanel();
	    JRadioButton easy = new JRadioButton(Level.EASY.toString());
	    easy.setActionCommand(Level.EASY.toString());
	    easy.setSelected(true);

	    JRadioButton hard = new JRadioButton(Level.HARD.toString());
	    hard.setActionCommand(Level.HARD.toString());

	    //Group the radio buttons.
	    ButtonGroup levelGroup = new ButtonGroup();
	    levelGroup.add(easy);
	    levelGroup.add(hard);

	    //Register a listener for the radio buttons.
	    easy.addActionListener(this);
	    hard.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.setOption(Level.class,e.getActionCommand());
	}

}
