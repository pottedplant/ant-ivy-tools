package com.unicorntoast.ant.ivy.eclipse;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.ivy.core.settings.IvySettings;

public abstract class SettingsUtils {
	
	public static IvySettings load(String file) throws ParseException, IOException {
		IvySettings ivySettings = new IvySettings();
		ivySettings.load(new File(file));
		return ivySettings;
	}

}
