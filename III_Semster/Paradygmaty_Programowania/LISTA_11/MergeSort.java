import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class MergeSort extends Thread implements Runnable{

    private Thread[] threads= new Thread[Runtime.getRuntime().availableProcessors()];
    private  int[] table;

    public MergeSort(){
    }


    public MergeSort(int[] table){
        this.table = table;
    }

    public void singleThreadSort(int[] table, int left,int right ){
        if(left<right) {
            int pivot = (left + right) / 2;
            singleThreadSort(table, left, pivot);
            singleThreadSort(table,pivot+1,right);
            singleThreadMerge(table,left,pivot,right);
        }
     //   System.out.println(Thread.currentThread().getName());

    }


    public void multi (int[] table) {

        this.table = table;

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(this::run, "Thread_" + i);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ArrayList<Integer> list = new ArrayList();

        int left=0;

        for (int i = 0; i < threads.length; i++) {
            left = left + (table.length -left) / (threads.length - i);
            list.add(left);
        }


        while (list.size()>1) {
            singleThreadMerge(table,0,list.remove(0)-1,list.get(0)-1);
        }


    }


    private void singleThreadMerge(int[] table, int left, int pivot, int right){
        int size1 = pivot - left  + 1;
        int size2 = right - pivot;

        int[] leftTable = new int[size1];
        int[] rightTable = new int[size2];

        for (int i=0; i<size1;i++) {
            leftTable[i] = table[left+i];
        }
        for (int j =0; j<size2; j++){
            rightTable[j] = table[pivot+1+j];
        }

        int i=0;
        int j=0;
        int k=left;

        while (i<size1 && j <size2){
            if (leftTable[i]<=rightTable[j]){
                table[k] = leftTable[i];
                i++;
            }
            else {
                table[k] = rightTable[j];
                j++;
            }
            k++;
        }

        while (i<size1){
            table[k] = leftTable[i];
            i++;
            k++;
        }

        while (j<size2){
            table[k] = rightTable[j];
            j++;
            k++;
        }

    }

    private boolean check (int[] table,int left, int right){
        for(int i =left+1;i<=right;i++){
            if(table[i-1]>table[i]) return false;
        }
        return true;
    }

    public void run() {

        int left = 0;
        int right = table.length;
        int rightEnd;
        int leftStart=left;
    //    System.out.println("wtf " + Thread.currentThread().getName());

        for (int i = 0; i < threads.length; i++) {
            rightEnd = leftStart + (table.length -leftStart) / (threads.length - i)-1;
              if (threads[i].getId() == Thread.currentThread().getId()) {
               //   System.out.println(Thread.currentThread().getId()+ " " + Thread.currentThread().getName()+ " " + leftStart + " " + rightEnd);
                  singleThreadSort(table, leftStart, rightEnd);
              //    System.out.println(Thread.currentThread().getName()+ " " + check(table,leftStart,rightEnd));
              }
            leftStart = rightEnd + 1;
        }

    }

}
