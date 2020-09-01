package Lab2_7;

public class QuickSort{
	private int compares=0;
	private int changes=0;
	
	public QuickSort() {}

	public int[] sort(int arr[], int low, int high){ 
		if (low < high){ 
	        int pi = partition(arr, low, high); 
	        sort(arr, low, pi-1); 
	        sort(arr, pi+1, high); 
	      }
		return arr;
	}
	
	private int partition(int arr[], int low, int high) { 
	   int pivot = arr[high];  
	   int i = (low-1);
	   for (int j=low; j<high; j++){
		   compares++;
	       if (arr[j] <= pivot){ 
	           i++; 
	           int temp = arr[i]; 
	           arr[i] = arr[j]; 
	           arr[j] = temp;
	           changes++;
	       } 
	   } 
	   int temp = arr[i+1]; 
	   arr[i+1] = arr[high]; 
	   arr[high] = temp;
	   changes++;
	   return i+1; 
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
