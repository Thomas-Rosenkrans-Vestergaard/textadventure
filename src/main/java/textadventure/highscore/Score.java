package textadventure.highscore;

/**
 * Represents an entry in a high-score file.
 */
public class Score
{

	/**
	 * The name on the {@link Score}.
	 */
	private String name;

	/**
	 * The value on the {@link Score}.
	 */
	private int score;

	/**
	 * The {@link Outcome} of the {@link textadventure.Game}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link Score}.
	 *
	 * @param name    The name on the {@link Score}.
	 * @param score   The value on the {@link Score}.
	 * @param outcome The {@link Outcome} of the {@link textadventure.Game}.
	 */
	public Score(String name, int score, Outcome outcome)
	{
		this.name = name;
		this.score = score;
		this.outcome = outcome;
	}

	/**
	 * Returns the name on the {@link Score}.
	 *
	 * @return The name on the {@link Score}.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Returns the value on the {@link Score}.
	 *
	 * @return The value on the {@link Score}.
	 */
	public int getScore()
	{
		return this.score;
	}

	/**
	 * Returns the {@link Outcome} of the {@link textadventure.Game}.
	 *
	 * @return The {@link Outcome} of the {@link textadventure.Game}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Converts the {@link Score} to a string representation.
	 *
	 * @return The string representation.
	 */
	@Override public String toString()
	{
		return String.format("%s,%d,%s", name, score, outcome);
	}
}
