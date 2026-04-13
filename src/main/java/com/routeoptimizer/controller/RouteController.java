package com.routeoptimizer.controller;

import com.routeoptimizer.model.Graph;
import com.routeoptimizer.model.Edge;
import com.routeoptimizer.algorithm.Dijkstra;
import com.routeoptimizer.algorithm.AStar;
import com.routeoptimizer.dto.EdgeRequest;
import com.routeoptimizer.dto.NodeRequest;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RouteController {

    private Graph graph = new Graph();

    public RouteController() {/*
        Example for hardcoded data
        graph.addNode("A", 0, 0);
        graph.addNode("B", 1, 2);
        graph.addNode("C", 2, 1);
        graph.addNode("D", 4, 2);
        graph.addNode("F", 6, 3);

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("C", "D", 4);
        graph.addEdge("D", "F", 6);
        graph.addEdge("B", "F", 10);*/
    }

    @GetMapping("/shortest-path")
    public Map<String, Object> getShortestPath(@RequestParam String src, @RequestParam String dst) {

        Map<String, Object> result = new HashMap<>();

        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dst)) {
            result.put("status", "error");
            result.put("message", "Invalid source or destination node");
            return result;
        }

        Map<String, Object> algoResult = Dijkstra.findShortestPath(graph, src, dst);

        if (algoResult.containsKey("error")) {
            result.put("status", "error");
            result.put("message", algoResult.get("error"));
        } else {
            result.put("status", "success");
            result.put("data", algoResult);
        }

        return result;
    }

    @GetMapping("/astar")
    public Map<String, Object> getAStar(@RequestParam String src, @RequestParam String dst) {

        Map<String, Object> result = new HashMap<>();

        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dst)) {
            result.put("status", "error");
            result.put("message", "Invalid source or destination node");
            return result;
        }

        Map<String, Object> algoResult = AStar.findPath(graph, src, dst);

        if (algoResult.containsKey("error")) {
            result.put("status", "error");
            result.put("message", algoResult.get("error"));
        } else {
            result.put("status", "success");
            result.put("data", algoResult);
        }

        return result;
    }

    @GetMapping("/graph")
    public Map<String, List<Edge>> getGraph() {
        return graph.getAdjList();
    }

    @GetMapping("/nodes")
    public Map<String, ?> getNodes() {
        return graph.getNodeMap();
    }

    @PostMapping("/add-node")
public Map<String, String> addNode(@RequestBody NodeRequest request) {

    Map<String, String> res = new HashMap<>();

    if (request.getName() == null) {
        res.put("status", "error");
        res.put("message", "Invalid node");
        return res;
    }

    graph.addNode(request.getName(), request.getX(), request.getY());

    res.put("status", "success");
    res.put("message", "Node added successfully");

    return res;
}

    @PostMapping("/add-edge")
public Map<String, String> addEdge(@RequestBody EdgeRequest request) {

    Map<String, String> res = new HashMap<>();

    if (!graph.getNodes().contains(request.getSrc()) ||
        !graph.getNodes().contains(request.getDst())) {

        res.put("status", "error");
        res.put("message", "Add nodes before adding edge");
        return res;
    }

    graph.addEdge(request.getSrc(), request.getDst(), request.getWt());

    res.put("status", "success");
    res.put("message", "Edge added successfully");

    return res;
}

    @PostMapping("/clear")
    public String clearGraph() {
        graph = new Graph();
        return "Graph cleared";
    }
    @GetMapping("/compare")
public Map<String, Object> compare(@RequestParam String src, @RequestParam String dst) {

    Map<String, Object> result = new HashMap<>();

    if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dst)) {
        result.put("status", "error");
        result.put("message", "Invalid source or destination node");
        return result;
    }

    long start1 = System.nanoTime();
    Map<String, Object> dijkstra = Dijkstra.findShortestPath(graph, src, dst);
    long end1 = System.nanoTime();

    long start2 = System.nanoTime();
    Map<String, Object> astar = AStar.findPath(graph, src, dst);
    long end2 = System.nanoTime();

    Map<String, Object> data = new HashMap<>();
    data.put("dijkstra", dijkstra);
    data.put("astar", astar);
    data.put("dijkstraTime", end1 - start1);
    data.put("astarTime", end2 - start2);

    result.put("status", "success");
    result.put("data", data);

    return result;
}
}