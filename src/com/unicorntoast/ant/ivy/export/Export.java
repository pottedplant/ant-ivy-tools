package com.unicorntoast.ant.ivy.export;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.ivy.Ivy;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.util.FileUtil;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.unicorntoast.ant.ivy.utils.IvySettingsUtils;
import com.unicorntoast.ant.ivy.utils.IvyUtils;
import com.unicorntoast.ant.ivy.utils.Validate;

public class Export extends Task {

	private String settings;
	private String input;
	private String conf;
	private String output;
	private String classpath;
	private String pathprefix;
	private String pattern = "[organisation]-[module]-[type]-[artifact]-[revision].[ext]";
	private boolean clean = false;
	private boolean offline = false;

	// impl

	@Override
	public void execute() throws BuildException {

		Validate.notEmpty(input, "input attribute required");
		Validate.notEmpty(output, "output attribute required");

		try {

			Ivy ivy = IvyUtils.create(IvySettingsUtils.load(settings));
			ResolveReport resolved = IvyUtils.resolve(ivy,input,conf,offline);
			
			if( clean )
				FileUtil.forceDelete(new File(output));

			ExportUtils.export(resolved,output,classpath,pattern,pathprefix);

		} catch (ParseException e) {
			throw new BuildException(e);
		} catch (IOException e) {
			throw new BuildException(e);
		}

	}


	// stupido

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public void setPathprefix(String pathprefix) {
		this.pathprefix = pathprefix;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}
}
