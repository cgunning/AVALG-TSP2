import java.util.Comparator;

public class Morton implements Comparator<Node> {

	private boolean lessMsb(int x, int y) {
		return x < y && x < (x ^ y);
	}

	@Override
	public int compare(Node n1, Node n2) {
		// int[] a = { (int) n1.getX(), (int) n1.getY() };
		// int[] b = { (int) n2.getX(), (int) n2.getY() };
		/*
		 * int j = 0; int x = 0; for (int k = 0; k < 2; k++) { int y = a[k] ^
		 * b[k]; if (lessMsb(x, y)) { j = k; x = y; } } return a[j] - b[j];
		 */
		return (int) ((n1.getX() - n2.getX()) * (n1.getY() - n2.getY()));
	}
}
