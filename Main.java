
public class Main
{
	public static void main(String[] args) {
	    GraphSolution test = new GraphSolution(1,9);
	    System.out.println(new State(0,1,2,3).equals(new State(0,1,2,3)));
	    test.find(new State(8, 7, 6, 5, 4, 3, 2, 1, 0), new State(8,7,6,5,4,3,2,1,0));
		System.out.println("Hello World");
	}
}