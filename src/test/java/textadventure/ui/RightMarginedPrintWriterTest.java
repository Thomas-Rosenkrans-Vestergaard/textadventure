package textadventure.ui;

import org.junit.Test;

import static org.junit.Assert.*;

public class RightMarginedPrintWriterTest
{
	@Test
	public void write() throws Exception
	{
		RightMarginedPrintWriter printer = new RightMarginedPrintWriter(System.out, true, 10);
		printer.print("Thomas Thomas");
	}
}