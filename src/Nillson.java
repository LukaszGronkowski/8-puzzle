public class Nillson implements Heuristics {
	@Override
	public int calculateValue(String actualState) {
		int s = 0;	// S(n), because for Nilsson's Sequence Score h(n) = P(n) + 3S(n), where P(n) is Manhattan Distance

		// adding 2 to the sum for each tile that is not followed by its proper successor
		
		// checking for each condition 
		for (int i = 0; i < 2; i++) {
			if (actualState.charAt(i) != (actualState.charAt(i + 1) - 1)) s += 2;
		}
		
		// going clockwise
		if (actualState.charAt(2) != (actualState.charAt(5) - 1)) s += 2;
		if (actualState.charAt(5) != (actualState.charAt(8) - 1)) s += 2;
		
		for (int i = 8; i > 6; i--) {
			if (actualState.charAt(i) != (actualState.charAt(i - 1) - 1)) s += 2;
		}
		
		if (actualState.charAt(6) != (actualState.charAt(3) - 1)) s += 2;
		if (actualState.indexOf(Integer.toString(1)) != 0) s += 2;
		
		// if 0 (blank space) is not in the middle, we add 1 to the h(n)
		if ((actualState.indexOf(Integer.toString(0)) != "123804765".indexOf(Integer.toString(0)))) s++;

		int p = (new Manhattan()).calculateValue(actualState);	// p is P(n)
		return (p + (3 * s));
	}
}
