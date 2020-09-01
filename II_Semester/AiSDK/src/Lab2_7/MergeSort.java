package Lab2_7;

public class MergeSort {
	private int compares;
	private int changes;
	
	public MergeSort() {}
	
    public int[] sort(int arr[], int l, int r) { 
        if (l < r) { 
            int m = (l+r)/2; 
            sort(arr, l, m); 
            sort(arr , m+1, r); 
            merge(arr, l, m, r); 
        } 
        return arr;
    } 
	
    public void merge(int arr[], int l, int m, int r){ 
        int n1 = m - l + 1; 
        int n2 = r - m; 

        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
        int i = 0, j = 0; 
        int k = l; 
        
        while (i < n1 && j < n2) { 
        	compares++;
            if (L[i] <= R[j]) { 
                arr[k] = L[i]; 
                i++; 
            } 
            else{ 
                arr[k] = R[j]; 
                j++; 
            }
            k++; 
        } 
        while (i < n1) {
        	changes++;
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while (j < n2){ 
        	changes++;
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
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
