import java.io.IOException;
import java.util.Scanner;

class SolutionBB {

    static boolean match(char left, char right) {
        boolean match = false;
        if (left == '(' && right == ')') {
            match = true;
        }
        if (left == '[' && right == ']') {
            match = true;
        }
        if (left == '{' && right == '}') {
            match = true;
        }
        return match;
    }

    static String isBalanced(String s) {
        int len = s.length();
        if (len == 1) {
            return "NO";
        } else if (len == 2) {
            if (match(s.charAt(0), s.charAt(1))) {
                return "YES";
            } else {
                return "NO";
            }
        } else {
            StringBuilder str = new StringBuilder(s);
            for (int i = 0; i < s.length() - 1; i++) {
                if (match(s.charAt(i), s.charAt(i + 1))) {
                    str.deleteCharAt(i);
                    str.deleteCharAt(i);
                    break;
                }
            }
            if (str.toString().equals(s)) {
                return "NO";
            } else {
                return isBalanced(str.toString());
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            System.out.println(result);
        }

        scanner.close();
    }
}
