package com.file.observer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import com.file.observer.vo.ContentsVO;
import com.file.observer.vo.SortingCollection;

public class FileOutputConsumer extends Thread{

	ListContainer container;
	
	public FileOutputConsumer(ListContainer container) {
		this.container = container;		
	}
	
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {		
			if (container.isEmpty())
				continue;
			
			File file = container.getFile();
			
			BufferedReader reader = null;
			LinkedList<ContentsVO> list = new LinkedList<>();
			
			try {			
				reader = new BufferedReader(new FileReader(file.getPath()));
				//pass title row
				String data = reader.readLine();
				String[] str = data.split(",");
				
				if(!str[0].equals("Id") || !str[1].equals("Name") || !str[2].equals("ParentId")) {
					System.out.println("\n[error] wrong format file");
					continue;
				}
				
				while(true) {
					
					data = reader.readLine(); 
					
					str = data.split(",");

					ContentsVO vo = new ContentsVO();
					vo.setId(Integer.parseInt(str[0]));
					vo.setName(str[1]);
					if(str.length==3)
						vo.setParentId(str[2]);
					else
						vo.setParentId(null);					
					
					list.add(vo); 
				}				
				
			}catch(IOException e) {
				System.out.println("IO ERROR");
			}catch(NullPointerException e){} 
			
			SortingCollection sort = new SortingCollection();

			System.out.println("\n--------- result --------------------");
			System.out.println("File:"+file.getPath());
			System.out.println("--------- result --------------------");
			sort.setList(list);			
			sort.sortWithParentId();
			sort.sortByIdLink();
			sort.print();
			
		}
	}
}
