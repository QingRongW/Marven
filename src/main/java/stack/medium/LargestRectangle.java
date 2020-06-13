package stack.medium;

import contants.Path;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


public class LargestRectangle {
    // Complete the largestRectangle function below.
    static long largestRectangle(int[] height) {
        // prepare the data
        int maxArea = 0;
        int n = height.length;
        height = Arrays.copyOf(height, n + 1);
        height[n] = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int curHeight = height[i];
            if (stack.isEmpty() || height[stack.peek()] < curHeight) {
                stack.push(i);
            } else {
                while (!stack.isEmpty()) {
                    int stackTop = stack.pop();
                    int width;
                    if (stack.isEmpty()) {
                        width = i;
                    } else {
                        width = i - stack.peek() - 1;
                    }
                    maxArea = Math.max(maxArea, width * height[stackTop]);
                    if (stack.isEmpty() || curHeight > height[stack.peek()]) {
                        stack.push(i);
                        break;
                    }
                }
            }
        }
        return maxArea;
    }


    static long largestRectangle_Standard(int[] height) {
        Stack<Integer> s = new Stack<>();
        int len = height.length;
        height = Arrays.copyOf(height, len + 1);
        height[len] = 0;

        long sum = 0;
        int i = 0;
        while (i < height.length) {
            if (s.isEmpty() || height[i] > height[s.peek()]) {
                s.push(i);
                i++;
            } else {
                int t = s.peek();
                s.pop();
                sum = Math.max(sum, height[t] * (s.empty() ? i : i - s.peek() - 1));
            }

        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Path.OUTPATH));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

