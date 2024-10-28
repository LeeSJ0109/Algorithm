import java.io.*;
import java.util.*;

public class BOJ_2887 {

    static class Edge implements Comparable<Edge> {
        int w;     // ������ �༺
        int cost;  // �ͳ� ���� ���

        public Edge(int w, int cost) {
            this.w = w;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);	// ��뿡 ���� ����
        }
    }
    
    static int N;
    static List<Edge>[] graph;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];
        int[] z = new int[N];

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            z[i] = Integer.parseInt(st.nextToken());
        }
        
        Integer[] idx = new Integer[N];
        for (int i = 0; i < N; i++) {
            idx[i] = i;
        }

        // x ��ǥ�� ���� �ε��� ����, ���� ���� ���� �߰�
        Arrays.sort(idx, Comparator.comparingInt(i -> x[i]));
        for (int i = 0; i < N - 1; i++) {
            int u = idx[i]; 	// ���� ����
            int v = idx[i + 1]; // ���� ����
            int cost = Math.abs(x[u] - x[v]);
            graph[u].add(new Edge(v, cost)); // ����� ���� �߰�
            graph[v].add(new Edge(u, cost));
        }

        // y ��ǥ�� ���� ���� �߰�
        Arrays.sort(idx, Comparator.comparingInt(i -> y[i]));
        for (int i = 0; i < N - 1; i++) {
            int u = idx[i];
            int v = idx[i + 1];
            int cost = Math.abs(y[u] - y[v]);
            graph[u].add(new Edge(v, cost));
            graph[v].add(new Edge(u, cost));
        }

        // z ��ǥ�� ���� ���� �߰�
        Arrays.sort(idx, Comparator.comparingInt(i -> z[i]));
        for (int i = 0; i < N - 1; i++) {
            int u = idx[i];
            int v = idx[i + 1];
            int cost = Math.abs(z[u] - z[v]);
            graph[u].add(new Edge(v, cost));
            graph[v].add(new Edge(u, cost));
        }

        int minCost = Prim();
        System.out.println(minCost);
    }

    static int Prim() {
        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(0, 0));

        int totalCost = 0;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int v = edge.w;
            int cost = edge.cost;

            if (visited[v]) {
                continue;
            }

            visited[v] = true;
            totalCost += cost;

            for (Edge e : graph[v]) {
                if (!visited[e.w]) {
                    queue.add(e);
                }
            }
        }

        return totalCost;
    }
}
