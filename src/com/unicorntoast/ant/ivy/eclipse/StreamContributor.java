package com.unicorntoast.ant.ivy.eclipse;

import java.io.IOException;
import java.io.PrintStream;

public interface StreamContributor {

	void write(PrintStream out) throws IOException;
	
}
