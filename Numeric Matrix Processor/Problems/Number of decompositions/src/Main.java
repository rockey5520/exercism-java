import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int count = scanner.nextInt();
        decomp(count);
    }

    public static void decomp(int n) {
        if (n > 1) {
            decomp(n, n - 1, "");
        }
        System.out.println(n);
    }

    public static void decomp(int n, int max, String prefix) {
        if (max > 1) {
            decomp(n, max - 1, prefix);
        }
        if (n - max > 0) {
            decomp(n - max, max, prefix + max + " ");
        } else if (n - max == 0) {
            System.out.println(prefix + max);
        }
    }
}