package com.file.observer;

public class Main {

	static public void main(String[] args) {
		
		String rootDir = args[0];
		
		ListContainer container = new ListContainer();
		DirObserver subject = new DirObserver(rootDir);
		Thread observer = new Thread(subject);
		Thread producer = new DirObserverProducer(container, subject);
		Thread consumer = new FileOutputConsumer(container);
		
		observer.start();
		producer.start();
		consumer.start();
	}
}
