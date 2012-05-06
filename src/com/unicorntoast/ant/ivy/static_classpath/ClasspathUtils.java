package com.unicorntoast.ant.ivy.static_classpath;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ResolveReport;

import com.unicorntoast.ant.ivy.utils.IvyPathUtils;
import com.unicorntoast.ant.ivy.utils.TypeUtils;

public abstract class ClasspathUtils {

	public static void store(String basepath, ResolveReport report, String output, String pathprefix) throws IOException {

		OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(output)));
		try {

			for(ArtifactDownloadReport r : report.getAllArtifactsReports())  {
				Artifact a = r.getArtifact();

				if( TypeUtils.jar(a) )
					out.append(IvyPathUtils.path(basepath,r,pathprefix)).append('\n');
			}

		} finally {
			out.close();
		}

	}

	public static ArrayList<String> load(String input) throws IOException {
		ArrayList<String> result = new ArrayList<String>();

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
		try {
			String line;
			while( (line=in.readLine()) != null )
				result.add(line);
		} finally {
			in.close();
		}
		return result;
	}

}
