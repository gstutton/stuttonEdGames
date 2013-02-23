/**
 * 
 */
package uk.co.stutton.games.question.english;

import java.util.ArrayList;
import java.util.List;

import uk.co.stutton.games.question.WordQuestion;

/**
 * @author Gary Stutton Creates a simple game for spelling word
 */
public class SpellingGame {

	private int pointsPerAnswer = 1;
	private int correctScore;
	private int incorrectScore;
	private int numberOfQuestionsPerGame = 5;
	private List<WordQuestion> questions = new ArrayList<WordQuestion>(
			getNumberOfQuestionsPerGame());
	private int currentQuestionIndex = 0;

	public SpellingGame() {
		makeQuestions();
	}

	public boolean isRight(String question, String userAnswer) {
		if (userAnswer.equalsIgnoreCase(question)) {
			setCorrectScore(getCorrectScore() + pointsPerAnswer);
			return true;
		}
		setIncorrectScore(getIncorrectScore() + pointsPerAnswer);
		return false;
	}

	public WordQuestion getNextQuestion() {
		if (currentQuestionIndex < questions.size()){
			return questions.get(currentQuestionIndex++);
		}
		return null;
	}


	private void makeQuestions() {
		List<WordQuestion> words = new ArrayList<WordQuestion>();
		words.add(addWord("look"));
		words.add(addWord("at"));
		words.add(addWord("is"));
		words.add(addWord("this"));
		words.add(addWord("me"));
		words.add(addWord("the"));
		words.add(addWord("in"));
		words.add(addWord("house"));
		words.add(addWord("for"));
		words.add(addWord("on"));
		words.add(addWord("big"));
		words.add(addWord("it"));
		words.add(addWord("said"));
		words.add(addWord("will"));
		words.add(addWord("he"));
		words.add(addWord("help"));
		words.add(addWord("was"));
		words.add(addWord("not"));
		words.add(addWord("him"));
		words.add(addWord("his"));
		words.add(addWord("went"));
		words.add(addWord("can"));
		words.add(addWord("no"));
		words.add(addWord("and"));
		words.add(addWord("come"));
		words.add(addWord("if"));
		words.add(addWord("you"));
		words.add(addWord("got"));
		words.add(addWord("with"));
		words.add(addWord("very"));
		
		for (int i=0; i<getNumberOfQuestionsPerGame();i++){
			int index = (int) (Math.random() * words.size());
			questions.add(words.get(index));
			words.remove(index);
		}
	}

	private WordQuestion addWord(String word) {
		WordQuestion question = new WordQuestion();
		question.setWord(word);
		question.setCorrectAnswer(word);
		question.setQuestionText("Spelling the word:");
		return question;
	}

	/**
	 * @return the correct score
	 */
	public int getCorrectScore() {
		return correctScore;
	}

	/**
	 * @param correctScore the score to set
	 */
	private void setCorrectScore(int correctScore) {
		this.correctScore = correctScore;
	}

	/**
	 * @return the incorrect score
	 */
	public int getIncorrectScore() {
		return incorrectScore;
	}
	
	/**
	 * @param incorrectScore the score to set
	 */
	private void setIncorrectScore(int incorrectScore) {
		this.incorrectScore = incorrectScore;
	}

	/**
	 * @return the numberOfQuestionsPerGame
	 */
	public int getNumberOfQuestionsPerGame() {
		return numberOfQuestionsPerGame;
	}

	public SpellingGame newGame(){
		return new SpellingGame();
	}

}
