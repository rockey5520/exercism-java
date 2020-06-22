import java.util.Scanner;

public class Main {

    public static long fib(long n){
        if (n == 0) {
            return 0;
        }
        int i = 1, j = 1, next;
        for (long k = 2; k < n; k++) {
            next = i + j;
            i = j;
            j = next;
        }
        return n % 2 == 0 ? -j : j;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fib(n));
    }
}