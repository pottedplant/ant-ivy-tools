package com.unicorntoast.ant.ivy.utils;

import org.apache.ivy.core.report.ArtifactDownloadReport;

public abstract class IvyPathUtils {

	public static String path(String basepath,ArtifactDownloadReport a,String pathprefix) {
		String path = a.getLocalFile().getAbsolutePath();
		if( basepath!=null && path.startsWith(basepath) )
			path = path.substring(basepath.length());
		if( pathprefix==null )
			return path;
		return pathprefix + path;
	}

	public static CharSequence path(String basepath,ArtifactDownloadReport a) {
		return path(basepath,a,null);
	}

}
