package transitiveClosure;

public class TransitiveClosure {
	boolean[][] generateNext(boolean[][] G1) {
		int size = G1.length;
		boolean[][] G2 = new boolean[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (G1[i][j] == true) {
					G2[i][j] = true;
					continue;
				}
				for (int k = 0; k < size; ++k) {
					if (G1[i][k] && G1[k][j] == true) {
						G2[i][j] = true;
						break;
					}
				}
			}
		}
		return G2;
	}

	boolean[][] generateTransitiveClosure(boolean[][] G) {
		int size = G.length;
		boolean[][] TC = G.clone();
		boolean[][] nextTC;
		for (int t = 1; t < size; ++t) {
			nextTC = generateNext(TC);
			if (equal(TC, nextTC)) {
				return TC;
			}
			TC = nextTC;
		}
		return TC;
	}

	boolean equal(boolean[][] A, boolean[][] B) {
		int size = A.length;
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (A[i][j] != B[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
