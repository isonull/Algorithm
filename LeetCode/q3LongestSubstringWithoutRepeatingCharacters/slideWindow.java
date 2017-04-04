package q3LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;

public class slideWindow {
	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		
		HashMap<Character,Integer> sto = new HashMap<>();
		int j = 0;
		for(int i = 0; i!=s.length();++i){
			if(sto.containsKey(s.charAt(i))){
				j= Math.max(sto.get(s.charAt(i))+1,j);
			}
			sto.put(s.charAt(i), i);
			max = Math.max(max,i-j+1);
		}
		return max;
    }
}
