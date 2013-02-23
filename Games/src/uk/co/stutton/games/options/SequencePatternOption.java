package uk.co.stutton.games.options;

import java.util.ArrayList;
import java.util.List;

public class SequencePatternOption implements Option {

	private String name = "Counting Pattern";
	private List<String> possibleValues = new ArrayList<String>(); 
	private String selectedValue;
	
	public SequencePatternOption() {
		possibleValues.add("2");
		possibleValues.add("3");
		possibleValues.add("5");
		possibleValues.add("10");
		selectedValue = "2";
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
