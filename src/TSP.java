import java.io.DataInputStream;
import java.io.InputStream;

public class TSP {

	Graph graph;

	public Node[] solve(double deadline) {
		// System.out.println(calculateTotalDistance(graph.getNodes()));
		Node[] tour = graph.getCities();
		if (tour.length == 2) {
			System.out.println(tour[0].getIndex());
			System.out.println(tour[1].getIndex());
			return null;
		}

		Node[] greedyTour = null;

		greedyTour = greedyTour();
		tour = greedyTour;
		// while (System.currentTimeMillis() < deadline)
		// tour = Opt.twoOpt(graph, tour, deadline);
		tour = Opt.threeOpt(graph, tour, deadline);

		for (int i = 0; i < tour.length; i++) {
			System.out.println(tour[i].getIndex());
		}
		return tour;

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
}
