/**
 * 
 */
package uk.co.stutton.games.question;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gary Stutton
 *
 */
public class WordQuestion {
	
	private String questionText;     // Any textual part of the question
	private String word;
	private List<String> possibleAnswers = new ArrayList<String>();  // For multi choice questions a list of possible answers, including the correct answer
	private String correctAnswer;

	/**
	 * @return the questionText
	 */
	public String getQuestionText() {
		return questionText;
	}
	/**
	 * @param questionText the questionText to set
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	/**
	 * @return the possibleAnswers
	 */
	public List<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	/**
	 * @param possibleAnswers the possibleAnswers to set
	 */
	public void setPossibleAnswers(List<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}
	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	

}
