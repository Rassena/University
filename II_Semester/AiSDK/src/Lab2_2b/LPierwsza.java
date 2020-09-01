package Lab2_2b;

public class LPierwsza implements Predicate<Integer>{
	
	public boolean accept(int i) {
		if(i<=1)
			return false;
		if(i==2)
			return true;	
		for(int k=2;k<i;k++) {
			if(i%k==0)
				return false;
		}
		return true;
	}
	
	
}
