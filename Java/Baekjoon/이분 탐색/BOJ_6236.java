import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6236 {

    static int N, M;
    static int[] money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int min = 0;
        int max = 0;

        money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
            min = Math.max(min, money[i]);
            max += money[i];
        }

        int result = max;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (isPossible(mid)) {
                result = mid;
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }

        System.out.println(result);
    }

    static boolean isPossible(int mid) {
        int count = 1;
        int current = mid;

        for (int m: money) {
            // ���忡�� �� ������ �Ϸ縦 ���� �� ����
            // ���� �ݾ��� ���忡 ����ְ� �ٽ� K���� ����
            if (current < m) {
                // ���� Ƚ���� M���� Ŀ���� false
                if (count > M) {
                    return false;
                }

                count++;
                current = mid;
            }
            // ���忡�� �� ������ �Ϸ縦 ���� �� ����
            current -= m;
        }
        return count <= M;
    }
}
