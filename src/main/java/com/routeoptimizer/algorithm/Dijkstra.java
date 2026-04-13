package com.routeoptimizer.algorithm;

import com.routeoptimizer.model.Edge;
import com.routeoptimizer.model.Graph;
import java.util.*;

public class Dijkstra {

    public static Map<String, Object> findShortestPath(Graph graph, String src, String dst) {

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        PriorityQueue<String> pq =
                new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize distances
        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(src, 0);
        pq.add(src);

        Set<String> visited=new HashSet<>();
        while (!pq.isEmpty()) {

            String current = pq.poll();
            System.out.println("Dijkstra visiting: " + current);
            if(visited.contains(current)) continue;
            visited.add(current);
            if (current.equals(dst)) break;

            for (Edge edge : graph.getNeighbors(current)) {

                String neighbor = edge.getDestination();
                int newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
            
        if(distances.get(dst)==Integer.MAX_VALUE)
        {
            Map<String,Object> error=new HashMap<>();
            error.put("error", "No path found between given nodes");
            return error;
        }
        List<String> path = new ArrayList<>();
        String step = dst;

        while (step != null) {
            path.add(step);
            step = previous.get(step);
        }

        Collections.reverse(path);

        Map<String, Object> result = new HashMap<>();
        result.put("path", path);
        result.put("distance", distances.get(dst));
        
        return result;
    }
}