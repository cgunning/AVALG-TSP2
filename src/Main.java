public class Main {
	public static void main(String[] args) {
		//
		// GUI gui = new GUI();
		// gui.setSize(500, 500);
		// while (true) {
		double deadline = System.currentTimeMillis() + 1300;
		// deadline += 20000;
		TSP tsp = new TSP();
		tsp.readGraph();
		// double deadline = System.currentTimeMillis() + 1500;
		Node[] tour = tsp.solve(deadline);
		// }
		// gui.drawTour(tour);
		// gui.setVisible(true);
	}
}