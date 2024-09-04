import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17135 {

    static int N, M, D;
    static int[][] map;
    static int[] combinations;

    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        combinations = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.println(max);
    }

    static void comb(int start, int depth) {
        if (depth == 3) {
            // ���� ����
            play();
            return;
        }

        for (int i = start; i < M; i++) {
            combinations[depth] = i;
            comb(i + 1, depth + 1);
        }
    }

    static void play() {
        int[][] playMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            playMap[i] = map[i].clone();
        }

        int count = 0;
        for (int turn = 0; turn < N; turn++) {
            ArrayList<int[]> targets = new ArrayList<>();

            // �� �ü��� ��Ī�Ǵ� ���� ����Ʈ�� ����
            for (int archer : combinations) {
                int[] target = findEnemy(playMap, archer);
                if (target != null) {
                    targets.add(target);
                }
            }
            
            // �� ����
            for (int[] target : targets) {
                if(playMap[target[0]][target[1]] == 1) {
                    playMap[target[0]][target[1]] = 0;
                    count++;
                }
            }

            // �ü��� ������ ������, ���� �Ʒ��� �� ĭ �̵�
            moveEnemy(playMap);
        }

        max = Math.max(max, count);
    }

    static int[] findEnemy(int[][] playMap, int archer) {
        int[] target = null;

        int min = D + 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (playMap[i][j] == 1) {
                    int distance = Math.abs(N - i) + Math.abs(archer - j);
                    if (distance <= D) {
                        // �Ÿ��� ���� ����� ��, ������ ��쿡�� ���� ���ʿ� �ִ� ��
                        if (distance < min || (target != null && distance == min && j < target[1])) {
                            min = distance;
                            target = new int[]{i, j};
                        }
                    }
                }
            }
        }

        return target;
    }

    static void moveEnemy(int[][] playMap) {
        for (int i = N - 1; i > 0; i--) {
            playMap[i] = playMap[i - 1].clone();
        }
        Arrays.fill(playMap[0], 0);
    }
}
