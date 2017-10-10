package textadventure;

import static org.junit.Assert.*;

import org.junit.Test;
import textadventure.exception.UnknownPlayerException;

class PlayerLocationTrackerTest
{

	@Test
	void getLocation()
	{
		try {
			Player                player                = new MockPlayer();
			Room                  room                  = new MockRoom();
			PlayerLocationTracker playerLocationTracker = new PlayerLocationTracker();
			playerLocationTracker.setLocation(player, room);
			assertSame(room, playerLocationTracker.getLocation(player));
		} catch (Exception e) {
			fail("Should not throw exception.");
		}

		try {
			Player                player                = new MockPlayer();
			PlayerLocationTracker playerLocationTracker = new PlayerLocationTracker();
			playerLocationTracker.getLocation(player); // Should throw exception
			fail("Should throw exception");
		} catch (Exception e) {

		}
	}

	@Test
	void setLocation()
	{
		Player                player                = new MockPlayer();
		Room                  room                  = new MockRoom();
		PlayerLocationTracker playerLocationTracker = new PlayerLocationTracker();

		try {
			playerLocationTracker.getLocation(player);
			fail("Should throw exception."); // Should throw exception
		} catch (UnknownPlayerException e) {
		}

		playerLocationTracker.setLocation(player, room);
		try {
			assertSame(room, playerLocationTracker.getLocation(player));
		} catch (UnknownPlayerException e) {
			fail("Should not throw exception.");
		}
	}

	@Test
	void hasEnd()
	{
		PlayerLocationTracker playerLocationTracker = new PlayerLocationTracker();

		Player a = new MockPlayer();
		Player b = new MockPlayer();

		playerLocationTracker.setLocation(a, new StartingRoom());
		playerLocationTracker.setLocation(b, new StartingRoom());

		assertFalse(playerLocationTracker.hasEnd());

		playerLocationTracker.setLocation(a, new MockRoom());
		playerLocationTracker.setLocation(b, new MockRoom());

		assertFalse(playerLocationTracker.hasEnd());

		playerLocationTracker.setLocation(a, new EndingRoom());

		assertTrue(playerLocationTracker.hasEnd());

		playerLocationTracker.setLocation(b, new EndingRoom());

		assertTrue(playerLocationTracker.hasEnd());
	}
}