import java.util.HashMap;

public class Manhattan implements Heuristics {
	@Override
	public int calculateValue(String actualState) {

		int h = 0;	// h(n) - sum
		for (int i = 1; i < 9; i++)
		{
			// checking in which space is each number in the actualState 
			// and in the goalState
			h += countMovements(actualState.indexOf(Integer.toString(i)),
					"123804765".indexOf(Integer.toString(i)));
		}		
		return h;
	}
	
	int countMovements(int inputPosition, int goalPosition) {
		HashMap<String, String> board = new HashMap<String, String>();

		board.put("0", "0;0"); board.put("1", "1;0"); board.put("2", "2;0"); board.put("3", "0;1"); board.put("4", "1;1"); 
		board.put("5", "2;1"); board.put("6", "0;2"); board.put("7", "1;2"); board.put("8", "2;2");
		
		String state = board.get(String.valueOf(inputPosition));	// position of a number in a actual board
		String goal = board.get(String.valueOf(goalPosition));		// position of a number in a goal board

		int x1 = Character.getNumericValue(state.charAt(0));
		int y1 = Character.getNumericValue(state.charAt(2));
		int x2 = Character.getNumericValue(goal.charAt(0));
		int y2 = Character.getNumericValue(goal.charAt(2));

		int h = Math.abs(x1 - x2) + Math.abs(y1 - y2);				// the distance between the wrong and the correct position
		return h;
	}
}
