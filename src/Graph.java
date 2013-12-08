public class Graph {
	Node[] cities;
	double[][] distance;
	int size;

	public Graph(Node[] tour) {
		size = tour.length;
		distance = new double[size][size];
		this.cities = tour;

		for (int i = 1; i < size; i++) {
			for (int j = 0; j < i; j++) {
				double d = calculateDistance(cities[i], cities[j]);
				distance[i][j] = d;
				distance[j][i] = d;
			}
		}
	}

	@Override
	public String toString() {
		String returnString = cities.length + "\n";

		for (Node node : cities) {
			returnString += node.toString() + "\n";
		}

		return returnString;
	}

	public double getDistance(Node n1, Node n2) {
		return distance[n1.getIndex()][n2.getIndex()];
	}

	public Node[] getCities() {
		return cities;
	}

	private double calculateDistance(Node n1, Node n2) {
		return Math.hypot(n1.getX() - n2.getX(), n1.getY() - n2.getY());
	}

	public double calculateTotalDistance(Node[] tour) {
		double distance = 0;

		for (int i = 1; i < tour.length; i++) {
			distance += getDistance(tour[i - 1], tour[i]);
		}
		return distance;
	}
}
