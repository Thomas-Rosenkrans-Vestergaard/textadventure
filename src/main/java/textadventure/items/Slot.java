package textadventure.items;

import java.util.ArrayList;
import java.util.List;

public class Slot<T extends Item>
{

	/**
	 * The type of {@link Item} in the {@link Slot}.
	 */
	private Class<? extends T> type;

	/**
	 * The maximum amount of {@link Item} that can be in the slot.
	 */
	private final int maxSize;

	/**
	 * The {@link Item}s in the {@link Slot}.
	 */
	private List<T> items = new ArrayList<>();

	/**
	 * Creates a new {@link Slot}.
	 *
	 * @param type    The type of {@link Item} in the {@link Slot}.
	 * @param maxSize The maximum amount of {@link Item} that can be in the slot. <code>-1</code> indicates no limit.
	 */
	public Slot(Class<? extends T> type, int maxSize)
	{
		if (maxSize < -1)
			throw new IllegalArgumentException("maxSize must be greater than -1.");

		this.type = type;
		this.maxSize = maxSize;
	}

	/**
	 * Returns the type of {@link Item} in the {@link Slot}.
	 *
	 * @return The type of {@link Item} in the {@link Slot}.
	 */
	public Class<? extends T> getType()
	{
		return this.type;
	}

	/**
	 * Returns the maximum amount of {@link Item} that can be in the slot.
	 *
	 * @return The maximum amount of {@link Item} that can be in the slot.
	 */
	public int getMaxSize()
	{
		return this.maxSize;
	}

	/**
	 * Returns <code>true</code> if the {@link Slot} is empty, <code>false</code> otherwise.
	 *
	 * @return <code>true</code> if the {@link Slot} is empty, <code>false</code> otherwise.
	 */
	public boolean isEmpty()
	{
		return items.size() == 0;
	}

	/**
	 * Returns <code>true</code> if the {@link Slot} is full, <code>false</code> otherwise.
	 *
	 * @return <code>true</code> if the {@link Slot} is full, <code>false</code> otherwise.
	 */
	public boolean isFull()
	{
		return items.size() == maxSize;
	}
}
