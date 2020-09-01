/*package Codility;

import java.util.ArrayList;
import java.util.ListIterator;

public class oddOccutrencesInArray {
	
	public static void main(String[]args) {
		int size = 100;
		
		
		int[] array = new int[size];
		int amount=0;
		for(int i =0; i>array.length;i++) {
			amount = 1;
			for(int checked = i+1; checked<array.length;checked++) {
				if(array[i]==array[checked])
					amount++;
			}
			if(amount%2!=0)
				array.
				return array[i];
		}					
										
		
		ArrayList<Integer> ar = new ArrayList<Integer>();
        for(int i =0;i<A.length;i++){
            ar.add(A[i]);
        }
        ar.sort(null);
        int amount=1;
        for(int i = 0; i<ar.size();i++){
            if(ar.get(i)==ar.get(i+1)){
                amount++;
            }
            else{
                if(amount%2!=0){
                    if(amount==1){
                        return ar.get(i+1);
                    }
                    else{
                        return ar.get(i);
                    }
                }
            }
            amount=1;
        }
        return -1;
    }
		
	}

}		*/
