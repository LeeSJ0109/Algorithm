import java.io.*;
import java.util.*;

public class BOJ_20057 {

    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static double[][] spreadRatio = {
            {   // ��
                    0, 0, 2, 0, 0,
                    0, 10, 7, 1, 0,
                    5, 0, 0, 0, 0,
                    0, 10, 7, 1, 0,
                    0, 0, 2, 0, 0
            },

            {   // ��
                    0, 0, 0, 0, 0,
                    0, 1, 0, 1, 0,
                    2, 7, 0, 7, 2,
                    0, 10, 0, 10, 0,
                    0, 0, 5, 0, 0
            },

            {   // ��
                    0, 0, 2, 0, 0,
                    0, 1, 7, 10, 0,
                    0, 0, 0, 0, 5,
                    0, 1, 7, 10, 0,
                    0, 0, 2, 0, 0
            },

            {   // ��
                    0, 0, 5, 0, 0,
                    0, 10, 0, 10, 0,
                    2, 7, 0, 7, 2,
                    0, 1, 0, 1, 0,
                    0, 0, 0, 0, 0
            },
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(tornado());
    }

    static int tornado() {
        int x = N / 2;
        int y = N / 2;

        int outSand = 0;
        int moveCount = 1;
        int direction = 0;

        while (true) {
            for (int step = 0; step < 2; step++) { // ��,�� or ��,��
                for (int move = 0; move < moveCount; move++) {
                    x += dx[direction];
                    y += dy[direction];

                    // �� �𳯸���
                    outSand += spreadSand(x, y, direction);

                    if (x == 0 && y == 0) {
                        return outSand;
                    }
                }
                // ���� ���� (�� -> ��- > �� -> ��)
                direction = (direction + 1) % 4;
            }

            moveCount++;
        }
    }

    static int leftSand;

    static int spreadSand(int x, int y, int direction) {
        int sandAmount = map[x][y];
        leftSand = sandAmount;
        map[x][y] = 0;

        // ������ ������ ���� ���� ��
        int outSand = 0;

        for (int i = 0; i < 25; i++) {
            int nx = x + (i / 5) - 2;
            int ny = y + (i % 5) - 2;
            int sand = (int)(sandAmount * spreadRatio[direction][i] / 100);
            outSand += checkRange(nx, ny, sand);
            leftSand -= sand;
        }

        // ���� �� ó��
        int nx = x + dx[direction];
        int ny = y + dy[direction];
        outSand += checkRange(nx, ny, leftSand);

        return outSand;
    }

    static int checkRange(int x, int y, int sand) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            // ���� ���̸� �𷡷� return
            return sand;
        }
        else {
            // ���� ���� ������ �𷡷� ����
            map[x][y] += sand;
            return 0;
        }
    }
}
