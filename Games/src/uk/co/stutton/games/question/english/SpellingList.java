package uk.co.stutton.games.question.english;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpellingList {

	private int listId;
	private Date weekStartDate;
	private List<String> words = new ArrayList<String>();
	private int currentWordIndex = 0;
	
	public SpellingList(int listId, Date weekStartDate, List<String> words){
		this.listId = listId;
		this.weekStartDate = weekStartDate;
		this.words = words;
	}
	
	/*
	 * @return String the next word, if there is one, otherwise the last word
	 */
	public String getNextWord(){
		if (currentWordIndex < words.size()){
			return words.get(currentWordIndex++);
		}
		return words.get(words.size());
	}
	
	/*
	 * @return String the first word 
	 */
	public String getFirstWord(){
		currentWordIndex = 0;
		return getNextWord();
	}

	/*
	 * @return String the previous word if it exists, otherwise the first word 
	 */
	public String getPrevWord(){
		if(currentWordIndex > 0){
			currentWordIndex--;
		}
		return getNextWord();
	}
	
	
	
}
