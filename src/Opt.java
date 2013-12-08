public class Opt {

	public Node[] twoOpt(Graph graph, Node[] tour, double deadline) {
		boolean hasChanged = true;
		while (hasChanged) {
			hasChanged = false;
			for (int i = 0; i < tour.length - 1; i++) {
				Node u1 = tour[i];
				Node u2 = tour[i + 1];
				Node v1 = null;
				Node v2 = null;

				for (int j = i + 2; j < tour.length - 1; j++) {
					v1 = tour[j];
					v2 = tour[j + 1];

					if (System.currentTimeMillis() >= deadline)
						return tour;

					if ((graph.getDistance(u1, u2) + graph.getDistance(v1, v2)) > (graph
							.getDistance(u1, v1) + graph.getDistance(u2, v2))) {
						tour = reverse(tour, i + 1, j);
						hasChanged = true;
						break;
					}
				}
				if (hasChanged && tour.length < 450)
					break;
			}
		}

		return tour;
	}

	private Node[] reverse(Node[] tour, int start, int end) {
		Node[] newTour = tour.clone();

		for (int i = start; i <= end; i++) {
			newTour[i] = tour[end - (i - start)];
		}
		return newTour;
	}
}
