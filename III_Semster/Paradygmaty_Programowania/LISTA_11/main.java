import java.util.Random;

public class main {


    private static int[] makeTable(int size, int bound){

        Random random =new Random();
        int[] table = new int[size];

        for (int i =0;i<size;i++){
            table[i] = random.nextInt(bound);
        }

        return table;

    }

    private static void showTable(int[] table){

        for (int i=0; i<table.length;i++){
            System.out.print(table[i] + " ");
        }
        System.out.println();

    }

    private static void testSingleMerge(int[] table) {

        MergeSort mergeSort = new MergeSort();

        System.out.println("Table after single thread:");
     //   showTable(table);

        long time = System.currentTimeMillis();

        mergeSort.singleThreadSort(table,0,table.length-1);

        time = System.currentTimeMillis() - time;

        System.out.println("After merge sort:");
        //    showTable(table);

        System.out.println("time: " + time);
    }

    private static void testMultiMerge(int[] table) {

        MergeSort mergeSort = new MergeSort(table);

        System.out.println("Table after multi threads:");
    //    showTable(table);

        long time = System.currentTimeMillis();

        mergeSort.multi(table);

        time = System.currentTimeMillis() - time;

        System.out.println("After merge sort:");
     //   showTable(table);

        System.out.println("time: " + time);
    }

    private static boolean check(int[] table1, int[] table2){
        if(table1.length==table2.length){
            for(int i=0; i<table1.length;i++){
                if (table1[i]!=table2[i]){
                    System.out.println("Diference at: " + i + " table_1: " + table1[i] + " table_2: " + table2[i]);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean check(int[] table) {
        for (int i=1;i<table.length;i++){
            if(table[i-1]>table[i]) return false;
        }
        return true;
    }



    public static void main(String[]args) {
        int size=100000000;
        int[] example = makeTable(size,size);
        int[] copy = new int[example.length];

        for (int i =0; i<example.length;i++){
            copy[i] = example[i];
        }

        testSingleMerge(example);

        System.out.println("Is correct: " + check(example));

        System.out.println("----------");

        testMultiMerge(copy);

        System.out.println("----------");

        System.out.println("Same: " + check(example,copy));

    }

}
