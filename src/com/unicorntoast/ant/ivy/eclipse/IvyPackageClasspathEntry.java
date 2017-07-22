package com.unicorntoast.ant.ivy.eclipse;

import java.io.PrintStream;

import com.unicorntoast.ant.ivy.utils.IvyPathUtils;

public class IvyPackageClasspathEntry implements ClasspathEntry {

	private final String basepath;
	private final IvyPackage ivyPackage;
	private final boolean exported;

	public IvyPackageClasspathEntry(String basepath, IvyPackage ivyPackage, boolean exported) {
		this.basepath = basepath;
		this.ivyPackage = ivyPackage;
		this.exported = exported;
	}

	@Override
	public void write(PrintStream out) {
		out.append("\t<classpathentry kind=\"lib\" path=\"").append(IvyPathUtils.path(basepath,ivyPackage.jar)).append("\"");
		
		if( exported )
			out.append(" exported=\"true\"");

		if( ivyPackage.sources!=null )
			out.append(" sourcepath=\"").append(IvyPathUtils.path(basepath,ivyPackage.sources)).append("\"");

		if( ivyPackage.javadoc==null ) {
			out.append("/>\n");
			return;
		}

		out.append(">\n");
		out.append("\t\t<attributes>\n\t\t\t<attribute name=\"javadoc_location\" value=\"").append(IvyPathUtils.path(basepath,ivyPackage.javadoc)).append("\"/>\n").append("\t\t</attributes>\n");
		out.append("\t</classpathentry>\n");
	}

}
