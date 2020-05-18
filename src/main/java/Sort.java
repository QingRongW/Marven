import java.util.Arrays;
import java.util.Scanner;

class SolutionSort {


    private static int[] insertItem(int[] a, int aItem, int len) {
        if(len == 1){
            a[0] = aItem;
            return a;
        }
        int[] copy = a;

        boolean inserted = false;
        for (int i = 0; i < len; i++) {
            if (!inserted) {
                if (a[i] > aItem) {
                    copy[i] = a[i];
                } else {
                    copy[i] = aItem;
                    i = i - 1;
                    inserted = true;
                }
            } else {
                copy[i+1] = a[i];
            }
        }
        return copy;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        for (int i = 0; i < aCount; i++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a = insertItem(a, aItem, i + 1);
            if (i % 2 == 0) {
                int j = i / 2;
                System.out.println(a[j]);
            } else {
                int j = (i + 1) / 2;
                int medium = (a[j - 1] + a[j]) / 2;
                System.out.println(medium);
            }
        }

    }


}
