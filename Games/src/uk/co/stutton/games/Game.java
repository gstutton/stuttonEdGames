package uk.co.stutton.games;

public abstract class Game implements Saveable {

	private int score = 0;
	private String title;
	
	/* (non-Javadoc)
	 * @see uk.co.stutton.games.Saveable#saveGame()
	 */
	@Override
	public void save() {
		System.out.println("Saving game");
	}

	/* (non-Javadoc)
	 * @see uk.co.stutton.games.Saveable#loadGame()
	 */
	@Override
	public void load() {
		System.out.println("Loading game");
	}

	public abstract void playGame();

	public void quitGame() {
		System.out.println("Quiting game");
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
//	public abstract boolean hasGameOptions();
}
