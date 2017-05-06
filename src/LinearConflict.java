public class LinearConflict extends Manhattan {

	@Override
	public int calculateValue(String currentState) {
		// first, calculate Manhattan Distance
		int h = super.calculateValue(currentState);
		
		// converting the string to the 2D board
		int[][] board = new int[3][3];
		board = createTable(currentState);

		h += linearVerticalConflict(board);
		h += linearHorizontalConflict(board);

		return h;
	}

	private int linearVerticalConflict(int[][] board) {
		int varticalSum = 0;

		for (int row = 0; row < 3; row++) {
			byte max = -1;
			for (int column = 0; column < 3; column++) {
				byte cellValue = (byte) board[row][column];
				// is tile in its goal row ?
				if (cellValue != 0 && (cellValue - 1) / 3 == row) {
					if (cellValue > max) {
						max = cellValue;
					} else {
						varticalSum += 2;
					}
				}

			}

		}
		return varticalSum;
	}

	private int linearHorizontalConflict(int[][] board) {

		int linearConflict = 0;

		for (int column = 0; column < 3; column++) {
			byte max = -1;
			for (int row = 0; row < 3; row++) {
				byte cellValue = (byte) board[row][column];
				// is tile in its goal row ?
				if (cellValue != 0 && cellValue % 3 == column + 1) {
					if (cellValue > max) {
						max = cellValue;
					} else {
						linearConflict += 2;
					}
				}

			}

		}
		return linearConflict;
	}
	
	private int[][] createTable(String string) {
		int[][] board = new int[3][3];
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 3; column++) {
				board[row][column] = Character.getNumericValue(string.charAt(3 * row + column));
			}
		}		
		return board;
	}

}
