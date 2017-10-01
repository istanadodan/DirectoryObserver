package com.file.observer;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DirObserverProducer extends Thread implements Observer{

	ListContainer container;
	
	public DirObserverProducer(ListContainer container, Observable subject) {
		this.container = container;		
		subject.addObserver(this);
	}
	
	public void run() {}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) arg;
		
		for(int i=0;i<list.size();i++) {
			container.setFile(new File(list.get(i)));
		}
	}
	
}
