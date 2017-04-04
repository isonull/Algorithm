package q4MedianofTwoSortedArrays;

//fail to pass the tester for high time complexity
public class Merge {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		return median(merge(nums1, nums2));
	}

	public int[] merge(int[] a1, int[] a2) {
		if (a1.length == 0)
			return a2;
		if (a2.length == 0)
			return a1;
		int i = 0;
		int j = 0;
		int k = 0;
		int[] m = new int[a1.length + a2.length];
		while (i != a1.length && j != a2.length) {
			if (a1[i] <= a2[j]) {
				m[k++] = a1[i++];
			} else {
				m[k++] = a2[j++];
			}
		}
		if (i == a1.length) {
			while (j != a2.length) {
				m[k++] = a2[j++];
			}
		} else {
			while (i != a1.length) {
				m[k++] = a1[i++];
			}
		}
		return m;
	}

	public double median(int[] is) {
		if (is.length % 2 == 0) {
			return ((double) is[is.length / 2] + (double) is[is.length / 2 - 1]) / 2;
		}
		return is[is.length / 2];
	}
}
