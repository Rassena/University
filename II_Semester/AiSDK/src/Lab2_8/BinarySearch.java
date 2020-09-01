package Lab2_8;

public class BinarySearch {
	private int pch;
	private int ptr;
	private boolean found;
	int trafione;
	
	public BinarySearch() {}
	
	public void search(int[] list,int search) {
		int compares=0;
		found=false;
		int pivot;
		int left=0;
		int right=list.length-1;
		while(left<=right&&!found) {
			pivot=(left+right)/2;
			if(list[pivot]==search) {
				found=true;
				trafione++;
			}
			else
				if(list[pivot]<search)
					left=pivot+1;
				else
					right=pivot-1;
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
	}
	
	public int getTrafione() {
		return trafione;
	}
	
}
