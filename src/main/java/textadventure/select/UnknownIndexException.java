package textadventure.select;

import com.google.common.collect.ImmutableSet;

import java.util.List;

/**
 * Thrown when an index provided to the {@link Select#selectIndices(List)} does not exist in the {@link Select}.
 */
public class UnknownIndexException extends SelectException
{

	/**
	 * The complete set of possible indices.
	 */
	private ImmutableSet<Integer> indecies;

	/**
	 * The index that was selected.
	 */
	private Integer selection;

	/**
	 * Creates a new {@link UnknownIndexException}.
	 *
	 * @param select    The {@link Select} where the exception occurred.
	 * @param indices   The complete set of possible indices.
	 * @param selection The index that was selected.
	 */
	public UnknownIndexException(Select select, ImmutableSet<Integer> indices, Integer selection)
	{
		super(select);
	}

	/**
	 * Returns the complete set of possible indices.
	 *
	 * @return The complete set of possible indices.
	 */
	public ImmutableSet<Integer> getIndecies()
	{
		return this.indecies;
	}

	/**
	 * Returns the index that was selected.
	 *
	 * @return The index that was selected.
	 */
	public Integer getSelection()
	{
		return this.selection;
	}
}
