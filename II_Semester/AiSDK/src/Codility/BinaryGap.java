package Codility;

import java.util.ArrayList;

public class BinaryGap {

        public static void main(String[] args) {
        	int N = 1025;
        	
        	 ArrayList<Integer> binary = new ArrayList<Integer>();
             int zeros=0;
             int max=0;
                  while(N>0){
                     	binary.add(N%2);
                     	N=N/2;
                  }
                 for(int i = binary.size();i>0;i--){
                     int current = binary.get(i-1);
                     if(current == 0){
                         zeros++;
                     }
                     else{
                         if(max<zeros)
                             max=zeros;
                         zeros=0;
                     }
                 }
                 System.out.println(max);       	
                 }
}

