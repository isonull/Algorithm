package imageConvolotion;

public class MatrixConvolution {
	public static int[][] convolution(int[][] A, int[][] G) {
		int nA = A.length;
		int nG = G.length;
		int d = nG / 2;
		int[][] B = new int[nA][nA];

		for (int i = 0; i < nG; ++i) {
			for (int j = 0; j < nG; ++j) {
				for (int k = 0; k < nG; ++k) {
					for (int l = 0; l < nG; ++l) {
						B[i][j] = G[k][l] * A[i - d + k][j - d + l];
					}
				}
			}
		}
		return B;
	}
}
