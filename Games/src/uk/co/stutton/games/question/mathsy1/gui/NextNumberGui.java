/**
 * 
 */
package uk.co.stutton.games.question.mathsy1.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import uk.co.stutton.games.Game;
import uk.co.stutton.games.GameMenu;
import uk.co.stutton.games.question.NumberQuestion;
import uk.co.stutton.games.question.QuestionGame;

/**
 * @author Gary Stutton
 * 
 */
public class NextNumberGui extends GameGui {
	private JTextField userAnswer;
	private JLabel message;
	private JLabel score;
	private JButton[] displayQuestionNumbers;
	private JFrame frame;
	private JButton nextQuestionButton;
	private QuestionGame game;
	private NumberQuestion question;
	private JPanel mainPanel;
	private Font bigFont;
	private JProgressBar scoreBar;
	private JPanel topPanel;

	public NextNumberGui(QuestionGame game) {
		super();
		this.game = game;
	}

	public void buildGui() {
		question = game.getNextQuestion();
		frame = new JFrame("Next number game");
		mainPanel = new JPanel();
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
		bigFont = new Font("Comic Sans MS", Font.BOLD, 24);
		JLabel title = new JLabel();
		title.setFont(bigFont);
		title.setText(game.getTitle());
		userAnswer = new JTextField(5);
		userAnswer.setFont(bigFont);
		message= new JLabel();
		message.setFont(bigFont);
		score = new JLabel();
		score.setFont(bigFont);
		mainPanel.setBackground(Color.orange);
		topPanel.setBackground(Color.orange);
		scoreBar = new JProgressBar(SwingConstants.VERTICAL, 0, 10);
		scoreBar.setValue(0);
		scoreBar.setForeground(Color.GREEN);
		
		message.setText("Hello -- What number comes next?");
		JScrollPane qScroller = new JScrollPane(mainPanel);
		qScroller
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		qScroller
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		nextQuestionButton = new JButton("Next Question");
		
		topPanel.add(title);
		topPanel.add(message);
		topPanel.add(score);
		buildQuestionNumbers();
		mainPanel.add(topPanel);
		mainPanel.add(nextQuestionButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		nextQuestionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.isRight(question, userAnswer.getText())) {
					message.setText("That's right. Well done!");
					score.setText("Score: "	+ game.getScore());
					scoreBar.setValue(game.getScore());
				} else {
					message.setText("Sorry. That's wrong. Never mind!");
					score.setText("Score: "	+ game.getScore());
				}
				question = game.getNextQuestion();
				if (question != null) {
					getQuestionNumbers();
				}
				else {
					message.setText("End of game");
					score.setText("Final score: " + game.getScore());
					for (JButton button: displayQuestionNumbers){
						button.setVisible(false);
					}
					userAnswer.setVisible(false);
					nextQuestionButton.setVisible(false);
				}
				userAnswer.setText("");
				userAnswer.requestFocusInWindow();
			}
		});

		frame.setJMenuBar(new GameMenu().buildMenu());
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.getContentPane().add(BorderLayout.EAST, scoreBar);
		frame.setSize(550, 500);
		scoreBar.setPreferredSize(new Dimension(frame.getWidth()/10, frame.getHeight()));
		JRootPane rootPane = frame.getRootPane();
	    rootPane.setDefaultButton(nextQuestionButton);
		frame.setVisible(true);
		userAnswer.requestFocusInWindow();

	}

	private void getQuestionNumbers() {
		int[] questionNumbers = question.getQuestionNumbers();
		for (int i = 0; i < questionNumbers.length; i++) {
			displayQuestionNumbers[i].setText("" + questionNumbers[i]);
		}
	}

	private void buildQuestionNumbers() {
		JPanel numberPanel = new JPanel();
		int[] questionNumbers = question.getQuestionNumbers();
		displayQuestionNumbers = new JButton[questionNumbers.length];
		for (int i = 0; i < questionNumbers.length; i++) {
			displayQuestionNumbers[i] = new JButton("" + questionNumbers[i]);
			displayQuestionNumbers[i].setFont(bigFont);
			numberPanel.add(displayQuestionNumbers[i]);
		}
		numberPanel.add(userAnswer);
		topPanel.add(numberPanel);
	
	}

}
