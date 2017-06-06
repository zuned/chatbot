/**
 * All rights reserved. hCentive Hackathon.
 */ 
package com.hcentive.hackathon.commons.collections;

import java.util.ArrayDeque;

/**
 * @author Zuned.Ahmed
 * @param <T>
 *
 */
public class CustomFixedSizeQueque<T> extends ArrayDeque<T>{

	private static final long serialVersionUID = 1L;

	int fixedSize = 16;

	public CustomFixedSizeQueque(int fixedSize) {
		super(fixedSize);
		this.fixedSize = fixedSize;
	}
	
	/* (non-Javadoc)
	 * @see java.util.ArrayDeque#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		if(this.size() == fixedSize  ){
			this.removeLast();
			this.addFirst(e);
		}
		return super.add(e);
	}
	
	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if(!this.isEmpty())
			buffer.append(this.peekLast());
		return buffer.toString();
	}
}
