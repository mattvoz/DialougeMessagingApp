package com.example.demo.FriendCode;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;


public class FriendsList implements Set<Friend>
{
	private class Node
	{
		Friend data;
		Node previous;
		Node next;
		
		private Node(Friend data, Node previous, Node next)
		{
			this.data = data;
			this.previous = previous;
			this.next = next;
		}
		
		private Friend getData()
		{
			return this.data;
		}
		
		private Node getNext()
		{
			return this.next;
		}
		
		private Node getPrev()
		{
			return this.previous;
		}
		
		private void setNext(Node newNode)
		{
			this.next = newNode;
		}
		
		private void setPrevious(Node newNode)
		{
			this.previous = newNode;
		}
		
	}
	
	int size;
	Node head ;
	Node tail ;
	
	
	public FriendsList()
	{
		size = 0;
		head = new Node(null,null,null);
		tail = new Node(null,null,null);
	}
	

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		if(size == 0)
		{
			return false;
		}
		
		Node psuedo = head.next;
		
		for(int i = 0; i < this.size ; i++)
		{
			Friend compareFriend =  psuedo.getData();
			if(compareFriend.compareTo(o) == 0)
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Iterator<Friend> iterator() {
		return null;
	}

	@Override
	public Friend[] toArray() {

		Friend[] ret = new Friend[size];
		Node psuedo = this.head.next;
		int i = 0;
		while(psuedo != this.tail)
		{
			ret[i] = psuedo.data;
			i++;
			psuedo = psuedo.getNext();
		}
		
		return ret;
	}


	@Override
	public boolean add(Friend e) {
		Node addition = new Node(e,null, null);
		if (size == 0)
		{
			head.setNext(addition);
			tail.setPrevious(addition);
			addition.setPrevious(head);
			addition.setNext(tail);
			size++;
			return true;
		}
		
		Node psuedo = head.next;
		
		for(int i = 0; i < size; i++)
		{
			if(psuedo.data.compareTo(addition.data) == 0)
			{
				return false;
			}
			else if(psuedo.data.compareTo(e) < 1)
			{
				addition.previous = psuedo.previous;
				psuedo.previous.next = addition;
				psuedo.previous = addition;
				addition.next = psuedo;
				size++;
				return true;
			}
			psuedo = psuedo.getNext();
		}
		
		addition.previous = tail.previous;
		tail.previous.next = addition;
		tail.previous = addition;
		addition.next = tail;
		size ++;
		return true;
	}



	@Override
	public boolean remove(Object o) {
		if(size == 0)
		{
			return false;
		}
		
		Node psuedo = head.next;
		
		for(int i = 0; i < this.size ; i++)
		{
			Friend removeFriend =  psuedo.getData();
			if(removeFriend.compareTo(o) == 0)
			{
				psuedo.previous.next = psuedo.next;
				psuedo.next.previous = psuedo.previous;
				size --;
				return true;
			}
			psuedo = psuedo.next;
		}
		
		return false;
		
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(Collection<? extends Friend> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


