package textadventure.actions;

import org.junit.Test;
import textadventure.characters.Character;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ActionTest
{

	@Test
	public void testAbstractAction() throws Exception
	{
		test(() -> new AbstractAction()
		{
			@Override public void perform(Character character, String[] arguments, ActionResponses callback)
			{

			}
		});
	}

	public static void test(Supplier<Action> actionSupplier) throws Exception
	{
		onSuccess(actionSupplier.get());
		onException(actionSupplier.get());
		getException(actionSupplier.get());
		setException(actionSupplier.get());
		hasException(actionSupplier.get());
	}

	private static void onSuccess(Action action) throws Exception
	{
		Runnable mocked = mock(Runnable.class);

		action.onSuccess(mocked);
		verify(mocked, times(1)).run();

		action.setException(new Exception());
		action.onSuccess(mocked);
		verify(mocked, times(1)).run();
	}

	private static void onException(Action action) throws Exception
	{
		Consumer<Exception> mocked = mock(Consumer.class);

		action.onException(Exception.class, mocked);
		verify(mocked, times(0)).accept(any(Exception.class));

		action.setException(new Exception());
		action.onException(Exception.class, mocked);
		verify(mocked, times(1)).accept(any(Exception.class));
	}

	private static void getException(Action action) throws Exception
	{
		Exception expected = new Exception();

		action.setException(expected);
		assertSame(expected, action.getException());
	}

	private static void setException(Action action) throws Exception
	{
		Exception expected = new Exception();
		action.setException(expected);
		assertSame(expected, action.getException());
	}

	private static void hasException(Action action) throws Exception
	{
		action.setException(new Exception());
		assertTrue(action.hasException(Exception.class));
		assertTrue(action.hasException());
		assertFalse(action.hasException(IllegalStateException.class));

		action.setException(new IllegalStateException());
		assertTrue(action.hasException(IllegalStateException.class));
		assertTrue(action.hasException(Exception.class));
		assertTrue(action.hasException());
	}
}