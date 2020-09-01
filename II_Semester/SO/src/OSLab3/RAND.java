package OSLab3;

import java.util.ArrayList;
import java.util.Random;

public class RAND {
	private int[] memory;
	int siteErrors;
	private ArrayList<Integer> list;
	
	
	public RAND(int size,ArrayList<Integer> ar) {
		memory =new int[size];
		list =ar;
	}
	
	public void run() {
		clear();
		for(int i=0;i<list.size();i++)
			nextSite(list.get(i));
		System.out.println("RAND bledy: " + siteErrors);
	}
	
	private void nextSite(int next) {
		boolean inMemory=false;
		for(int i=0;i<memory.length;i++) {
			if(memory[i]==next)
				inMemory=true;
		}
		if(!inMemory) {
			siteErrors++;
			boolean found=false;
			for(int i=0;i<memory.length&&!found;i++) {
				if(memory[i]==-1) {
					memory[i]=next;
					found=true;
				}
			}
			if(!found) {
				Random r = new Random();
				memory[r.nextInt(memory.length)] = next;
			}
		}
	}
	
	private void clear() {
		for(int i=0;i<memory.length;i++) {
			memory[i]=-1;
		}
		siteErrors=0;
	}

}
