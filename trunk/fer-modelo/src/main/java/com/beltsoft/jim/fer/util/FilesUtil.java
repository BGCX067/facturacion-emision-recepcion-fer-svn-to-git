package com.beltsoft.jim.fer.util;

import java.io.File;
import java.util.Collection;

public class FilesUtil {

	
	public static void addTree(File file, Collection<File> all) {
	    File[] children = file.listFiles();
	    if (children != null) {
	        for (File child : children) {
	            all.add(child);
	            addTree(child, all);
	        }
	    }
	}
}
