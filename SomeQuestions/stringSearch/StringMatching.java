package stringSearch;

import java.util.ArrayList;

public class StringMatching {
	public static ArrayList<Integer> stringSearch(char[] string, char[] pattern) {
		ArrayList<Integer> result = new ArrayList<>();
		int lenStr = string.length;
		int lenPat = pattern.length;
		ArrayList<Integer> bt = new ArrayList<>();
		for (int i = 0; i <= lenStr - lenPat; ++i) {
			if (pattern[0] == string[i]) {
				bt.add(i);
			}
		}
		if (lenPat == 1) {
			return bt;
		}
		for (Integer p : bt) {
			for (int i = 1; i < lenPat; ++i) {
				if (string[p + i] != pattern[i]) {
					break;
				}
				if (i == lenPat - 1) {
					result.add(p);
				}
			}
		}
		return result;
	}

	public static ArrayList<Integer> btGenerate(char[] string, char[] pattern) {
		int rep = 0;
		int lenStr = string.length;
		int lenPat = pattern.length;
		ArrayList<Integer> bt = new ArrayList<>();
		while (++rep < lenPat) {
			if (pattern[rep] != pattern[0]) {
				break;
			}
		}
		for (int i = rep - 1; i <= lenStr - lenPat; i += rep) {
			if (pattern[0] == string[i]) {
				for (int j = -1; j > -rep; --j) {
					if (pattern[0] != string[i + j]) {
						break;
					} else {
						--i;
					}
				}
				bt.add(i);
			}
		}
		return bt;
	}

	public static void main(String[] args) {
		for (Integer integer : btGenerate("eebeeb".toCharArray(), "ee".toCharArray())) {
			System.out.println(integer);
		}
		System.out.println("----------------------------");
		for (Integer integer : stringSearch("aaaaa".toCharArray(), "a".toCharArray())) {
			System.out.println(integer);
		}
	}
}
