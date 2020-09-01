package Lab2_8;

public class LineSearch {
	private int pch;
	private int ptr;
	private boolean found;
	private int trafione;
	
	public LineSearch() {}
	
	public void search(int[] list,int search) {
		int compares=0;
		found=false;
		for(int i=0;i<list.length&&!found;i++) {
			if(list[i]==search) {
				found=true;
				trafione++;
				}
			compares++;
		}
		count(compares);
	}
	
	private void count(int ammount) {
		if(found)
			ptr+=ammount;
		else
			pch+=ammount;
	}

	public int getPch() {
		return pch;
	}

	public int getPtr() {
		return ptr;
	}
	
	public void clear() {
		pch=0;
		ptr=0;
		trafione++;
	}
	public int getTrafione() {
		return trafione;
	}

}
