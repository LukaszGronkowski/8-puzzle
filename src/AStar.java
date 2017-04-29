import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class AStar {

	Heuristics heuristicFunction;
	PriorityQueue<State> stateQueue = new PriorityQueue<State>();
	Map<String, Integer> costMap = new HashMap<String, Integer>();

	String checkedState;

	int pathCost;
	int indexPosition;
	int checkedNodes = 0;
	int generatedNodes = 0;
	
	// (0) Constructor - initialising the heuristic type
	AStar(int heuNumber) {
		switch (heuNumber) {
		case 1:
			heuristicFunction = new Manhattan();
			break;
		case 2:
			heuristicFunction = new Nielsen();
			break;
		default:
			break;
		}
	}
	
	// (1) adding the actual node to the queue (with expected cost)
	void addToQueue(String actualState, String previousState) {
		pathCost = calculatePathCost(previousState); // g(n) - cost to get to
														// this node from start
														// state
		int expectedCost = heuristicFunction.calculateValue(actualState) + 
				pathCost; // expected cost to get to the goal state

		// map the actual state with the expected cost
		costMap.put(actualState, pathCost); 
		// adding this state with expected cost to the queue
		stateQueue.add(new State(actualState, expectedCost)); 
	}

	// (2) cost to get from the start node to the node n -> g(n)
	int calculatePathCost(String previousState) {
		// initial state (no previousState)
		if (previousState == null) {
			pathCost = 0;
		}
		// else add 1 to the path cost hidden in the map
		else {
			pathCost = 1 + costMap.get(previousState);
			checkedNodes++; // 1 more state checked
		}
		return pathCost;
	}

	// (3)	// checking if the neighbour state is the goal state
	void checkNeighbours() {
		// while the queue is not empty
		while (stateQueue.size() > 0) {
			// removing the node with the best value (the smallest)
			checkedState = stateQueue.remove().toString();
			// current state is the goal state?
			if ("123804765".equals(checkedState)) {
				display(checkedState);
				break;
			} else {
				moveTiles();
			}
		}
	}
	
	// (4) Moving the tiles
	void moveTiles() {
		// actual cost did not reach the limit?
			indexPosition = checkedState.indexOf("0");
			// we are checking every possible move (neighbours)
			
			//
			while (indexPosition != 0 && indexPosition != 3 && indexPosition != 6) {
				left();
				break;
			}
			while (indexPosition > 2) {
				up();
				break;
			}
			while (indexPosition != 2 && indexPosition != 5 && indexPosition != 8) {
				right();
				break;
			}
			while (indexPosition < 6) {
				down();
				break;
			}
	}
	
	void left() {
		String newState = checkedState.substring(0, indexPosition - 1) + "0" + checkedState.charAt(indexPosition - 1)
				+ checkedState.substring(indexPosition + 1);
		checkRepeatedNode(newState, checkedState);
	}

	void right() {
		String newState = checkedState.substring(0, indexPosition) + checkedState.charAt(indexPosition + 1) + "0"
				+ checkedState.substring(indexPosition + 2);
		checkRepeatedNode(newState, checkedState);
	}

	void up() {
		String newState = checkedState.substring(0, indexPosition - 3) + "0"
				+ checkedState.substring(indexPosition - 2, indexPosition) + checkedState.charAt(indexPosition - 3)
				+ checkedState.substring(indexPosition + 1);
		checkRepeatedNode(newState, checkedState);
	}

	void down() {
		String newState = checkedState.substring(0, indexPosition) + checkedState.charAt(indexPosition + 3)
				+ checkedState.substring(indexPosition + 1, indexPosition + 3) + "0"
				+ checkedState.substring(indexPosition + 4);
		checkRepeatedNode(newState, checkedState);
	}


	void checkRepeatedNode(String newState, String previousState) {
		generatedNodes++;
		// is the state already in the map?
		if (!(costMap.containsKey(newState))) {
			addToQueue(newState, previousState);
		}
	}

	void display(String checkedState) {
		System.out.println("Final state: " + checkedState);
		System.out.println("Moves done:" + costMap.get(checkedState));
		System.out.println("Nodes visited: " + checkedNodes);
		System.out.println("Nodes checked: " + generatedNodes);
	}
}
