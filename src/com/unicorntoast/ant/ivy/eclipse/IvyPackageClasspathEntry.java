package com.unicorntoast.ant.ivy.eclipse;

import java.io.PrintStream;

import org.apache.ivy.core.report.ArtifactDownloadReport;

public class IvyPackageClasspathEntry implements ClasspathEntry {

	private final String basepath;
	private final IvyPackage ivyPackage;

	public IvyPackageClasspathEntry(String basepath, IvyPackage ivyPackage) {
		this.basepath = basepath;
		this.ivyPackage = ivyPackage;
	}

	@Override
	public void write(PrintStream out) {
		out.append("  <classpathentry kind=\"lib\" path=\"").append(path(ivyPackage.jar)).append("\" ");
		
		if( ivyPackage.sources!=null )
			out.append(" sourcepath=\"").append(path(ivyPackage.sources)).append("\" ");
		
		if( ivyPackage.javadoc==null ) {
			out.append(" />\n");
			return;
		}
		
		out.append(">\n");
		out.append("    <attributes><attribute name=\"javadoc_location\" value=\"").append(path(ivyPackage.javadoc)).append("\" />").append("</attributes>\n");
		out.append("  </classpathentry>\n");
	}

	private String path(ArtifactDownloadReport a) {
		String path = a.getLocalFile().getAbsolutePath();
		if( basepath!=null && path.startsWith(basepath) )
			path = path.substring(basepath.length());
		return path;
	}

}
