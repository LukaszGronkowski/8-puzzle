public class NMaxSwap implements Heuristics {
	@Override
	public int calculateValue(String currentState) {
		int h = 0;
		int blankPosition = 0;

		for (int i = 0; i < 9; i++) {
			if (currentState.charAt(i) == '0') {
				blankPosition = i; // position of blank space
				break;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (currentState.charAt(i) == "123804765".charAt(blankPosition)) {
				// we swap the tiles
				currentState.replace(currentState.charAt(blankPosition), currentState.charAt(i));
				h++;
			}

			if (currentState.charAt(0) == '0') {
				for (i = 0; i < 9; i++) {
					// we swap what is still in wrong position
					if (currentState.charAt(i) != "123804765.".charAt(i)) {
						currentState.replace(currentState.charAt(0), currentState.charAt(i));
						h++;
					}
				}
			}

		}
		return h;
	}
}
