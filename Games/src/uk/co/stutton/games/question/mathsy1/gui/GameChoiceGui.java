/**
 * 
 */
package uk.co.stutton.games.question.mathsy1.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import uk.co.stutton.games.GameMenu;
import uk.co.stutton.games.options.CustomisableGame;
import uk.co.stutton.games.question.QuestionGame;
import uk.co.stutton.games.question.mathsy1.NextNumberGame;

/**
 * @author Gary Stutton
 * 
 */

// Swing and Threads: see
// http://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html
public class GameChoiceGui extends GameGui {
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel gamePanel;
	private Font bigFont;
	private int gameCount;
	private JButton nextGame;

	public GameChoiceGui() {
		super();
		buildGui();
	}

	public void buildGui() {
		frame = new JFrame("Choose a game");
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
		gamePanel = new JPanel();
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
		addTypeNextNumberGameButton("Type next number");
		addSpotNextNumberGameButton("Spot next number");
		mainPanel.add(gamePanel);
	}

	private void addButton() {
		// create a new gamePanel every 5 games
		if (gameCount++ == 4) {
			mainPanel.add(gamePanel);
			gamePanel = new JPanel();
			gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));
			gameCount = 0;
		}
		gamePanel.add(nextGame);
	}

	private void addSpotNextNumberGameButton(String title) {
		nextGame = new JButton(title);
		nextGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				QuestionGame game = new NextNumberGame();
				GameGui gameGui = new NextNumberDragAndDropGui(game);
				if (game instanceof CustomisableGame){
					GameOptionsGui optionsGui = new GameOptionsGui((CustomisableGame)game, gameGui);
					optionsGui.buildGui();
				}
			}
		});
		addButton();

	}

	private void addTypeNextNumberGameButton(String title) {
		nextGame = new JButton(title);
		nextGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				QuestionGame game = new NextNumberGame();
				GameGui gameGui = new NextNumberGui(game);
				if (game instanceof CustomisableGame){
					GameOptionsGui optionsGui = new GameOptionsGui((CustomisableGame)game, gameGui);
					optionsGui.buildGui();
				}
			}
		});
		addButton();
	}

}
