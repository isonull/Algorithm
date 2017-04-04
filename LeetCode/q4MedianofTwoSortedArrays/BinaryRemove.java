package q4MedianofTwoSortedArrays;

public class BinaryRemove {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		// if disjoint...;
		// make the cardinality nums1 > nums2;
		// remove elements from nums2 until the size of nums2 <= 2;
		// finalise

		if (nums1.length < nums2.length) {
			int[] t = nums2;
			nums2 = nums1;
			nums1 = t;
		}

		if (nums2.length == 0) {
			return median(nums1, 0, nums1.length - 1);
		}

		int beg1 = 0;
		int end1 = nums1.length - 1;
		int beg2 = 0;
		int end2 = nums2.length - 1;
		int im2 = (end2 - beg2) / 2;
		double med1 = median(nums1, beg1, end1);
		double med2 = median(nums2, beg2, end2);

		while (end2 - beg2 > 1) {
			if (med1 == med2)
				return med1;
			if (med1 < med2) {
				beg1 = beg1 + im2;
				end2 = end2 - im2;
			} else {
				end1 = end1 - im2;
				beg2 = beg2 + im2;
			}
			im2 = (end2 - beg2) / 2;
			med1 = median(nums1, beg1, end1);
			med2 = median(nums2, beg2, end2);
		}

		int indexMed1 = (end1 + beg1) / 2;
		int[] tem = new int[6];
		int co = 0;
		if ((end1 - beg1) % 2 == 1) {
			if (indexMed1 > beg1)
				tem[co++] = nums1[indexMed1 - 1];
			tem[co++] = nums1[indexMed1];
			if (indexMed1 < end1)
				tem[co++] = nums1[indexMed1 + 1];
			if (indexMed1 < end1 - 1)
				tem[co++] = nums1[indexMed1 + 2];
			insert(tem, nums2[beg2], co++);
			if (beg2 != end2)
				insert(tem, nums2[end2], co++);

		} else {
			if (indexMed1 > beg1)
				tem[co++] = nums1[indexMed1 - 1];
			tem[co++] = nums1[indexMed1];
			if (indexMed1 < end1)
				tem[co++] = nums1[indexMed1 + 1];
			insert(tem, nums2[beg2], co++);
			if (beg2 != end2)
				insert(tem, nums2[end2], co++);
		}
		return median(tem, 0, co - 1);

	}

	public double median(int[] is, int l, int u) {
		int ind = (u + l) / 2;
		if ((u - l) % 2 == 1) {
			return ((double) is[ind] + (double) is[ind + 1]) / 2;
		}
		return is[ind];
	}

	public void insert(int[] ins, int num, int pos) {
		int temp;
		ins[pos] = num;
		while (pos > 0 && ins[pos] < ins[pos - 1]) {
			temp = ins[pos];
			ins[pos] = ins[pos - 1];
			ins[pos - 1] = temp;
			--pos;
		}
	}

	public static void main(String args[]) {
		int[] arr1 = { 1, 2 };
		int[] arr2 = { 1, 2, 3 };
		BinaryRemove aaa = new BinaryRemove();
		double a = aaa.findMedianSortedArrays(arr1, arr2);
		System.out.println(a);
	}

}
