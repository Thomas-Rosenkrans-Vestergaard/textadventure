package textadventure.highscore;

@FunctionalInterface
public interface HighScoreResponse
{

	/**
	 * Respond to whether or not to save a high-score.
	 *
	 * @param save Whether or not to save the high-score.
	 * @param name The name the high-score should be added under.
	 */
	void respond(boolean save, String name);
}
