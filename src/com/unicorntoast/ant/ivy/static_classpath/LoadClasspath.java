package com.unicorntoast.ant.ivy.static_classpath;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

public class LoadClasspath extends Task {
	
	private String input;
	private String refid;
	
	// impl
	
	@Override
	public void execute() throws BuildException {
		
		Project project = getProject();
		Path path = new Path(project);
		project.addReference(refid, path);
		
		try {
			for(String cp : ClasspathUtils.load(input))
				path.createPathElement().setLocation(new File(cp));
		} catch (IOException e) {
			throw new BuildException(e);
		}
		
	}
	
	// stupido
	
	public void setInput(String input) {
		this.input = input;
	}
	
	public void setRefid(String refid) {
		this.refid = refid;
	}

}
