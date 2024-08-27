import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16236 {

    static int N;
    static int[][] map;
    static int sharkX, sharkY;
    static int sharkSize = 2, fishCount = 0, time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 1;
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            int result = BFS();
            if (result == -1) break;
            time += result;
        }
        System.out.println(time);
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int BFS() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) {       // �Ÿ��� ���� ���
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];   // ���� ���� �ִ� ����Ⱑ �����������, ���� ���ʿ� �ִ� ����Ⱑ �켱����
                }
                return o1[0] - o2[0];   // ���� �켱 ��
            }
            return o1[2] - o2[2];       // �Ÿ��� �ٸ��� ������ ����
        });

        pq.offer(new int[]{sharkX, sharkY, 0});

        boolean[][] visited = new boolean[N][N];
        visited[sharkX][sharkY] = true;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            if (map[x][y] > 0 && map[x][y] < sharkSize) {
                map[x][y] = 0; // ����⸦ ����
                sharkX = x;
                sharkY = y;
                fishCount++;

                if (fishCount == sharkSize) { // ��� ũ�� ����
                    sharkSize++;
                    fishCount = 0;
                }
                return distance; // �̵� �Ÿ� ��ȯ
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && map[nx][ny] <= sharkSize) {
                    visited[nx][ny] = true;
                    pq.offer(new int[]{nx, ny, distance + 1});
                }
            }
        }
        return -1; // �� �̻� ���� �� �ִ� ����Ⱑ ���� ���
    }
}
