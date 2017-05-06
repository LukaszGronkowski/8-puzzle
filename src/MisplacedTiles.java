public class MisplacedTiles implements Heuristics {
	@Override
	public int calculateValue(String currentState) {
		int h = 0;
		
		// iterate through the given string
		for (int i = 0; i < 9; i++) {
			// we don't add 1 for blank space
			if (currentState.charAt(i) == '0') continue;
			
			// if the tile is not in its proper place, add 1
			if (currentState.charAt(i) != "123804765".charAt(i)) h++;
		}
		
		return h;
	}

}
