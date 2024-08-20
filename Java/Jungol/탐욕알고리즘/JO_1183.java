package Java.Jungol.Ž��˰���;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1183 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// ������ ���� W
		int W = Integer.parseInt(br.readLine());
		
		// ������ ��ġ
		int[] coinValues = {500, 100, 50, 10, 5, 1};
		
		// ������ ����
		int[] coinCounts = new int[6];
		
		// ��ü ���� ����, ���� �� �ִ� �ִ� ��ġ
        int totalCoins = 0;
        int maxValue = 0;
        
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 6; i++) {
			coinCounts[i] = Integer.parseInt(st.nextToken());
			totalCoins += coinCounts[i];
			maxValue += coinValues[i] * coinCounts[i];
		}
		
		// ��ǥ ��ġ: ���� �� �ִ� �ִ� ��ġ - ������ ����
		int targetValue = maxValue - W;	// ������� �ϴ� ��ǥ ��ġ
		int usedCoins = 0;				// ���� ������ ����
		
		// ������ ��ġ�� ū �ͺ��� ���ʷ� ���
		for (int i = 0; i < 6; i++) {
			int coinValue = coinValues[i];
            int coinCount = coinCounts[i];
            
            // ���� �������� ���� �� �ִ� �ִ� ����
            int useCount = Math.min(targetValue / coinValue, coinCount);
            
            // ���� ���
            targetValue -= useCount * coinValue;
            usedCoins += useCount;
            
            // ���� ���� �ֽ�ȭ
            coinCounts[i] -= useCount;
            
            // ��ǥ ��ġ�� 0�� �Ǹ� ����
            if (targetValue == 0)
                break;
		}
		
		System.out.println(totalCoins - usedCoins);
		for (int coinCount : coinCounts) {
			System.out.print(coinCount + " ");
		}
	}

}
