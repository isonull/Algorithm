package q7ReverseInteger;

public class Modulus {
	public int reverse(int x) {
		int ans = 0;
		int temp = 0;
		while(x!=0){
			temp = ans * 10 + x % 10;
			if(temp/10 != ans) return 0;
			x=x/10;
			ans = temp;
		}
		return ans;
	}
	
	public static void main(String[] args){
		System.out.println((new Modulus()).reverse(-1233421499));
	}
}
