/**
 * 
 */
package uk.co.stutton.games.question;

/**
 * @author Gary Stutton
 *
 */
public class NumberQuestion {
	
	private String questionText;     // Any textual part of the question
	private int[] questionNumbers;  // Any pattern of numbers forming part of the question
	private int[] possibleAnswers;  // For multi choice questions a list of possible answers, including the correct answer
	private int correctAnswer;

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
	 * @return the questionNumbers
	 */
	public int[] getQuestionNumbers() {
		return questionNumbers;
	}
	/**
	 * @param questionNumbers the questionNumbers to set
	 */
	public void setQuestionNumbers(int[] questionNumbers) {
		this.questionNumbers = questionNumbers;
	}
	/**
	 * @return the possibleAnswers
	 */
	public int[] getPossibleAnswers() {
		return possibleAnswers;
	}
	/**
	 * @param possibleAnswers the possibleAnswers to set
	 */
	public void setPossibleAnswers(int[] possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}
	/**
	 * @return the correctAnswer
	 */
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	

}
