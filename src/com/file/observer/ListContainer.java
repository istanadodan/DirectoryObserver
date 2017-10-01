package com.file.observer;

import java.io.File;
import java.util.LinkedList;

public class ListContainer {

	LinkedList<File> lList = new LinkedList<>();
	
	public ListContainer() {}
	
	synchronized public void setFile(File file) {
		lList.add(file);
		notifyAll();
	}
	
	synchronized public File getFile() {
		File file = lList.pollFirst();
		notifyAll();
		return file;
	}
	
	synchronized public boolean isEmpty() {
		return lList.isEmpty();
		
	}
}
