public class LinearConflict extends Manhattan {

	// converting the string to the 2D board
	int[] board = new int[9];

	@Override
	public int calculateValue(String currentState) {
		// first, calculate Manhattan Distance
		int h = super.calculateValue(currentState);
		board = createTable(currentState);

		// calculating conflicts for each row and column
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

	// count how many inversion there are in a row or in a column
	public int calculate(String which, int colRowNumber) {
		int[] current = createRowOrTable(which, colRowNumber);
		int[] goal = goalRowOrColumn(which, colRowNumber);
		int sum = 0;

		// are the goal positions in the same row/column?
		for (int i = 1; i < 3; i++) {
			boolean isInGoal = isNumberInTheGoalArray(goal, current[i]);
			if (current[i] != 0 && isInGoal == true) {
				for (int j = 0; j < i; j++) {
					boolean isInGoal2 = isNumberInTheGoalArray(goal, current[j]);
					if (current[j] != 0 && isInGoal2 == true) {
						// ... and are inverted, count it as a conflict.
						if ((current[i] < current[j]) != (i < j)) {
							sum++;
						}
					}
				}
			}
		}
		return sum;
	}

	private int[] createTable(String string) {
		int[] board = new int[9];
		for (int i = 0; i < 9; i++)
			board[i] = string.charAt(i);
		return board;
	}
	
	private boolean isNumberInTheGoalArray(int[] goalArray, int number) {
		for (int i = 0; i < 3; i++) {
			if (goalArray[i] == number) return true;
		}
		return false;
	}
}
