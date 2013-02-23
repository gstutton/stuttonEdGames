package uk.co.stutton.games.question.english;

public class TestSpellingListCreator {

	public static void main(String[] args){
		System.out.println("About to create spelling list");
		SpellingListCreator creator = new SpellingListCreator();
		creator.saveList();
		System.out.println("Finished !!");
		
	}
}
