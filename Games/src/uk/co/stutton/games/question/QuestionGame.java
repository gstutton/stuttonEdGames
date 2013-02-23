/**
 * 
 */
package uk.co.stutton.games.question;

import uk.co.stutton.games.Game;

/**
 * @author Gary Stutton
 *
 */
public abstract class QuestionGame extends Game{
	
	public abstract NumberQuestion getNextQuestion();

	public abstract boolean isRight(NumberQuestion question, String userAnswer);
		
}
