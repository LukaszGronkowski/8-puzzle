public class State implements Comparable<State> {
	private String state;
	private int expCost;

	public State(String state, int expCost) {
		this.expCost = expCost;
		this.state = state;
	}

	// comparing the cost of actual best state to the cost of next state
	public int compareTo(State nextState) {
		return Integer.valueOf(expCost).compareTo(nextState.expCost);
	}

	public String toString() {
		return state;
	}
}
