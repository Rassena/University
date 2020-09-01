package Lab2_6;

import java.util.ArrayList;

public class CompoundComparator<T> implements Comparator<T> {
	
	private ArrayList<Object> comparators = new ArrayList<Object>();
	
	public void addComparator(Comparator<T> comp) {
		comparators.add(comp);
	}
	
	@SuppressWarnings("unchecked")
	public int compare(T left, T right) {
		int result=0;
		
		for(Object obj: comparators) {
			Comparator<T> comp = (Comparator<T>) obj;
			result=comp.compare(left, right);
			if(result!=0) break;
		}
		return result;
	}
	
	

}
