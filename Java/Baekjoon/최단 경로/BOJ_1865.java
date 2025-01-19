import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1865 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());   // �׽�Ʈ���̽��� ����
        for (int t = 0; t < TC; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // ������ ��
            int M = Integer.parseInt(st.nextToken());   // ������ ����
            int W = Integer.parseInt(st.nextToken());   // ��Ȧ�� ����

            int[][] dist = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            // ������ ����
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());   // ���� ����
                int E = Integer.parseInt(st.nextToken());   // ���� ����
                int T = Integer.parseInt(st.nextToken());   // �ɸ��� �ð�
                dist[S][E] = Math.min(dist[S][E], T);
                dist[E][S] = Math.min(dist[E][S], T);
            }

            // ��Ȧ�� ����
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                dist[S][E] = Math.min(dist[S][E], -T);
            }

            // �÷��̵�-����
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (dist[i][k] != INF && dist[k][j] != INF) {
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        }
                    }
                }
            }

            boolean found = false;

            // ���� ����ġ ����Ŭ Ž��
            for (int i = 1; i <= N; i++) {
                if (dist[i][i] < 0) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
