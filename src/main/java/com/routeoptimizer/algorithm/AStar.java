package com.routeoptimizer.algorithm;

import com.routeoptimizer.model.*;
import java.util.*;

public class AStar {

    public static Map<String, Object> findPath(Graph graph, String src, String dst) {

        Map<String, Integer> gScore = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String node : graph.getNodes()) {
            gScore.put(node, Integer.MAX_VALUE);
        }

        gScore.put(src, 0);

        PriorityQueue<String> pq = new PriorityQueue<>(
                Comparator.comparingInt(n ->
                        gScore.get(n) + heuristic(graph.getNode(n), graph.getNode(dst))
                )
        );

        pq.add(src);

        while (!pq.isEmpty()) {

            String current = pq.poll();

            if (visited.contains(current)) continue;
            visited.add(current);

            System.out.println("A* visiting: " + current);

            if (current.equals(dst)) break;

            for (Edge edge : graph.getNeighbors(current)) {

                String neighbor = edge.getDestination();
                int tentative = gScore.get(current) + edge.getWeight();

                if (tentative < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentative);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        if (gScore.get(dst) == Integer.MAX_VALUE) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "No path found between given nodes");
            return error;
        }

        // 🔁 Path reconstruction
        List<String> path = new ArrayList<>();
        String step = dst;

        while (step != null) {
            path.add(step);
            step = previous.get(step);
        }

        Collections.reverse(path);

        Map<String, Object> result = new HashMap<>();
        result.put("path", path);
        result.put("distance", gScore.get(dst));

        return result;
    }

    private static int heuristic(Node a, Node b) {

        // Safety check (important if node missing)
        if (a == null || b == null) return 0;

        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();

        return (int) Math.sqrt(dx * dx + dy * dy);
    }
}