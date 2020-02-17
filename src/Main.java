
public class Main
{
	public static void main(String[] args) {
	    GraphSolution test = new GraphSolution(3,3);
	   // test.find(new State(0,1,2,1,4,3), new State(3,1,2,0,4,1));
	    //test.find(new State(0,1,2,3), new State(0,3,1,2));
	    test.find(new State(0,2,1,3,4,5,6,7,8), new State(8,7,2,5,4,3,6,1,0));
		System.out.println("end");
	}
}
