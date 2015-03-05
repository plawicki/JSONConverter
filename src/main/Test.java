package main;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import JSON.JSONConverter;

public class Test extends SuperTest implements JSONConverter {
	
	int a;
	char b;
	double c;
	String d = "asdasdas";
	int[] e;
	List<Integer> f = new ArrayList<Integer>();
	Queue<Integer> g = new PriorityQueue<Integer>();
	SuperTest sup = new SuperTest();
	
	public Test(int a, char b, double c){
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		int[] e = {a, b, (int)c};
		//double[] e = {c};
		this.e = e;
		this.f.add(a);
		this.g.add(a);
		this.g.add((int) b);
	}
}
