package com.unicorntoast.ant.ivy.eclipse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ResolveReport;

import com.unicorntoast.ant.ivy.utils.TypeUtils;

public abstract class ClasspathUtils {

	private static final String ATTR_ORGANISATION = "organisation";

	public static List<IvyPackageClasspathEntry> build(String basepath,ResolveReport report) {
		
		ArrayList<IvyPackageClasspathEntry> result = new ArrayList<IvyPackageClasspathEntry>();
		HashMap<IvyPackageKey, IvyPackage> packages = new HashMap<IvyPackageKey,IvyPackage>();

		for(ArtifactDownloadReport r : report.getAllArtifactsReports()) {
			
			Artifact a = r.getArtifact();
			String name = a.getName();
			String organisation = a.getAttribute(ATTR_ORGANISATION);
			
			if( TypeUtils.jar(a) ) {
				
				IvyPackageKey packageKey = new IvyPackageKey(organisation,name);
				IvyPackage ivyPackage = get(packages,packageKey);
				ivyPackage.jar = r;
				result.add(new IvyPackageClasspathEntry(basepath,ivyPackage));
				
			} else if( TypeUtils.source(a) ) {
				
				if(name.endsWith("-sources"))
					name = name.substring(0,name.length()-"-sources".length());
				IvyPackageKey packageKey = new IvyPackageKey(organisation,name);
				get(packages,packageKey).sources=r;
				
			} else if( TypeUtils.javadoc(a) ) {
				
				if(name.endsWith("-javadoc"))
					name = name.substring(0,name.length()-"-javadoc".length());
				IvyPackageKey packageKey = new IvyPackageKey(organisation,name);
				get(packages,packageKey).javadoc=r;
				
			}
			
		}
		
		return result;
	}
	
	private static IvyPackage get(Map<IvyPackageKey, IvyPackage> packages, IvyPackageKey packageKey) {
		IvyPackage result = packages.get(packageKey);
		if( result==null )
			packages.put(packageKey, result=new IvyPackage());
		return result;
	}
	
}
