package textadventure.ui;

import java.io.*;
import java.nio.charset.Charset;

public class RightMarginedPrintWriter extends PrintWriter
{

	/**
	 * The maximum length of the lines printed by the {@link RightMarginedPrintWriter}.
	 */
	private int maxLineLength;
	private int currentLineLength;
	private char delimiter = ' ';

	/**
	 * Creates a new PrintWriter, without automatic line flushing.
	 *
	 * @param out A character-output stream
	 */
	public RightMarginedPrintWriter(Writer out, int maxLineLength)
	{
		super(out);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Creates a new PrintWriter.
	 *
	 * @param out       A character-output stream
	 * @param autoFlush A boolean; if true, the <tt>println</tt>,
	 *                  <tt>printf</tt>, or <tt>format</tt> methods will
	 */
	public RightMarginedPrintWriter(Writer out, boolean autoFlush, int maxLineLength)
	{
		super(out, autoFlush);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Creates a new PrintWriter, without automatic line flushing, from an
	 * existing OutputStream.  This convenience constructor creates the
	 * necessary intermediate OutputStreamWriter, which will convert characters
	 * into bytes using the default character encoding.
	 *
	 * @param out An output stream
	 * @see OutputStreamWriter#OutputStreamWriter(OutputStream)
	 */
	public RightMarginedPrintWriter(OutputStream out, int maxLineLength)
	{
		super(out);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Creates a new PrintWriter from an existing OutputStream.  This
	 * convenience constructor creates the necessary intermediate
	 * OutputStreamWriter, which will convert characters into bytes using the
	 * default character encoding.
	 *
	 * @param out       An output stream
	 * @param autoFlush A boolean; if true, the <tt>println</tt>,
	 *                  <tt>printf</tt>, or <tt>format</tt> methods will
	 *                  flush the output buffer
	 * @see OutputStreamWriter#OutputStreamWriter(OutputStream)
	 */
	public RightMarginedPrintWriter(OutputStream out, boolean autoFlush, int maxLineLength)
	{
		super(out, autoFlush);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Creates a new PrintWriter, without automatic line flushing, with the
	 * specified file name.  This convenience constructor creates the necessary
	 * intermediate {@link OutputStreamWriter OutputStreamWriter},
	 * which will encode characters using the {@linkplain
	 * Charset#defaultCharset() default charset} for this
	 * instance of the Java virtual machine.
	 *
	 * @param fileName The name of the file to use as the destination of this writer.
	 *                 If the file exists then it will be truncated to zero size;
	 *                 otherwise, a new file will be created.  The output will be
	 *                 written to the file and is buffered.
	 * @throws FileNotFoundException If the given string does not denote an existing, writable
	 *                               regular file and a new regular file of that name cannot be
	 *                               created, or if some other error occurs while opening or
	 *                               creating the file
	 * @throws SecurityException     If a security manager is present and {@link
	 *                               SecurityManager#checkWrite checkWrite(fileName)} denies write
	 *                               access to the file
	 * @since 1.5
	 */
	public RightMarginedPrintWriter(String fileName, int maxLineLength) throws FileNotFoundException
	{
		super(fileName);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Creates a new PrintWriter, without automatic line flushing, with the
	 * specified file name and charset.  This convenience constructor creates
	 * the necessary intermediate {@link OutputStreamWriter
	 * OutputStreamWriter}, which will encode characters using the provided
	 * charset.
	 *
	 * @param fileName The name of the file to use as the destination of this writer.
	 *                 If the file exists then it will be truncated to zero size;
	 *                 otherwise, a new file will be created.  The output will be
	 *                 written to the file and is buffered.
	 * @param csn      The name of a supported {@linkplain Charset
	 *                 charset}
	 * @throws FileNotFoundException        If the given string does not denote an existing, writable
	 *                                      regular file and a new regular file of that name cannot be
	 *                                      created, or if some other error occurs while opening or
	 *                                      creating the file
	 * @throws SecurityException            If a security manager is present and {@link
	 *                                      SecurityManager#checkWrite checkWrite(fileName)} denies write
	 *                                      access to the file
	 * @throws UnsupportedEncodingException If the named charset is not supported
	 * @since 1.5
	 */
	public RightMarginedPrintWriter(String fileName, String csn, int maxLineLength) throws FileNotFoundException,
			UnsupportedEncodingException
	{
		super(fileName, csn);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Creates a new PrintWriter, without automatic line flushing, with the
	 * specified file.  This convenience constructor creates the necessary
	 * intermediate {@link OutputStreamWriter OutputStreamWriter},
	 * which will encode characters using the {@linkplain
	 * Charset#defaultCharset() default charset} for this
	 * instance of the Java virtual machine.
	 *
	 * @param file The file to use as the destination of this writer.  If the file
	 *             exists then it will be truncated to zero size; otherwise, a new
	 *             file will be created.  The output will be written to the file
	 *             and is buffered.
	 * @throws FileNotFoundException If the given file object does not denote an existing, writable
	 *                               regular file and a new regular file of that name cannot be
	 *                               created, or if some other error occurs while opening or
	 *                               creating the file
	 * @throws SecurityException     If a security manager is present and {@link
	 *                               SecurityManager#checkWrite checkWrite(file.getPath())}
	 *                               denies write access to the file
	 * @since 1.5
	 */
	public RightMarginedPrintWriter(File file, int maxLineLength) throws FileNotFoundException
	{
		super(file);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Creates a new PrintWriter, without automatic line flushing, with the
	 * specified file and charset.  This convenience constructor creates the
	 * necessary intermediate {@link OutputStreamWriter
	 * OutputStreamWriter}, which will encode characters using the provided
	 * charset.
	 *
	 * @param file The file to use as the destination of this writer.  If the file
	 *             exists then it will be truncated to zero size; otherwise, a new
	 *             file will be created.  The output will be written to the file
	 *             and is buffered.
	 * @param csn  The name of a supported {@linkplain Charset
	 *             charset}
	 * @throws FileNotFoundException        If the given file object does not denote an existing, writable
	 *                                      regular file and a new regular file of that name cannot be
	 *                                      created, or if some other error occurs while opening or
	 *                                      creating the file
	 * @throws SecurityException            If a security manager is present and {@link
	 *                                      SecurityManager#checkWrite checkWrite(file.getPath())}
	 *                                      denies write access to the file
	 * @throws UnsupportedEncodingException If the named charset is not supported
	 * @since 1.5
	 */
	public RightMarginedPrintWriter(File file, String csn, int maxLineLength) throws FileNotFoundException,
			UnsupportedEncodingException
	{
		super(file, csn);

		this.maxLineLength = maxLineLength;
	}

	/**
	 * Writes a portion of a string.
	 *
	 * @param s   A String
	 * @param off Offset from which to start writing characters
	 * @param len Number of characters to write
	 */
	@Override public void write(String s, int off, int len)
	{
		System.out.println("Printing " + s);
		System.out.println(off);

		if (len + currentLineLength <= maxLineLength) {
			System.out.println("small " + s.substring(s.length() - off));
			super.write(s.substring(s.length() - off));
			this.currentLineLength += len;
			return;
		}

		int max   = maxLineLength - currentLineLength;
		int nextIndex;
		int index = off;

		while (true) {
			nextIndex = s.indexOf(delimiter, index);
			if (nextIndex > max || nextIndex == -1 || nextIndex == index)
				break;
			index = nextIndex;
		}

		super.write(s, off, off + index);
		super.println();
		this.currentLineLength = 0;
		this.write(s, nextIndex, len - off - index);
	}
}
