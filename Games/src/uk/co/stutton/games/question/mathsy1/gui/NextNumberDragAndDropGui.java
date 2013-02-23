/**
 * 
 */
package uk.co.stutton.games.question.mathsy1.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import uk.co.stutton.games.GameMenu;
import uk.co.stutton.games.question.NumberQuestion;
import uk.co.stutton.games.question.QuestionGame;

/**
 * @author Gary Stutton
 * 
 */
public class NextNumberDragAndDropGui extends GameGui {
	/**
	 * 
	 */
	private JFrame frame;
	private QuestionGame game;
	private NumberQuestion question;
	private JLabel message;
	private JLabel score;
	private JButton[] displayQuestionNumbers;
	private int[] possibleAnswers;
	private int possibleAnswerIndex;
	private Font bigFont;
//	private GamePanel mainPanel;
	private JPanel mainPanel;
	private int xCoord; // TODO will make this array
	private int yCoord;
	private static NextNumberDragAndDropGui gui;
	private int loopCount;
	private Timer timer;
	private JButton dragAndDropButton;
	private int xButtonDown = 0;
	private int yButtonDown = 0;
	private JButton blankAnswer;

	public NextNumberDragAndDropGui(QuestionGame game) {
		super();
		this.game = game;
	}

	public void buildGui() {
		question = game.getNextQuestion();
		possibleAnswers = question.getPossibleAnswers();
		for (int i:possibleAnswers){
			System.out.println(i);
		}
		bigFont = new Font("Comic Sans MS", Font.BOLD, 24);
		frame = new JFrame("Next number game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new GamePanel();
		JLabel title = new JLabel();
		title.setFont(bigFont);
		title.setText(game.getTitle());
		mainPanel.add(title);
		mainPanel.setPreferredSize(new Dimension(300, 300));
		buildQuestionNumbers();
		buildPossibleAnswers();
		frame.setJMenuBar(new GameMenu().buildMenu());
		frame.setContentPane(mainPanel);
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension screenDimension = toolkit.getScreenSize();
		frame.setPreferredSize(screenDimension);
		frame.pack();
		frame.setVisible(true);
		frame.requestFocus();
		animate();
	}

	private void buildPossibleAnswers() {
		dragAndDropButton = new JButton("" + possibleAnswers[0]);
		dragAndDropButton.setFont(bigFont);
		
		//add a MouseListener to initiate the Drag on the appropriate
		//MouseEvent
		dragAndDropButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
//				JButton button = (JButton)e.getSource();
				timer.stop();
				xButtonDown =  e.getX();
				yButtonDown =  e.getY();
			}
			
			public void mouseReleased(MouseEvent e){
				// if mouse pressed on the button, stop the timer to stop scrolling the button
				JButton button = (JButton)e.getSource();
				button.setLocation(e.getPoint());
				xCoord = xCoord + (e.getX() - xButtonDown); // This is the button coords, we need the panel coords
				yCoord = yCoord + (e.getY() - yButtonDown);
				mainPanel.repaint(Math.min(xCoord, xButtonDown),
						Math.min(yCoord, yButtonDown),
						Math.abs(xCoord-xButtonDown), 
						Math.abs(yCoord-yButtonDown));
				// check if new button position over answer space
				// if so, check if button number is correct answer
				System.out.println("blank answer position " + blankAnswer.getX() + " " + blankAnswer.getY() + blankAnswer.getBounds());
				
			}
		});
		
		dragAndDropButton.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				JButton button = (JButton)e.getSource();
				button.setLocation(e.getPoint());
				xCoord = xCoord + (e.getX() - xButtonDown); // This is the button coords, we need the panel coords
				yCoord = yCoord + (e.getY() - yButtonDown);
				mainPanel.repaint(Math.min(xCoord, xButtonDown),
						Math.min(yCoord, yButtonDown),
						Math.abs(xCoord-xButtonDown), 
						Math.abs(yCoord-yButtonDown));
			}
		});
		
		dragAndDropButton.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				// If possible answer gets to other side of screen, set back to original side
				// and set it to the next possible answer
				if ((xCoord + dragAndDropButton.getWidth()) <=0 ){
//					Point p = dragAndDropButton.getLocation();
					xCoord = mainPanel.getWidth();
					System.out.println(possibleAnswerIndex);
					if (++possibleAnswerIndex >= possibleAnswers.length){
						possibleAnswerIndex = 0;
					}
					dragAndDropButton.setText("" + possibleAnswers[possibleAnswerIndex]);
					mainPanel.repaint();
				}
				
			}
		});
		
		mainPanel.add(dragAndDropButton);
		mainPanel.setComponentZOrder(dragAndDropButton, 0);
		
	}

	private void animate() {
		xCoord = mainPanel.getWidth();  // set xCoord to right side of visible panel
		yCoord = mainPanel.getHeight() / 10; // set yCoord to 10% of height of visible panel
		int delay = 5; // milliseconds
		loopCount = 0;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				xCoord--;
				loopCount++;
				mainPanel.repaint();
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();
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
		// Add the blank button for where the answer needs to be dropped
		blankAnswer = new JButton("?");
		blankAnswer.setFont(bigFont);

		numberPanel.add(blankAnswer);
		mainPanel.add(numberPanel);
	
	}


	class GamePanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4981584416216634961L;

		// TODO - just repaint the part of the screen that's changed
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint(0, 0, Color.ORANGE,
					this.getWidth(), this.getHeight(), Color.RED);
			g2d.setPaint(gradient);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

//			g2d.setColor(Color.green);
//			g2d.fillOval(xCoord, yCoord, 40, 40);
			dragAndDropButton.setLocation(xCoord, yCoord);

		}
	}

}
