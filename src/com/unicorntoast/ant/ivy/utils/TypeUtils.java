package com.unicorntoast.ant.ivy.utils;

import org.apache.ivy.core.module.descriptor.Artifact;

public abstract class TypeUtils {
	
	public static final String TYPE_JAR = "jar";
	public static final String TYPE_BUNDLE = "bundle";
	public static final String TYPE_SRC = "src";
	public static final String TYPE_SOURCE = "source";
	public static final String TYPE_JAVADOC = "javadoc";

	public static boolean jar(Artifact a) {
		return TYPE_JAR.equals(a.getType()) || TYPE_BUNDLE.equals(a.getType());
	}

	public static boolean source(Artifact a) {
		return TYPE_SRC.equals(a.getType()) || TYPE_SOURCE.equals(a.getType());
	}

	public static boolean javadoc(Artifact a) {
		return TYPE_JAVADOC.equals(a.getType());
	}

}
