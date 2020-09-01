package Lab2_6;

public class InsertSort<T> {
	private Comparator<T> comparator;
	
	public InsertSort(Comparator<T> comp){
		comparator = comp;
	}
	
	public T[] sort(T[] list) {
		for(int i=1;i< list.length;i++) {
			T value = list[i];
			T temp;
			int j;
			for(j=i;j>0&&comparator.compare(value, temp = list[j-1])<0;j--) {
				list[j]=temp;
			}
			list[j] =value;
		}
		return list;
	}
	
	

}
