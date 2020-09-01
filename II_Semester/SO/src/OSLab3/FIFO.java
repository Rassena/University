package OSLab3;

import java.util.ArrayList;

public class FIFO {
	private int[] memory;
	int first;
	int siteErrors;
	private ArrayList<Integer> list;
	
	
	public FIFO(int size,ArrayList<Integer> ar) {
		memory =new int[size];
		list =ar;
	}
	
	public void run() {
		clear();
		for(int i=0;i<list.size();i++)
			nextSite(list.get(i));
		System.out.println("FIFO bledy: " + siteErrors);
	}
	
	private void nextSite(int next) {
		boolean inMemory=false;
		for(int i=0;i<memory.length;i++) {
			if(memory[i]==next)
				inMemory=true;
		}
		if(!inMemory) {
			siteErrors++;
			memory[first] = next;
			first++;
			first=first%memory.length;
		}
	}
	
	private void clear() {
		for(int i=0;i<memory.length;i++) {
			memory[i]=-1;
		}
		first=0;
		siteErrors=0;
	}

}
