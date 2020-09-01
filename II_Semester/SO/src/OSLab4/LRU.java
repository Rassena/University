package OSLab4;

import java.util.ArrayList;

public class LRU {
	private int[][] memory;
	int siteErrors;
	private ArrayList<Integer> list;
	
	
	public LRU(int size,ArrayList<Integer> ar) {
		memory =new int[size][2];
		list =ar;
	}
	
	public int run() {
		clear();
		for(int i=0;i<list.size();i++)
			nextSite(list.get(i));
		return siteErrors;
	}
	
	public void step(int step) {
		if(list.size()!=step-1) {
			getNextSite(step);
		}
	}
	
	private void getNextSite(int next) {
		boolean inMemory=false;
		for(int i=0;i<memory.length;i++) {
			memory[i][1]++;
			if(memory[i][0]==next) {
				inMemory=true;
				memory[i][1]=0;
			}
		}
		if(!inMemory) {
			siteErrors++;
			memory[check()][0] = next;
		}
	}
	
	private void nextSite(int next) {
		boolean inMemory=false;
		for(int i=0;i<memory.length;i++) {
			memory[i][1]++;
			if(memory[i][0]==next) {
				inMemory=true;
				memory[i][1]=0;
			}
		}
		if(!inMemory) {
			siteErrors++;
			memory[check()][0] = next;
		}
	}
	
	private void clear() {
		for(int i=0;i<memory.length;i++) {
			memory[i][0]=-1;
		}
		siteErrors=0;
	}
	
	private int check() {
		int index=-1;
		int longest=-1;
		for(int i=0;i<memory.length;i++) {
			if(memory[i][1]>longest) {
				longest=memory[i][1];
				index=i;
			}
		}
		memory[index][1]=0;
		return index;
	}

}
