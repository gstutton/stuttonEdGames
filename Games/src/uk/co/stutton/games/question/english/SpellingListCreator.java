package uk.co.stutton.games.question.english;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uk.co.stutton.games.files.DataLoader;

public class SpellingListCreator {

	public SpellingList create(){
		int index = 1;
		// TODO revise how to use dates
		Calendar startDate = Calendar.getInstance();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		List<String> words = new ArrayList<String>();
		
		words.add("home");
		words.add("get");
		words.add("here");
		
		return new SpellingList(index, new Date(), words);
	}
	
	public void saveList(){
		DataLoader dataLoader = new DataLoader();
		dataLoader.writeToFile("spellings", create());
	}
}
