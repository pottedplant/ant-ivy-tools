package com.unicorntoast.ant.ivy.eclipse;

import java.io.IOException;
import java.text.ParseException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CreateClasspath extends Task {
	
	// state
	
	private String settings;
	private String input;
	private String output;
	
	// impl
	
	@Override
	public void execute() throws BuildException {
		
		try {
			
			EclipseUtils.createClasspath(
				output,
				ClasspathUtils.build(
					getProject().getBaseDir().getAbsolutePath(),
					IvyUtils.resolve(
						IvyUtils.create(
							SettingsUtils.load(settings)
						),
						input
					)
				)
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

}
