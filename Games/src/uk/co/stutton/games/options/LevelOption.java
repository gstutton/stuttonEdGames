/**
 * 
 */
package uk.co.stutton.games.options;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gary Stutton
 *
 */
public class LevelOption implements Option {
//	private enum Level{
//		EASY("Easy"), MEDIUM("Medium"), HARD("Hard");
//		
//		private Level(final String level){
//			this.level = level;
//		}
//		
//		private String level;
//		
//		public String toString(){
//			return level;
//		}
//		
//		public static Level fromString(String text) {
//		    if (text != null) {
//		      for (Level l : Level.values()) {
//		        if (text.equalsIgnoreCase(l.level)) {
//		          return l;
//		        }
//		      }
//		    }
//		    return null;
//		}
//	}
	
	private String name = "Level";
	private List<String> possibleValues = new ArrayList<String>(); 
	private String selectedValue;
	
	public LevelOption(){
		possibleValues.add("Easy");
		possibleValues.add("Medium");
		possibleValues.add("Hard");
		selectedValue = "Easy";
	}

	@Override
	public void setSelectedValue(String value){
		if (possibleValues.contains(value)){
			selectedValue = value;
		} else {
			throw new UnsupportedOperationException("The selected value for this option is not an allowed value");
		}
	}

	@Override
	public String getSelectedValue() {
		return selectedValue;
	}

	@Override
	public List<String> getAllowedValues() {
		return possibleValues;
	}

	@Override
	public String getName() {
		return name;
	}

}
