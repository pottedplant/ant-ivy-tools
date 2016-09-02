package com.unicorntoast.ant.ivy.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.ivy.Ivy;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.settings.IvySettings;

public abstract class IvyUtils {
	
	public static Ivy create(String settings) throws ParseException, IOException {
		if( settings!=null )
			return create(IvySettingsUtils.load(settings));
		return create();
	}
	
	public static Ivy create(IvySettings settings) {
		return Ivy.newInstance(settings);
	}
	
	public static Ivy create() {
		return Ivy.newInstance();
	}

	public static ResolveReport resolve(Ivy ivy, String file, String conf, boolean offline) throws ParseException, IOException {
		
		ResolveOptions opts = new ResolveOptions();
		
		if( conf!=null )
			opts.setConfs(conf.split(","));

		if(offline)
			opts.setUseCacheOnly(true);
		
		ResolveReport report = ivy.resolve(new File(file).toURI().toURL(),opts);
		
		if( report.hasError() )
			throw new RuntimeException(errorMessage(report));
		
		return report;
	}
	
	private static String errorMessage(ResolveReport report) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ivy-resolve error:\n");
		
		for(Object msg : report.getAllProblemMessages())
			sb.append(msg).append("\n");
		
		return sb.toString();
	}

}
