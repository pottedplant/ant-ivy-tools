package com.unicorntoast.ant.ivy.eclipse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public abstract class EclipseUtils {

	public static void createClasspath(String output,List<? extends ClasspathEntry> classpath) throws FileNotFoundException {
		
		PrintStream ps = new PrintStream(new FileOutputStream(output));
		try {
			
			ps.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			ps.println("<classpath>");
			
			for(ClasspathEntry e : classpath)
				e.write(ps);
			
			ps.println("</classpath>\n");
			
		} finally {
			ps.close();
		}
		ps.close();

	}

}
