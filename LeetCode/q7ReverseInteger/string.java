package q7ReverseInteger;

public class string {
	public int reverse(int x) {
		String xs = ((Integer) Math.abs(x)).toString();
		String ans = "";
		if(x<0) ans+="-";
		for(int i=xs.length()-1;i>-1;--i){
			ans += xs.charAt(i);
		}
		try{
			return Integer.parseInt(ans);
		}catch(Exception e){
			return 0;
		}
    }
}
