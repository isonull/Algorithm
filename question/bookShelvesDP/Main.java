package bookShelvesDP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Main {
	public static int n = 4;
	public static Integer[] b = { 3, 2, 1, 1 };
	public static Integer[] w = { 12, 14, 25, 32 };
	public static int W = 50;
	public static ArrayList<Integer[]> choice = new ArrayList<>();
	public static Hashtable<List<Integer>, Integer> memory = new Hashtable<>();

	public void constructPossibleChoice(int wi, int pointer, Integer[] ch) {
		if (wi < w[0]) {
			choice.add(ch);
		} else if (pointer >= 0) {
			Integer[] newch = new Integer[n];
			for (int i = 0; i < n; ++i) {
				newch[i] = ch[i];
			}
			constructPossibleChoice(wi, pointer - 1, newch);
			if (wi - w[pointer] >= 0) {
				newch[pointer] += 1;
				constructPossibleChoice(wi - w[pointer], pointer, newch);
			}
		}
	}

	public int findMinNoOfShelves(Integer[] bk) {
		Integer min = memory.get(Arrays.asList(bk));
		if (min != null) {
			return min;
		}
		boolean allZero = true;
		for (Integer i : bk) {
			if (i > 0)
				allZero = false;
		}
		if (allZero) {
			return 0;
		}
		min = Integer.MAX_VALUE;
		Integer[] minKey = new Integer[n];
		boolean cont = true;
		for (Integer[] is : choice) {
			cont = true;
			Integer[] newbk = new Integer[n];
			for (int i = 0; i < n; ++i) {
				if (bk[i] > 0 && is[i] > 0) {
					// Check there are such books to remove.
					cont = false;
				}
				newbk[i] = bk[i] - is[i];
				if (newbk[i] < 0) {
					newbk[i] = 0;
				}
			}
			if (cont) {
				continue;
			}
			int tempNewMin = findMinNoOfShelves(newbk);
			if (min > 1 + tempNewMin) {
				for (int i = 0; i < n; ++i) {
					minKey[i] = bk[i];
				}
				min = 1 + tempNewMin;
			}
		}

		for (Integer i : minKey) {
			System.out.print(i + " ");
		}
		System.out.print("with min: " + min + "\n");
		memory.put(Arrays.asList(minKey), min);
		return min;
	}

	public static void main(String args[]) {
		Main main = new Main();
		Integer[] integers = new Integer[n];
		for (int i = 0; i < n; ++i) {
			integers[i] = 0;
		}
		main.constructPossibleChoice(W, n - 1, integers);
		main.findMinNoOfShelves(b);
	}
}
