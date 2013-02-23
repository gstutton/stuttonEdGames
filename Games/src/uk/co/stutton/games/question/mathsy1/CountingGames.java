/**
 * 
 */
package uk.co.stutton.games.question.mathsy1;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import uk.co.stutton.games.question.mathsy1.gui.GameChoiceGui;


/**
 * @author Gary Stutton
 *
 */
public class CountingGames {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
			    public void run() {
			    	new GameChoiceGui();
			    }
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		

}
