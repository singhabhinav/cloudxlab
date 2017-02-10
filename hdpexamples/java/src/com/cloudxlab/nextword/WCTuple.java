package com.cloudxlab.nextword;

import java.util.PriorityQueue;

public class WCTuple implements Comparable<WCTuple>{
	public WCTuple(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}

	private String word;
	private int count;

	public int getCount() {
		return count;
	}

	public String getWord() {
		return word;
	}

	@Override
	public int compareTo(WCTuple o) {
		return this.count - o.count;
	}
	public void addToList(PriorityQueue<WCTuple> pq2) {
		if(pq2.size() < 5)
		{
			pq2.add(this);
			return;
		}

		WCTuple top = pq2.peek();

		if(top.count < this.count)
		{
			pq2.remove();
			pq2.add(this);
		}
	}

	public static void main(String[] args) {
		PriorityQueue<WCTuple> pq = new PriorityQueue<WCTuple>();
		new WCTuple("sandeep", 1).addToList(pq);
		new WCTuple("art", 20).addToList(pq);
		new WCTuple("art1", 5).addToList(pq);
		new WCTuple("sravani", 2).addToList(pq);
		new WCTuple("ryan0", 22).addToList(pq);
		new WCTuple("ryan2", 3).addToList(pq);
		new WCTuple("ryan1", 3).addToList(pq);

		while(!pq.isEmpty())
		{
			WCTuple wc = pq.remove();
			System.out.println(wc.word + ":" + wc.count);
		}
	}
}
