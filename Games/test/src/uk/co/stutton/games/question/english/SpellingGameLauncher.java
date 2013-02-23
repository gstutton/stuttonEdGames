/**
 * 
 */
package uk.co.stutton.games.question.english;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import uk.co.stutton.games.question.english.gui.SimpleSpellingGui;


/**
 * @author Gary Stutton
 *
 */
public class SpellingGameLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
			    public void run() {
			    	new SimpleSpellingGui(new SpellingGame());
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
