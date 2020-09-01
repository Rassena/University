package Lab2_6;

public class SelectSort <T>{
	private Comparator<T> comparator;
	
	public SelectSort(Comparator<T> comp) {
		comparator = comp;
	}
	
	public T[] sort(T[] list) {
		for(int i =0;i<list.length -1;i++) {
			int smallest= i;
			for(int check=i+1;check<list.length;check++)
				if(comparator.compare(list[check], list[smallest])<0)
					smallest=check;
			swap(list, smallest, i);
		}
		return list;
	}
	
	private void swap(T[] list, int left,int right) {
		if(left != right) {
			T temp = list[left];
			list[left] = list[right];
			list[right] = temp;
		}
	}

}
