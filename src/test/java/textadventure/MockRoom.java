package textadventure;

import textadventure.rooms.Room;
import textadventure.rooms.features.RoomFeature;

import java.util.List;

public class MockRoom implements Room
{

	@Override public String getName()
	{
		return null;
	}

	@Override public String getDescription()
	{
		return null;
	}

	@Override public List<RoomFeature> getFeatures()
	{
		return null;
	}
}
