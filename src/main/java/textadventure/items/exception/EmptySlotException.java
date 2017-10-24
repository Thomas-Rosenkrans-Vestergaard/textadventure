package textadventure.items.exception;

import textadventure.items.Inventory;
import textadventure.items.Slot;

public class EmptySlotException extends SlotException
{
	public EmptySlotException(Inventory inventory, Slot slot)
	{
		super(inventory, slot);
	}
}
