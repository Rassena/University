package OSLab3;

import java.util.ArrayList;

public class OPT {
	private int[][] memory;
	int siteErrors;
	private ArrayList<Integer> list;
	
	
	public OPT(int size,ArrayList<Integer> ar) {
		memory =new int[size][2];
		list =ar;
	}
	
	public void run() {
		clear();
		for(int i=0;i<list.size();i++)
			nextSite(list.get(i), i);
		System.out.println("OPT bledy: " + siteErrors);
	}
	
	private void nextSite(int next, int position) {
		boolean inMemory=false;
		for(int i=0;i<memory.length;i++) {
			if(memory[i][0]==next)
				inMemory=true;
		}
		if(!inMemory) {
			siteErrors++;
			memory[toReplace(next, position)][0]=next;
		}
	}
	
	private void clear() {
		for(int i=0;i<memory.length;i++) {
			memory[i][0]=-1;
			memory[i][1]=-1;
		}
		siteErrors=0;
	}
	
	private int toReplace(int next, int position) {
		for(int i=0;i<memory.length;i++) {
			boolean found=false;
			for(int j=position;j<list.size()&&!found;j++) {
				if(memory[i][0]==list.get(j)) {
					memory[i][1]=j;
					found=true;
				}
			}
			if(!found) {
				return i;
			}
		}
		int index =0;
		int farrest=memory[0][1];
		for(int i=0;i<memory.length;i++) {
			if(memory[i][1]>farrest) {
				farrest=memory[i][1];
				index=i;
			}
		}
		return index;
	}

}
