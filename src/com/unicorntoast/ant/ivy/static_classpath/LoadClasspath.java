package com.unicorntoast.ant.ivy.static_classpath;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

import com.unicorntoast.ant.ivy.utils.Validate;

public class LoadClasspath extends Task {
	
	private String input = Default.DEFAULT_CLASSPATH_FILE;
	private String refid = Default.DEFAULT_PATH_REFID;
	
	// impl
	
	@Override
	public void execute() throws BuildException {
		
		Validate.notEmpty(input, "input attribute reqired");
		Validate.notEmpty(refid, "refid attribute reqired");
		
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
