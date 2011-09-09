package com.unicorntoast.ant.ivy.eclipse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public abstract class EclipseUtils {

	public static void createClasspath(String output,List<? extends StreamContributor> classpath) throws IOException {
		
		PrintStream ps = new PrintStream(new FileOutputStream(output));
		try {
			
			ps.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			ps.print("<classpath>\n");
			
			for(StreamContributor c : classpath)
				c.write(ps);
			
			ps.print("</classpath>\n");
			
		} finally {
			ps.close();
		}

	}

}
