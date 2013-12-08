import java.util.Random;

public class Opt {

	public static Node[] twoOpt(Graph graph, Node[] tour, double deadline) {

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
				if (hasChanged)
					break;
			}
		}

		return tour;
	}

	public static Node[] threeOpt(Graph graph, Node[] tour, double deadline) {

		Node[] bestTour = tour.clone();
		while (System.currentTimeMillis() < deadline) {
			// for (int n = 0; n < 100; n++) {
			Random rnd = new Random();
			Node[] deleted = new Node[3];
			int[] deletedIndexes = new int[3];
			double[][] parts = new double[3][];

			for (int i = 0; i < 3; i++) {
				int deleteIndex = rnd.nextInt(tour.length - 2) + 1;
				while (tour[deleteIndex] == null) {
					deleteIndex = rnd.nextInt(tour.length - 2) + 1;
				}
				deleted[i] = tour[deleteIndex];
				deletedIndexes[i] = deleteIndex;
				tour[deleteIndex] = null;
			}

			double bestDistance = graph.calculateTotalDistance(bestTour);

			for (int i = 0; i < 3; i++) {
				tour[deletedIndexes[0]] = deleted[i];
				for (int j = 0; j < 3; j++) {
					if (System.currentTimeMillis() >= deadline)
						return bestTour;

					if (j == i)
						continue;
					tour[deletedIndexes[1]] = deleted[j];
					for (int k = 0; k < 3; k++) {
						if (k == i || k == j)
							continue;
						tour[deletedIndexes[2]] = deleted[k];
						double currentDistance = graph
								.calculateTotalDistance(tour);
						if (currentDistance < bestDistance) {
							bestDistance = currentDistance;
							bestTour = tour.clone();
						}
					}
				}
			}
			tour = bestTour.clone();
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
