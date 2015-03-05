package main;

public class Main {
	
	public static void main(String[] args)
	{
		Test test = new Test(3, 'a', 2.44);
		System.out.println(test.toJSON());
	}
}
