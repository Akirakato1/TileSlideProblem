import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class GraphSolution {

	private final int w, h;
	private State[] states;
	private Map<State, Integer> stateMap;
	private List<Integer>[] graph;
	private final Map<Integer, String> directionMap;

	@SuppressWarnings("unchecked")
	GraphSolution(int w, int h) {
		this.w = w;
		this.h = h;
		states = new State[factorial(w * h)];
		stateMap = new HashMap<State, Integer>();
		graph = new List[factorial(w * h)];
		directionMap= new HashMap<>();
		directionMap.put(0,"up");
		directionMap.put(1,"right");
		directionMap.put(2,"down");
		directionMap.put(3,"left");
	}

	public int find(State in, State out) {
		generateStates(in);
		generateMap();
		generateGraph(in);
		if (stateMap.get(in) == null || stateMap.get(out) == null) {
			throw new IllegalArgumentException("instate or outstate not formatted correctly");
		}
		countReachableNodes();
		//Run dijkstra's -> convert node indexes into list of states -> calculate the  direction to move at every state
		return 0;
	}

	private void generateStates(State in) {
		int nums[]=in.getInputs();
		int[] empty = {};
		/*
		int[] nums = new int[this.w * h];
		int[] empty = {};
		for (int i = 0; i < w * h; i++) {
			nums[i] = i;
		}*/
		generateStatesHelper(empty, nums, new IntegerMutate(0));
	}

	private void generateMap() {
		for (int i = 0; i < states.length; i++) {
			stateMap.put(states[i], i);
			System.out.println("State: "+states[i]+" index: "+stateMap.get(states[i]));
		}
	}

	private void generateGraph(State in) {
		initGraph();
		int emptySpot = in.getEmptySpot();
		generateGraphHelper(in, 0, emptySpot);
		generateGraphHelper(in, 1, emptySpot);
		generateGraphHelper(in, 2, emptySpot);
		generateGraphHelper(in, 3, emptySpot);
		printGraph();
		printIndexGraph();
	}

	private void initGraph() {
		List<Integer> temp;
		for (int i = 0; i < graph.length; i++) {
				temp=new ArrayList<>();
				graph[i]=temp;
		}
	}
	
	private void printGraph() {
		for(int i=0; i<graph.length;i++) {
			System.out.println(i+" Node: "+states[i]+"\nNeighbour: "+Arrays.toString(indexToStates(graph[i])));
		}
	}
	private void printIndexGraph(){
		for(int i=0; i<graph.length;i++) {
			System.out.println("graph.addNode("+i+");");
			for(int j=0;j<graph[i].size();j++){
				System.out.println("graph.addLink("+i+", "+graph[i].get(j)+");");
			}
			//System.out.println(i+" : "+graph[i]);
		}
	}

	//direction 0=up, 1=right, 2=down, 3=left
	private void generateGraphHelper(State currentState, Integer direction, int emptySpot) {
		if ((emptySpot % w == 0 && direction == 3) || (emptySpot % w == w - 1 && direction == 1)
				|| (emptySpot < w && direction == 0) || (emptySpot > (h - 1) * w - 1 && direction == 2)) {
			return;
		}

		State nextState = swap(currentState, direction, emptySpot);
		int nextEmptySpot = nextState.getEmptySpot();
		if (graph[stateMap.get(currentState)].contains(stateMap.get(nextState))) {
			return;
		}
		System.out.println("currentState hash: "+stateMap.get(currentState)+"\nnextstate hash: "+stateMap.get(nextState));
		System.out.println("currentState: "+currentState+"\nnextstate: "+nextState);
		
		graph[stateMap.get(currentState)].add(stateMap.get(nextState));
		graph[stateMap.get(nextState)].add(stateMap.get(currentState));
		
		generateGraphHelper(nextState, 0, nextEmptySpot);
		generateGraphHelper(nextState, 1, nextEmptySpot);
		generateGraphHelper(nextState, 2, nextEmptySpot);
		generateGraphHelper(nextState, 3, nextEmptySpot);
	}

	// direction 0=up, 1=right, 2=down, 3=left
	private State swap(State currentState, int direction, int emptySpot) {
		int[] arr = currentState.getInputs();
		if (direction == 0) {
			arr[emptySpot] = arr[emptySpot - w];
			arr[emptySpot - w] = 0;
		} else if (direction == 1) {
			arr[emptySpot] = arr[emptySpot + 1];
			arr[emptySpot + 1] = 0;
		} else if (direction == 2) {
			arr[emptySpot] = arr[emptySpot + w];
			arr[emptySpot + w] = 0;
		} else {
			arr[emptySpot] = arr[emptySpot - 1];
			arr[emptySpot - 1] = 0;
		}
		return new State(arr);
	}

	private int factorial(int n) {
		return factorial_t(n, 1);
	}

	private int factorial_t(int n, int acc) {
		if (n > 1) {
			return factorial_t(n - 1, acc * n);
		}
		return acc;
	}

	private void generateStatesHelper(int[] candidate, int[] remaining, IntegerMutate index) {
		if (remaining.length == 0) {
			System.out.println(index + "  " + Arrays.toString(candidate));
			this.states[index.value] = new State(candidate);
			index.value = index.value + 1;
		}

		for (int i = 0; i < remaining.length; i++) {
			int[] newCandidate = new int[candidate.length + 1];
			for (int j = 0; j < candidate.length; j++) {
				newCandidate[j] = candidate[j];
			}
			newCandidate[newCandidate.length - 1] = remaining[i];

			int[] newRemaining = new int[remaining.length - 1];
			for (int j = 0; j < i; j++) {
				newRemaining[j] = remaining[j];
			}
			for (int j = i; j < newRemaining.length; j++) {
				newRemaining[j] = remaining[j + 1];
			}

			generateStatesHelper(newCandidate, newRemaining, index);
		}
	}
	
	private List<Integer> dijkstras(){
		return null;
	}
	
	private State[] indexToStates(List<Integer> indexes) {
		State[] output=new State[indexes.size()];
		for(int i = 0; i<output.length;i++){
			output[i]=states[indexes.get(i)];
		}
		return output;
	}

	private String swapDirection(State from, State to){
		int fromEmpty=from.getEmptySpot();
		int toEmpty=to.getEmptySpot();
		if (fromEmpty == toEmpty + w){
			return directionMap.get(0);
		} else if (fromEmpty == toEmpty - 1){
			return directionMap.get(1);
		}else if (fromEmpty == toEmpty - w){
			return directionMap.get(2);
		}else if (fromEmpty == toEmpty + 1){
			return directionMap.get(3);
		} else {
			return from + " and "+to +" are not connected";
		}

	}

	private void countReachableNodes(){
		int count=0;
		for(int i=0;i<graph.length;i++){
			if(graph[i].size()>0){
				count++;
			}
		}
		System.out.println(count);
	}
	
}
