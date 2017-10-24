package textadventure.items.exception;

import textadventure.items.Inventory;
import textadventure.items.Slot;

public class SlotTypeException extends SlotException
{
	public SlotTypeException(Inventory inventory, Slot slot)
	{
		super(inventory, slot);
	}
}
