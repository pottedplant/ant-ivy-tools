package com.unicorntoast.ant.ivy.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.util.FileUtil;

import com.unicorntoast.ant.ivy.utils.TypeUtils;

public abstract class ExportUtils {
	
	private static final String[] ATTRS = new String[]{
		"organisation",
		"module",
		"type",
		"artifact",
		"revision",
		"ext",
	};

	public static void export(ResolveReport resolved,String output,String classpath,String pattern,String pathprefix) throws IOException {
		
		File outputPath = new File(output);
		
		if( !outputPath.exists() )
			outputPath.mkdirs();
		
		if( !outputPath.exists() || !outputPath.isDirectory())
			throw new RuntimeException("invalid output directory");
		
		PrintWriter classpathWriter = null;

		if( classpath!=null )
			classpathWriter = new PrintWriter(new FileWriter(classpath));
		
		for(ArtifactDownloadReport r : resolved.getAllArtifactsReports()) {
			Artifact a = r.getArtifact();
			if( TypeUtils.jar(a) ) {
				
				String name = name(pattern,a);
				FileUtil.copy(r.getLocalFile(),new File(output,name),null);
				
				if( classpathWriter!=null )
					classpathWriter.println( (pathprefix==null?"":pathprefix) + name );
				
			}
		}

		if( classpathWriter!=null )
			classpathWriter.close();

	}

	private static String name(String pattern,Artifact a) {
		for(String attr:ATTRS)
			pattern = pattern.replace(brackets(attr),a.getAttribute(attr));
		return pattern;
	}

	private static CharSequence brackets(String attr) {
		return String.format("[%s]",attr);
	}


}
