package com.unicorntoast.ant.ivy.eclipse;

import java.io.PrintStream;

import com.unicorntoast.ant.ivy.utils.IvyPathUtils;

public class IvyPackageClasspathEntry implements ClasspathEntry {

	private final String basepath;
	private final IvyPackage ivyPackage;

	public IvyPackageClasspathEntry(String basepath, IvyPackage ivyPackage) {
		this.basepath = basepath;
		this.ivyPackage = ivyPackage;
	}

	@Override
	public void write(PrintStream out) {
		out.append("  <classpathentry kind=\"lib\" path=\"").append(IvyPathUtils.path(basepath,ivyPackage.jar)).append("\" ");

		if( ivyPackage.sources!=null )
			out.append(" sourcepath=\"").append(IvyPathUtils.path(basepath,ivyPackage.sources)).append("\" ");

		if( ivyPackage.javadoc==null ) {
			out.append(" />\n");
			return;
		}

		out.append(">\n");
		out.append("    <attributes><attribute name=\"javadoc_location\" value=\"").append(IvyPathUtils.path(basepath,ivyPackage.javadoc)).append("\" />").append("</attributes>\n");
		out.append("  </classpathentry>\n");
	}

}
