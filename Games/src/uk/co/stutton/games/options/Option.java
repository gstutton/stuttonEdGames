package uk.co.stutton.games.options;

import java.util.List;


/**
 * @author Gary Stutton
 *
 */
/**
 * @author Gary Stutton
 *
 */

// TODO decide whether to make Option an abstract class, and somehow implement some of the getters here
// or to make it an interface
public interface Option {
	
	/**
	 * 
	 * @param value
	 *       Sets the currently selected value for the Option
	 */
	public abstract void setSelectedValue(String value);
	
	/**
	 *  @return String
	 *          Representing the currently selected value for the Option
	 */
	public abstract String getSelectedValue();

	/**
	 *  Returns List<String>
	 *          Representing all the possible allowed values for the Option
	 */
	public abstract List<String> getAllowedValues();

//	/**
//	 * 
//	 * @param allowedValues
//	 *          Representing all the possible allowed values for the Option
//	 */
//	public abstract void setAllowedValues(List<String> allowedValues);
	
	/**
	 * 
	 * @return String
	 *         Representing the label or name for the Option
	 */
	public abstract String getName();

//	/**
//	 * 
//	 * @param optionName
//	 *         Representing the label or name for the Option
//	 */
//	public abstract void setName(String optionName);
	

}
