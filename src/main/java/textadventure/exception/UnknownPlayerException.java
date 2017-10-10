package textadventure.exception;

import textadventure.Player;

public class UnknownPlayerException extends GameException
{
	private Player unknownPlayer;

	public UnknownPlayerException(Player unknownPlayer)
	{
		this.unknownPlayer = unknownPlayer;
	}

	public Player getUnknownPlayer()
	{
		return this.unknownPlayer;
	}
}
