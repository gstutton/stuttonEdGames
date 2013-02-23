package uk.co.stutton.games.options;

import java.util.Map;

public interface CustomisableGame {
	
	public abstract Map<Class, Option> getOptions();
}
