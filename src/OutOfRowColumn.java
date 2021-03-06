public class OutOfRowColumn implements Heuristics {
	
	// converting the string to the 2D board
	int[] board = new int[9];
	
	@Override
	public int calculateValue(String currentState) {
		int h = 0;
		board = createTable(currentState);

		// calculating sum of tiles out of each row and column
		h += calculate("row", 0);
		h += calculate("row", 1);
		h += calculate("row", 2);
		h += calculate("col", 0);
		h += calculate("col", 1);
		h += calculate("col", 2);

		return h;
	}
	
	// creating an array with a row or a column
	private int[] createRowOrTable(String which, int colRowNumber) {
		int[] result = new int[3];
		switch (which) {
		case "row":
			System.arraycopy(board, colRowNumber * 3, result, 0, 3);
			break;
		case "column":
			for (int i = 0, j = colRowNumber; i < 3; i++, j += 3) {
				result[i] = board[j];
			}
			break;
		}
		return result;
	}

	// creating a goal row or column
	private int[] goalRowOrColumn(String which, int colRowNumber) {
		int[] result = new int[3];
		switch (which) {
		case "row":
			for (int i = 0, j = colRowNumber * 3; i < 3; i++, j++) {
				result[i] = "123804765".charAt(j);
			}
			break;
		case "col":
			if (colRowNumber == 0) {
				result[0] = 1; result[1] = 8; result[2] = 7;
			} else if (colRowNumber == 1) {
				result[0] = 2; result[1] = 0; result[2] = 6;
			} else if (colRowNumber == 2) {
				result[0] = 3; result[1] = 4; result[2] = 5;
			}
		}
		return result;
	}
		

	public int calculate(String which, int colRowNumber) {
		int[] current = createRowOrTable(which, colRowNumber);
		int[] goal = goalRowOrColumn(which, colRowNumber);
		int sum = 0;

		for (int i = 0; i < 3; i++) {
			boolean isInProper = isNumberInTheGoalArray(goal, current[i]);
			// is the tile in the proper row/column?
			if (isInProper == false) {
				sum++;
			}
		}
		return sum;
	}
	
	private boolean isNumberInTheGoalArray(int[] goalArray, int number) {
		for (int i = 0; i < 3; i++) {
			if (goalArray[i] == number) return true;
		}
		return false;
	}
	
	private int[] createTable(String string) {
		int[] board = new int[9];
		for (int i = 0; i < 9; i++)
			board[i] = string.charAt(i);
		return board;
	}
}
