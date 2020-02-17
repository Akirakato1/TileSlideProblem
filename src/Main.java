
public class Main
{
	public static void main(String[] args) {
	    GraphSolution test = new GraphSolution(2,3);
	    test.find(new State(0,1,2,1,2,1), new State(1,1,2,0,1,2));
	    //test.find(new State(0,1,2,3), new State(0,3,1,2));
	    //test.find(new State(0,1,1,2,2,3,3,4,4), new State(4,2,1,4,2,1,3,0,3));
		System.out.println("end");
	}
}
