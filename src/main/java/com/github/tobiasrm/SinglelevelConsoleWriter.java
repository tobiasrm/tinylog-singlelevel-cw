package com.github.tobiasrm;

import java.io.PrintStream;
import java.util.EnumSet;
import java.util.Set;

import org.pmw.tinylog.Configuration;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.LogEntry;
import org.pmw.tinylog.writers.LogEntryValue;
import org.pmw.tinylog.writers.PropertiesSupport;
import org.pmw.tinylog.writers.Property;
import org.pmw.tinylog.writers.Writer;

/**  Extension of the the tinylog ConsoleWriter enabling to print the level specified in the config.
 * @author Tobias R. Mayer (trmayer@me.com)
 */
@PropertiesSupport(name = "singlelevelconsole", properties = 
{
		@Property(name = "stream", type = String.class, optional = true),
		@Property(name = "singlelevel", type = boolean.class, optional = true) 
})
public final class SinglelevelConsoleWriter implements Writer {

	private final PrintStream err;
	private final PrintStream out;

	private final boolean isSinglelevel;
	private Level level;


	// --------------------------------------------------------------------

	public SinglelevelConsoleWriter() {
		err = System.err;
		out = System.out;
		isSinglelevel = false;		
	}

	// --------------------------------------------------------------------

	/**
	 * @param stream
	 *            Print stream for outputting log entries
	 */
	public SinglelevelConsoleWriter(final PrintStream stream) {
		err = stream;
		out = stream;
		this.isSinglelevel = false;
	}

	// --------------------------------------------------------------------

	SinglelevelConsoleWriter(final String stream){
		this(stream, false);
	}

	// --------------------------------------------------------------------

	/** 
	 * @param stream Name of system print stream for outputting log entries ("out" for {@link System.out} or "err" for {@link System.err})
	 * @param singlelevel if the writer shall be restricted to his log level 
	 */
	SinglelevelConsoleWriter(final String stream, final boolean singlelevel) {

		if (stream == null) {
			err = System.err;
			out = System.out;
		} else if ("err".equalsIgnoreCase(stream)) {
			err = System.err;
			out = System.err;
		} else if ("out".equalsIgnoreCase(stream)) {
			err = System.out;
			out = System.out;
		} else {
			throw new IllegalArgumentException("Stream must be \"out\" or \"err\", \"" + stream + "\" is not a valid stream name");
		}

		this.isSinglelevel = singlelevel;
	}

	// --------------------------------------------------------------------

	public Set<LogEntryValue> getRequiredLogEntryValues() {
		return EnumSet.of(LogEntryValue.LEVEL, LogEntryValue.RENDERED_LOG_ENTRY);
	}

	// --------------------------------------------------------------------

	public void init(final Configuration configuration) {
		level = configuration.getWriterLevel(this);
		
	}

	// --------------------------------------------------------------------

	public void write(final LogEntry logEntry) {
		// if singlelevel is activated (true):  print only the log of the level specified in the config 
		if( isSinglelevel ) {
			if( logEntry.getLevel() == level )  
				getPrintStream( logEntry.getLevel()).print(logEntry.getRenderedLogEntry() );
		} else
			// if singlelevel is deactivated (false or not specified): print all logs
			getPrintStream( logEntry.getLevel()).print(logEntry.getRenderedLogEntry() );
	}

	// --------------------------------------------------------------------

	public void flush() {
		// Do nothing
	}

	// --------------------------------------------------------------------

	public void close() {
		// Do nothing
	}

	// --------------------------------------------------------------------

	private PrintStream getPrintStream(final Level level) {
		if (level == Level.ERROR || level == Level.WARNING) {
			return err;
		} else {
			return out;
		}
	}

}
