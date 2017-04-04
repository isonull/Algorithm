package q3LongestSubstringWithoutRepeatingCharacters;

//This is a terrible trial... Exponential time complexity.
public class divideAndConquer {
	public int lengthOfLongestSubstring(String s) {
		if(s.length()==0) return 0;
        return findMax(s.toCharArray(), 0, s.length() - 1);
    }
    public int findMaxCrossing(char[] chs, int beg, int i, int j, int end){
    
    	boolean l = i>beg&&!contain(chs,chs[i-1],i,j);
    	boolean r = j<end&&!contain(chs,chs[j+1],i,j);
    	
    	if(l&&r){return Integer.max(findMaxCrossing(chs, beg, i-1, j, end), findMaxCrossing(chs, beg, i, j+1, end));}
    	if(l){return findMaxCrossing(chs, beg, i-1, j, end); }
    	if(r){return findMaxCrossing(chs, beg, i, j+1, end);}
    	
    	return j-i+1;
    }
    public int findMax(char[] chs, int beg, int end){
    	if(beg==end) return 1;
    	int mid = (beg+end)/2;
    	System.out.print(beg);
    	System.out.print(" ");
    	System.out.println(end);
        return Integer.max(Integer.max(findMax(chs,beg,mid), findMax(chs,mid+1,end)),findMaxCrossing(chs,beg,mid,mid,end));
    }
    public boolean contain(char[] chs, char c,int beg,int end){
        for(;beg<=end;++beg){
        	if(c==chs[beg]) return true;
        }
        return false;
    }
}
