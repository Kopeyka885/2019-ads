package ru.mail.polis.ads.part9.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task2022 {

    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] cycle;
    private static int[] used, parent;

    private static void solve(final FastScanner in, final PrintWriter out) throws Exception {
        int vertexAmount = in.nextInt();
        int edgeAmount = in.nextInt();
        graph = new ArrayList<>();

        for (int i = 0; i <= vertexAmount; i++) {
            graph.add(new ArrayList<>());
        }
        cycle = new boolean[vertexAmount + 1];
        used = new int[vertexAmount + 1];
        parent = new int[vertexAmount + 1];

        for (int i = 0; i < edgeAmount; i++) {
            int fist = in.nextInt();
            int second = in.nextInt();
            graph.get(fist).add(second);
            graph.get(second).add(fist);
        }

        for (int i = 1; i <= vertexAmount; i++) {
            if (used[i] == 0)
                dfs(i);
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= vertexAmount; i++) {
            if (cycle[i] && min > i) 
            {
                min = i;
            }
        }

        if (min != Integer.MAX_VALUE) 
        {
            System.out.println("Yes");
            System.out.println(min);
        }
        else
        {
            System.out.println("No");
        }
    }

    private static void dfs(int vertex) {
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        deq.addFirst(vertex);

        while (!deq.isEmpty()) {
            int start = deq.peek();
            used[start] = 1;
            boolean newChild = false;

            for (Integer end : graph.get(start)) {
                if (parent[start] != end && used[end] == 0)
                {
                    parent[end] = start;
                    deq.addFirst(end);
                    newChild = true;
                    break;
                }
                else if (parent[start] != end && used[end] == 1) 
                {
                    cycle[end] = true;
                    int u = start;
                    if (!cycle[start]) 
                    {
                        while (u != end) {
                            cycle[u] = true;
                            u = parent[u];
                            if (cycle[u]) 
                            {
                                break;
                            }
                        }
                    }
                }
            }
            if (!newChild) {
                used[start] = 2;
                deq.removeFirst();
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) throws Exception{
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}








