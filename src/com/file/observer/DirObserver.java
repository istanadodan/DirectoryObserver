package com.file.observer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class DirObserver extends Observable implements Runnable {

	List<String> oldList = new ArrayList<>();
	String pathName;
	
	public DirObserver(String pathName) {		
		this.pathName = pathName;
	}	
	
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {
		
		List<String> newList = new ArrayList<>();
			
		File targetDirPath = new File(pathName);
		File[] fileList = targetDirPath.listFiles();
		
		for(File file: fileList) {
			String filePath = file.getPath();
			//remove pathname which already exists
			if(oldList.contains(filePath))
				continue;
			//check if a file has csv format extension.
			String ext = filePath.substring(filePath.lastIndexOf(".")+1);
			if(!ext.equals("csv")) {
				System.out.println("\n[error] "+ ext +" file dectected");
				oldList.add(filePath);
				continue;
			}
			//add pathname to the newList and update oldList.
			newList.add(filePath);
			oldList.add(filePath);
			
//			System.out.println("file="+file.getPath()+",ext="+ext);
		}
			if(!newList.isEmpty()) {
				setChanged();
				notifyObservers(newList);
		//		System.out.println(newList);
			}
		}
		
	}
}
