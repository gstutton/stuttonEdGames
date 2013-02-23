/**
 * 
 */
package uk.co.stutton.games.question.mathsy1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import uk.co.stutton.games.options.CustomisableGame;
import uk.co.stutton.games.options.LevelOption;
import uk.co.stutton.games.options.Option;
import uk.co.stutton.games.options.SequencePatternOption;
import uk.co.stutton.games.question.NumberQuestion;

/**
 * @author Gary Stutton Creates a game for next number
 */
public class NextNumberGame extends MathsQuestionGame implements CustomisableGame{

	private int pointsPerAnswer = 1;
	private int sequenceStep = 2;
	private int numberOfQuestionsPerGame = 10;
	private int numberOfNumericalItemsInQuestion = 3;
	private List<NumberQuestion> questions = new ArrayList<NumberQuestion>(
			numberOfQuestionsPerGame);
	// private List<Integer> answers = new ArrayList<Integer>(
	// NUMBER_OF_QUESTIONS_PER_GAME);
	private int currentQuestionIndex = 0;
	private int maxStartingNumber = 20;
	private int numberOfPossibleAnswers = 7;
//	private LevelOption level;
//	private SequencePatternOption sequencePattern;
	private Map<Class, Option> options = new HashMap<Class, Option>();
	

	public NextNumberGame() {
		createDefaultOptions();
		makeQuestions();
	}

	@Override
	public boolean isRight(NumberQuestion question, String userAnswer) {
		int givenAnswer = 0;
		try {
			givenAnswer = Integer.parseInt(userAnswer);
		} catch (NumberFormatException e) {
			return false;
		}
		if (givenAnswer == question.getCorrectAnswer()) {
			setScore(getScore() + pointsPerAnswer);
			return true;
		}
		return false;
	}

	@Override
	public NumberQuestion getNextQuestion() {
		if (currentQuestionIndex < questions.size()){
			return questions.get(currentQuestionIndex++);
		}
		return null;
	}

//	@Deprecated
//	@Override
//	public void playGame() {
//		super.playGame();
//		makeGui();
//		for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
//			System.out.println(questions.get(currentQuestionIndex));
//			BufferedReader bufferedReader = new BufferedReader(
//					new InputStreamReader(System.in));
//			try {
//				actualAnswer = bufferedReader.readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw new RuntimeException(e);
//			} catch (NumberFormatException e) {
//				System.out.println("Sorry - that's not a number");
//			}
//			if (isRight()) {
//				System.out.println("That's right - well done");
//				increaseScore(pointsPerAnswer);
//			} else {
//				System.out.println("Sorry, that's not right this time");
//			}
//		}
//		System.out.println("End of Game\nYour score is " + getScore()
//				+ " out of " + numberOfQuestionsPerGame);
//	}

//	@Deprecated
//	private void makeGui() {
//		new NextNumberGui(this);
//	}

	private void makeQuestions() {
		Set<Integer> startNumbers = new HashSet<Integer>();
		for (int i = 0; i < numberOfQuestionsPerGame; i++) {
			NumberQuestion question = new NumberQuestion();
			question.setQuestionText("Counting in " + sequenceStep
					+ "s\nWhat number comes next?");
			int[] questionNumbers = new int[numberOfNumericalItemsInQuestion];
			boolean isUniqueNumber = false;
			do {
				questionNumbers[0] = (int) (Math.random() * maxStartingNumber);
				if (options.get(Level.class).getSelectedValue().equalsIgnoreCase("Easy")){
					// EASY level should only provide numbers that are multiples of the Step
					// EG for counting in 3s, the starting numbers should be 0, 3, 6, 9, 15, etc
					
					// "round" down to the nearest multiple of the step by subtracting any "remainder"
					questionNumbers[0] = questionNumbers[0] - (questionNumbers[0] % sequenceStep);
					// NOTE: make sure there are enough possible unique starting numbers 
					// to fill the required number of questions in the game
					// EG if there are 10 questions, and maxStartingNumber is 20, counting in 5s, you can start with:
					// 0, 5, 10, 15 - i.e., only 4 questions. Need to increase maxStartingNumber accordingly
					if (maxStartingNumber < (sequenceStep * numberOfQuestionsPerGame)){
						maxStartingNumber = sequenceStep * numberOfQuestionsPerGame;
					}
				}
				isUniqueNumber = startNumbers.add(questionNumbers[0]);
			} while (isUniqueNumber == false);
			for (int j = 1; j < numberOfNumericalItemsInQuestion; j++) {
				questionNumbers[j] = questionNumbers[j - 1] + sequenceStep;
			}
			question.setQuestionNumbers(questionNumbers);
			int correctAnswer = questionNumbers[questionNumbers.length-1]
					+ sequenceStep;
			question.setCorrectAnswer(correctAnswer);

			// create set of unique possible answers per question
			Set<Integer> uniquePossibleAnswers = new HashSet<Integer>();
			uniquePossibleAnswers.add(correctAnswer);
			for (int j = 1; j < numberOfPossibleAnswers; j++) {
				isUniqueNumber = false;
				do {
					// Random number for possible answers to 1.5 times bigger
					// than the maxStartingNumber
					isUniqueNumber = uniquePossibleAnswers.add((int) (Math
							.random() * (maxStartingNumber * 1.5)));
				} while (isUniqueNumber == false);
			}

			// copy Set of unique possible answers into int[]
			int[] possibleAnswers = new int[numberOfPossibleAnswers];
			int j = 0;
			for (Integer answer : uniquePossibleAnswers) {
				possibleAnswers[j++] = answer;
			}
			question.setPossibleAnswers(possibleAnswers);
			questions.add(question);
		}
	}
	
	private void createDefaultOptions(){
		options.put(LevelOption.class, new LevelOption());
		options.put(SequencePatternOption.class, new SequencePatternOption());
	}

	
//	// TODO This method will not be needed - the GUI will check if the Game implements a Configurable interface (or similar)
//	// and then use that interface method, making this check redundant
//	@Override
//	public boolean hasGameOptions() {
//		if (options == null){
//			return false;
//		}
//		return true;
//	}

	public Map<Class, Option> getOptions() {
		return options;
	}


}
