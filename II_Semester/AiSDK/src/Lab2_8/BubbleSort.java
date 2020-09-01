package Lab2_8;

public class BubbleSort {
	private int compares=0;
	private int changes=0;
	
	public BubbleSort() {}

	public int[] sort(int list[]){ 
		for (int i=0;i<list.length;i++) {
			for(int j=0;j<list.length-i-1;j++) {
				compares++;
				if(list[j]>list[j+1])
					swap(list,j,j+1);
			}
	      }
		return list;
	}
	
	private void swap(int list[],int left,int right) {
		int temp = list[left];
		list[left]=list[right];
		list[right]=temp;
		changes++;
	}
	
	public int getCompares() {
		return compares;
	}
	
	public int getChanges() {
		return changes;
	}
	
	public void clear() {
		compares=0;
		changes=0;
	}
	
}
