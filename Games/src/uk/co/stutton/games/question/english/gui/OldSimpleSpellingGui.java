/**
 * 
 */
package uk.co.stutton.games.question.english.gui;

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

import uk.co.stutton.games.GameMenu;
import uk.co.stutton.games.question.WordQuestion;
import uk.co.stutton.games.question.english.SpellingGame;

/**
 * @author Gary Stutton
 * 
 */
public class OldSimpleSpellingGui {
	private JTextField userAnswer;
	private JLabel message;
	private JLabel score;
	private JLabel wordToSpell = new JLabel();
	private JFrame frame;
	private JButton nextQuestionButton;
	private JButton spellItButton;
	private JButton newGameButton;
	private SpellingGame game;
	private WordQuestion question;
	private JPanel mainPanel;
	private Font bigFont;
	private JProgressBar correctScoreBar;
	private JProgressBar incorrectScoreBar;
	private JPanel topPanel;
	private JPanel wordPanel = new JPanel();
	private JRootPane rootPane;

	public OldSimpleSpellingGui(SpellingGame game) {
		this.game = game;
		initialiseGui();
	}


	public void initialiseGui() {
		frame = new JFrame("Simple Spelling Game");
		mainPanel = new JPanel();
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
		bigFont = new Font("Comic Sans MS", Font.BOLD, 24);
		userAnswer = new JTextField(5);
		userAnswer.setFont(bigFont);
		wordToSpell.setFont(bigFont);
		message= new JLabel();
		message.setFont(bigFont);
		score = new JLabel();
		score.setFont(bigFont);
		mainPanel.setBackground(Color.orange);
		topPanel.setBackground(Color.orange);
		correctScoreBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, game.getNumberOfQuestionsPerGame());
		correctScoreBar.setForeground(Color.GREEN);
		incorrectScoreBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, game.getNumberOfQuestionsPerGame());
		correctScoreBar.setForeground(Color.RED);
		JScrollPane qScroller = new JScrollPane(mainPanel);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		nextQuestionButton = new JButton("Next Question");
		spellItButton = new JButton("Spell it");
		newGameButton = new JButton("Play again");
		
		topPanel.add(message);
		topPanel.add(score);
		wordPanel.add(wordToSpell);
		wordPanel.add(userAnswer);
		topPanel.add(wordPanel);
		topPanel.add(spellItButton);
		topPanel.add(nextQuestionButton);
		mainPanel.add(topPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		nextQuestionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.isRight(question.getCorrectAnswer(), userAnswer.getText())) {
					message.setText("That's right. Well done!");
					score.setText("Score: "	+ game.getScore());
					correctScoreBar.setValue(game.getScore());
				} else {
					message.setText("Sorry. That's wrong. Never mind!");
					score.setText("Score: "	+ game.getScore());
				}
				message.setVisible(true);
				score.setVisible(true);
				question = game.getNextQuestion();
				if (question != null) {
					displayQuestion();
					wordToSpell.setVisible(true);
					userAnswer.setVisible(false);
					spellItButton.setVisible(true);
					rootPane.setDefaultButton(spellItButton);
					spellItButton.requestFocusInWindow();
					
				}
				else {
					message.setText(message.getText() + " - End of game");
					score.setText("Final score: " + game.getScore());
					wordToSpell.setVisible(false);
					userAnswer.setVisible(false);
					wordPanel.setVisible(false);
					mainPanel.add(newGameButton);
				}
				nextQuestionButton.setVisible(false);
				userAnswer.setText("");
				userAnswer.requestFocusInWindow();
			}
		});
		
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = game.newGame();
				frame.dispose();
				initialiseGui();
			}
		});

		spellItButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wordToSpell.setVisible(false);
				userAnswer.setVisible(true);
				nextQuestionButton.setVisible(true);
				spellItButton.setVisible(false);
				message.setVisible(false);
				score.setVisible(false);
				rootPane.setDefaultButton(nextQuestionButton);
				userAnswer.requestFocusInWindow();
			}
		});


		frame.setJMenuBar(new GameMenu().buildMenu());
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.getContentPane().add(BorderLayout.EAST, correctScoreBar);
		frame.setSize(800, 500);
		correctScoreBar.setPreferredSize(new Dimension(frame.getWidth()/10, frame.getHeight()));
		incorrectScoreBar.setPreferredSize(new Dimension(frame.getWidth()/10, frame.getHeight()));
		rootPane = frame.getRootPane();
		frame.setVisible(true);
		resetGui();
	}

	private void displayQuestion() {
		wordToSpell.setText(question.getWord());
	
	}
	
	private void resetGui(){

		question = game.getNextQuestion();
		correctScoreBar.setValue(0);
		incorrectScoreBar.setValue(0);
		message.setText("Read this word: ");
		displayQuestion();
		
		wordToSpell.setVisible(true);
		spellItButton.setVisible(true);
		wordPanel.setVisible(true);
		userAnswer.setVisible(false);
		nextQuestionButton.setVisible(false);
		rootPane.setDefaultButton(spellItButton);
		spellItButton.requestFocusInWindow();
	}
	
}
