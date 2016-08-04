package com.file.observer.vo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SortingCollection {
	
	private ContentsVO head;
	private LinkedList<ContentsVO> list;
	private Set<Object> parentGroup;
	
	public SortingCollection() {
		this.parentGroup = new HashSet<>();
	}
	public void setParentGroup() {
				
		for(ContentsVO vo:list) {
			parentGroup.add(vo.getParentId());			
		}		
	}
	public void setList(LinkedList<ContentsVO> list) {
		this.list = list;
		head = this.list.removeFirst();
		
		setParentGroup();
		setDepth();
	}
	
	private void setDepth() {
		for(ContentsVO vo:list) {
			int pid = Integer.parseInt(String.valueOf(vo.getParentId()));
			int id = getId(pid);
			
			if (id<0) 
				vo.setDepth(1);
			else
				vo.setDepth(list.get(id).getDepth()+1);
		}				
	}
	public LinkedList<ContentsVO> getList() {
		LinkedList<ContentsVO> retList = list;
		retList.addFirst(head);
		return retList;
	}
	private int getId(int id) {
		int ret=-1;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getId() == id)
				ret = i;
		}
		return ret;
	}
	private int getId(int id, int ix) {
		int ret=-1;
		for(int i=0;i<ix;i++) {
			int pid = Integer.parseInt(String.valueOf(list.get(i).getParentId()));
			if(list.get(i).getId() == id || pid == id) {
				ret = i;
			}
		}
		return ret;
	}
	public void sortWithParentId() {
		
		LinkedList<ContentsVO> newList = new LinkedList<>();
		
		Iterator<Object> it = parentGroup.iterator();
		
		while(it.hasNext()) {
			List<ContentsVO> list = getListByGroupId(it.next());
			list.sort(new Comparator<ContentsVO>(){

				@Override
				public int compare(ContentsVO o1, ContentsVO o2) {
					return o1.getName().compareTo(o2.getName());
				}				
			});
			newList.addAll(list);
		}
		
		list = newList;
	}
	
	public void sortByIdLink(){
		
		for(int i=0;i<list.size();i++) {
			
			ContentsVO vo = list.get(i);
			
			int pid = Integer.parseInt(String.valueOf(list.get(i).getParentId()));
			int sid = getId(pid,i);
			
			if(sid!=-1) {
				list.remove(i);
				list.add(sid+1, vo);	
			}
			
		}
	}

	private List<ContentsVO> getListByGroupId(Object next) {
		
		List<ContentsVO> newList = new ArrayList<>();
				
		for(ContentsVO vo :list) {
			if(vo.getParentId().equals(next)) 
				newList.add(vo);
		}
		return newList;
	}
	public void print() {
		
		System.out.println(head.getName());
		
		for(ContentsVO vo : list) {
			for(int i=0;i<vo.getDepth();i++) 
				System.out.print(" ");
//			System.out.println(vo.getName()+","+vo.getId()+"/"+vo.getParentId()+","+vo.getDepth());
			System.out.println(vo.getName());
		}
		
	}
}
