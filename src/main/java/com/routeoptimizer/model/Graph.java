package com.routeoptimizer.model;

import java.util.*;

public class Graph {

    private Map<String, List<Edge>> adjList;
    private Map<String, Node> nodeMap;

    public Graph() {
        adjList = new HashMap<>();
        nodeMap = new HashMap<>();
    }

    public void addNode(String node, int x, int y) {

        if (nodeMap.containsKey(node)) return;

        nodeMap.put(node, new Node(node, x, y));
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String src, String dst, int wt) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dst, new ArrayList<>());

        adjList.get(src).add(new Edge(dst, wt));
        adjList.get(dst).add(new Edge(src, wt));
    }

    public List<Edge> getNeighbors(String node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Set<String> getNodes() {
        return adjList.keySet();
    }

    public Map<String, List<Edge>> getAdjList() {
        return adjList;
    }

    public Node getNode(String name) {
        return nodeMap.get(name);
    }

    public Map<String, Node> getNodeMap() {
        return nodeMap;
    }
}