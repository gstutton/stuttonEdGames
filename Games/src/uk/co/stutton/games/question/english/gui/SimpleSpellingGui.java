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
public class SimpleSpellingGui{
	private JFrame frame = new JFrame("Simple Spelling Game");
	private JRootPane rootPane;
	private SpellingGame game;
	private WordQuestion question;
	private Font bigFont = new Font("Comic Sans MS", Font.BOLD, 24);

	private JPanel mainPanel = new JPanel();

	private JPanel topPanel = new JPanel();
	private JLabel message = new JLabel();
	
	private JPanel answerPanel = new JPanel();
	private JTextField userAnswer = new JTextField(5);
	private JButton nextQuestionButton = new JButton("Next Question");

	private JPanel questionPanel = new JPanel();
	private JLabel wordToSpell = new JLabel();
	private JButton spellItButton = new JButton("Spell it");

	private JPanel scorePanel = new JPanel();
	private JLabel score = new JLabel();
	private JProgressBar correctScoreBar;
	private JProgressBar incorrectScoreBar;

	private JButton newGameButton = new JButton("Play again");
	

	public SimpleSpellingGui(SpellingGame game) {
		this.game = game;
		initialiseGui();
	}


	public void initialiseGui() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane qScroller = new JScrollPane(mainPanel);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
		topPanel.add(message);
		
		correctScoreBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, game.getNumberOfQuestionsPerGame());
		incorrectScoreBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, game.getNumberOfQuestionsPerGame());
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
		scorePanel.add(score);
		scorePanel.add(correctScoreBar);
		scorePanel.add(incorrectScoreBar);
		
		questionPanel.add(wordToSpell);
		questionPanel.add(spellItButton);
		
		answerPanel.add(userAnswer);
		answerPanel.add(nextQuestionButton);

		
		topPanel.add(questionPanel);
		topPanel.add(answerPanel);
		mainPanel.add(topPanel);
		mainPanel.add(newGameButton);
		
		setFormatting();
		

		nextQuestionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (userAnswer.getText().equalsIgnoreCase("")){
					message.setText("You need to type an answer in");
					return;
				}
				updateScore();
				question = game.getNextQuestion();
				if (question != null) {
					displayQuestion();
					answerPanel.setVisible(false);
					questionPanel.setVisible(true);
					rootPane.setDefaultButton(spellItButton);
				}
				else {
					message.setText(message.getText() + " - End of game");
					answerPanel.setVisible(false);
					newGameButton.setVisible(true);
					rootPane.setDefaultButton(newGameButton);
				}
			}
		});
		
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game = game.newGame();
				resetGui();
			}
		});

		spellItButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				message.setText("");
				questionPanel.setVisible(false);
				answerPanel.setVisible(true);
				rootPane.setDefaultButton(nextQuestionButton);
				userAnswer.requestFocusInWindow();
			}
		});


		frame.setJMenuBar(new SpellingGameMenu().buildMenu());
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, scorePanel);
		frame.setSize(800, 500);
		correctScoreBar.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/10));
		incorrectScoreBar.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()/10));
		rootPane = frame.getRootPane();
		frame.setVisible(true);
		resetGui();
	}


	private void setFormatting() {
		userAnswer.setFont(bigFont);
		wordToSpell.setFont(bigFont);
		message.setFont(bigFont);
		score.setFont(bigFont);
		mainPanel.setBackground(Color.orange);
		topPanel.setBackground(Color.orange);
		
		correctScoreBar.setForeground(Color.GREEN);
		correctScoreBar.setBackground(Color.ORANGE);
		correctScoreBar.setBorderPainted(false);
		incorrectScoreBar.setForeground(Color.RED);
		incorrectScoreBar.setBackground(Color.ORANGE);
		incorrectScoreBar.setBorderPainted(false);

	}

	private void displayQuestion() {
		wordToSpell.setText(question.getWord());
		userAnswer.setText("");
	}
	
	private void resetGui(){

		question = game.getNextQuestion();
		correctScoreBar.setValue(0);
		incorrectScoreBar.setValue(0);
		message.setText("Read this word: ");
		score.setText("");
		displayQuestion();
		
		questionPanel.setVisible(true);
		answerPanel.setVisible(false);
		newGameButton.setVisible(false);
		rootPane.setDefaultButton(spellItButton);
	}

	private void updateScore() {
		if (game.isRight(question.getCorrectAnswer(), userAnswer.getText())) {
			message.setText("That's right. Well done!");
			correctScoreBar.setValue(game.getCorrectScore());
		} else {
			message.setText("Sorry. That's wrong. Never mind!");
			incorrectScoreBar.setValue(game.getIncorrectScore());
		}
		score.setText("Score: "	+ game.getCorrectScore() + " out of " + (game.getCorrectScore() + game.getIncorrectScore()));
	}
}
