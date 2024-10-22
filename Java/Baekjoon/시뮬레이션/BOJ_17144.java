import java.io.*;
import java.util.*;

public class BOJ_17144 {

    static int R, C, T;
    static int[][] map;
    static int airPurifier1, airPurifier2;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // ����û������ ��ġ ����(airPurifier1, airPurifier2) �ʱ�ȭ
        initAirPurifierPos();

        // T�� ���� �ùķ��̼� �ݺ�
        for (int i = 0; i < T; i++) {
            spreadDust();
            OperateAirPurifier();
        }

        // �����ִ� �̼����� �� ���
        System.out.println(getRemainingDust());
    }

    // ����û������ ��ġ�� ã�� ������ �Ҵ�
    static void initAirPurifierPos() {
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                airPurifier1 = i;
                airPurifier2 = i + 1;
                return;
            }
        }
    }
    
    // �̼������� Ȯ���Ŵ
    static void spreadDust() {
        int[][] tempMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = map[i][j] / 5;   // 4�������� Ȯ��Ǵ� �̼������� ��
                    int totalSpreadAmount = 0;          // 4�������� Ȯ��Ǵ� �̼������� ���� ��

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                            continue;
                        }
                        if (map[nx][ny] == -1) {
                            continue;
                        }

                        tempMap[nx][ny] += spreadAmount;
                        totalSpreadAmount += spreadAmount;
                    }

                    tempMap[i][j] += map[i][j] - totalSpreadAmount;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            map[i] = tempMap[i].clone();
        }

        map[airPurifier1][0] = -1;
        map[airPurifier2][0] = -1;
    }
    
    // ����û���Ⱑ �۵�
    static void OperateAirPurifier() {
        // ���� ����û���� (�ݽð� ����)
        for (int i = airPurifier1 - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < airPurifier1; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[airPurifier1][i] = map[airPurifier1][i - 1];
        }

        map[airPurifier1][1] = 0;  // ����û����� �� �̼����� ����
        
        // �Ʒ��� ����û���� (�ð� ����)
        for (int i = airPurifier2 + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = R - 1; i > airPurifier2; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[airPurifier2][i] = map[airPurifier2][i - 1];
        }

        map[airPurifier2][1] = 0;  // ����û����� �� �̼����� ����
    }

    // �����ִ� �̼����� �� ���
    static int getRemainingDust() {
        int totalDustAmount = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    totalDustAmount += map[i][j];
                }
            }
        }

        return totalDustAmount;
    }
}
