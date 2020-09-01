package Lab2_8;

public class Heap {
	private int[] Heap; 
    private int size; 
    private int maxsize; 
   
    public Heap(int maxsize) { 
        this.maxsize = maxsize; 
        this.size = 0; 
        Heap = new int[this.maxsize + 1]; 
        Heap[0] = 0; 
    } 

    private int parent(int pos) { 
        return pos / 2; 
    } 
  
    private int leftChild(int pos)  { 
        return (2 * pos); 
    } 
    
    private int rightChild(int pos) { 
        return (2 * pos) + 1; 
    } 
    
    private boolean isLeaf(int pos)  { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
  
    private void swap(int fpos, int spos) { 
        int tmp; 
        tmp = Heap[fpos]; 
        Heap[fpos] = Heap[spos]; 
        Heap[spos] = tmp; 
    } 
  
    private void Heapify(int pos) { 
        if (isLeaf(pos)) 
            return; 
  
        if (Heap[pos] > Heap[leftChild(pos)] ||  
            Heap[pos] > Heap[rightChild(pos)]) { 
  
            if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) { 
                swap(pos, leftChild(pos)); 
                Heapify(leftChild(pos)); 
            } 
            else { 
                swap(pos, rightChild(pos)); 
                Heapify(rightChild(pos)); 
            } 
        } 
    } 
  
    public void enqueue(int value) { 
        Heap[++size] = value;
        swim(size);
    }
    
    private void swim(int index) {
        int current = index; 
        while (Heap[current] < Heap[parent(current)]) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    }
    
    public void changeValue(int value, int index) {
        int current = index + 1;
        Heap[current] = value;
        
        while (Heap[current] < Heap[parent(current)]) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
        Heapify(1);  
    }
    
    public int getByIndex(int index) {
        int popped = Heap[index+1]; 
        Heap[index+1] = Heap[size--];
        swim(index+1);
        Heapify(1); 
        return popped; 
    }
    
    public void print() { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print("PARENT: " + Heap[i] + " LEFT CHILD: " + 
                      Heap[2 * i] + " RIGHT CHILD: "); 
            if(2*i +1<maxsize) {System.out.print(Heap[2 * i + 1]);} 
            System.out.println();
        } 
        System.out.println();
    } 

    public int dequeue() { 
        int popped = Heap[1]; 
        Heap[1] = Heap[size--];
        swim(1);
        Heapify(1); 
        return popped; 
    }

}
