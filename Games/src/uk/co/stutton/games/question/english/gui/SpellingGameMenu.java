package uk.co.stutton.games.question.english.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import uk.co.stutton.games.GameMenu;

/**
 * @author Gary P Stutton
 *
 */
public class SpellingGameMenu extends GameMenu {
	
	@Override
	public JMenuBar buildMenu(){
		JMenuBar jMenuBar = super.buildMenu();
		JMenu fileMenu = jMenuBar.getMenu(0);
		if (!fileMenu.getText().equals("File")){
			throw new IllegalStateException("Expecting to find the file menu");
		}
		JMenuItem loadMenuItem = new JMenuItem("Load Spellings");
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO add the code to launch the loader and create a GUI for loader
			}
		});
		fileMenu.add(loadMenuItem,0);
		return jMenuBar;
		
	}

}
