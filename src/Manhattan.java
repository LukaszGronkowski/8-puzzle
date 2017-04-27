import java.util.HashMap;

public class Manhattan implements Heuristics {
	@Override
	public int calculateValue(String actualState) {

		int man_heuristic = 0;
		for (int i = 1; i < 9; i++)// skip blank
		{
			man_heuristic = man_heuristic + manhattanLookup(actualState.indexOf(Integer.toString(i)),
					"123804765".indexOf(Integer.toString(i)));
		}
		return man_heuristic;
	}

	int manhattanLookup(int inputPosition, int goalPosition) {
		int x1, y1, x2, y2;
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("0", "0,0");
		map.put("1", "1,0");
		map.put("2", "2,0");
		map.put("3", "0,1");
		map.put("4", "1,1");
		map.put("5", "2,1");
		map.put("6", "0,2");
		map.put("7", "1,2");
		map.put("8", "2,2");

		String state = map.get(String.valueOf(inputPosition));
		String goal = map.get(String.valueOf(goalPosition));

		x1 = Character.getNumericValue(state.charAt(0));
		y1 = Character.getNumericValue(state.charAt(2));
		x2 = Character.getNumericValue(goal.charAt(0));
		y2 = Character.getNumericValue(goal.charAt(2));

		int h = Math.abs(x1 - x2) + Math.abs(y1 - y2);
		return h;
	}
}
