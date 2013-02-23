/**
 * 
 */
package uk.co.stutton.games.question.english.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Gary Stutton
 * 
 */

public class OLDSimpleSpellingQuestionPanel extends JPanel{
	private JLabel wordToSpell = new JLabel();
	private JButton spellItButton = new JButton();
	private OLDSpellingGui gui;
	
	public OLDSimpleSpellingQuestionPanel(OLDSpellingGui gui) {
		this.gui = gui;
		initialisePanel();
	}


	public void initialisePanel() {
		spellItButton = new JButton("Spell it");
		
		add(wordToSpell);
		add(spellItButton);


		spellItButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gui.notifyDone(); // callback on the calling Gui; TODO how to say what 'object' called done?
			}
		});

	}

	public void displayQuestion() {
		wordToSpell.setText(gui.getQuestionWord());
	}

}

