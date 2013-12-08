import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Random;

public class TSP {

	Graph graph;

	public Node[] solve(double deadline) {
		Node[] tour = graph.getCities();
		if (tour.length == 2) {
			System.out.println(tour[0].getIndex());
			System.out.println(tour[1].getIndex());
			return null;
		}
		Opt opt = new Opt();
		Node[] greedyTour = null;

		greedyTour = graph.getCities();
		// Arrays.sort(greedyTour, new Morton());
		greedyTour = greedyTour();

		tour = greedyTour;
		Node[] bestTour = tour;
		double bestDistance = Double.MAX_VALUE;
		while (System.currentTimeMillis() < deadline) {
			tour = opt.twoOpt(graph, tour, deadline);
			double currentDistance = graph.calculateTotalDistance(tour);
			if (currentDistance < bestDistance) {
				bestDistance = currentDistance;
				bestTour = tour.clone();
			} else {
				tour = bestTour.clone();
			}
			tour = shuffle(tour);
		}

		for (int i = 0; i < bestTour.length; i++) {
			System.out.println(bestTour[i].getIndex());
		}
		// System.out
		// .println(graph.calculateTotalDistance(bestTour)
		// + graph.getDistance(bestTour[0],
		// bestTour[bestTour.length - 1]));
		return bestTour;

	}

	public Node[] greedyTour() {
		Node[] cities = graph.getCities();

		Node[] tour = new Node[cities.length];
		boolean[] used = new boolean[cities.length];

		tour[0] = cities[0];
		tour[tour.length - 1] = cities[0];
		used[0] = true;
		used[tour.length - 1] = true;

		for (int i = 1; i < cities.length; i++) {
			double bestDistance = Double.MAX_VALUE;
			Node bestNode = null;
			Node n1 = tour[i - 1];
			int usedIndex = 0;
			for (int j = 0; j < cities.length; j++) {
				if (used[j])
					continue;
				Node n2 = cities[j];
				double currentDistance = graph.getDistance(n1, n2);
				if (currentDistance < bestDistance) {
					bestDistance = currentDistance;
					bestNode = n2;
					usedIndex = j;
				}

			}
			if (bestNode == null)
				continue;

			used[usedIndex] = true;
			tour[i] = bestNode;
		}
		return tour;
	}

	public void readGraph() {
		InputStream is = new DataInputStream(System.in);
		Kattio kattio = new Kattio(is);
		int n = kattio.getInt();
		Node[] nodes = new Node[n + 1];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, kattio.getDouble(), kattio.getDouble());
		}
		nodes[nodes.length - 1] = nodes[0];
		this.graph = new Graph(nodes);
	}

	private Node[] shuffle(Node[] tour) {
		int n = 3;
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			int i1 = rnd.nextInt(tour.length - 2) + 1;
			int i2 = rnd.nextInt(tour.length - 2) + 1;

			while (i1 == i2)
				i2 = rnd.nextInt(tour.length - 2) + 1;

			tour = reverse(tour, i1, i2);
		}
		return tour;
	}

	private static Node[] reverse(Node[] tour, int start, int end) {
		Node[] newTour = tour.clone();

		for (int i = start; i <= end; i++) {
			newTour[i] = tour[end - (i - start)];
		}
		return newTour;
	}
}
