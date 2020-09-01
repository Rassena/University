package OSLab3;

import java.util.ArrayList;

public class ALRU {
	private int[][] memory;
	int first;
	int siteErrors;
	private ArrayList<Integer> list;
	
	/*
	 * Algorytm drugiej szansy
	 */
	
	
	public ALRU(int size,ArrayList<Integer> ar) {
		memory =new int[size][2];
		list =ar;
	}
	
	public void run() {
		clear();
		for(int i=0;i<list.size();i++)
			nextSite(list.get(i));
		System.out.println("ALRU bledy: " + siteErrors);
	}
	
	private void nextSite(int next) {
		boolean inMemory=false;
		for(int i=0;i<memory.length;i++) {
			if(memory[i][0]==next) {
				inMemory=true;
				memory[i][1]=1;
			}
		}
		if(!inMemory) {
			siteErrors++;
			boolean found=false;
			while(!found) {
				if(memory[first][1]==0) {
					memory[first][0]=next;
					memory[first][1]= 1;
					found=true;
				}
				else
					memory[first][1]=0;
				first++;
				first=first%memory.length;
			}

		}
	}
	
	private void clear() {
		for(int i=0;i<memory.length;i++) {
			memory[i][0]=-1;
			memory[i][1]=-1;
		}
		first=0;
		siteErrors=0;
	}

	
}
