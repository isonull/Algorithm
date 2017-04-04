package binaryToDecimal;

import java.util.ArrayList;

public class BinToDec {

	public static ArrayList<Integer> decAdd(ArrayList<Integer> dec1, ArrayList<Integer> dec2) {
		ArrayList<Integer> dec = new ArrayList<>();
		ArrayList<Integer> lDec;
		int minLen = Math.min(dec1.size(), dec2.size());
		int maxLen = Math.max(dec1.size(), dec2.size());

		if (dec1.size() >= dec2.size()) {
			maxLen = dec1.size();
			minLen = dec2.size();
			lDec = dec1;
		} else {
			maxLen = dec2.size();
			minLen = dec1.size();
			lDec = dec2;
		}

		int carry = 0;
		int temp = 0;
		for (int i = 0; i < minLen; ++i) {
			temp = carry + dec1.get(i) + dec2.get(i);
			carry = temp / 10;
			dec.add(temp % 10);
		}

		for (int i = minLen; i < maxLen; ++i) {
			temp = carry + lDec.get(i);
			carry = temp / 10;
			dec.add(temp % 10);
		}
		if (carry != 0) {
			dec.add(carry);
		}
		return dec;
	}

	public static ArrayList<Integer> binToDec(ArrayList<Boolean> bin) {
		ArrayList<Integer> dec = new ArrayList<>();
		ArrayList<Integer> twoPower = new ArrayList<>();
		twoPower.add(1);
		for (int i = 0; i < bin.size(); ++i) {
			if (bin.get(i)) {
				dec = decAdd(dec, twoPower);
			}
			twoPower = decAdd(twoPower, twoPower);
		}
		return dec;
	}

	public static void main(String[] args) {
		ArrayList<Integer> dec1 = new ArrayList<>();
		boolean[] bin11 = { true, true, true, true, true, true };
		ArrayList<Boolean> bin1 = new ArrayList<>();
		for (Boolean boolean1 : bin11) {
			bin1.add(boolean1);
		}
		dec1.add(1);
		ArrayList<Integer> dec = binToDec(bin1);
	}
}
