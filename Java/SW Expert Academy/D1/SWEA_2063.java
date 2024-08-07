import java.util.Arrays;
import java.util.Scanner;

public class SWEA_2063 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        arr = Arrays.stream(arr).sorted().toArray();

        System.out.println(arr[N/2]);

        sc.close();
    }
}