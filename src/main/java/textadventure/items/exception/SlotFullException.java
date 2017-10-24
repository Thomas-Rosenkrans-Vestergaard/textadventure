package textadventure.items.exception;

import textadventure.items.Inventory;
import textadventure.items.Slot;

public class SlotFullException extends SlotException
{
	public SlotFullException(Inventory inventory, Slot slot)
	{
		super(inventory, slot);
	}
}
