package com.unicorntoast.ant.ivy.static_classpath;

import java.io.IOException;
import java.text.ParseException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.unicorntoast.ant.ivy.utils.IvySettingsUtils;
import com.unicorntoast.ant.ivy.utils.IvyUtils;
import com.unicorntoast.ant.ivy.utils.Validate;

public class StoreClasspath extends Task {
	
	private String basepath;
	private String settings;
	private String input;
	private String output = Default.DEFAULT_CLASSPATH_FILE;
	private String conf;
	
	// impl
	
	@Override
	public void execute() throws BuildException {
		
		if( basepath==null )
			basepath = getProject().getBaseDir().getAbsolutePath() + "/";
		
		Validate.notEmpty(basepath, "basepath is empty");
		Validate.notEmpty(input, "input attribute required");
		Validate.notEmpty(output, "output attribute required");
		
		try {
			
			ClasspathUtils.store(
				basepath,
				IvyUtils.resolve(
					IvyUtils.create(
						IvySettingsUtils.load(settings)
					),
					input,
					conf
				),
				output
			);
			
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
	
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	
	public void setConf(String conf) {
		this.conf = conf;
	}

}
