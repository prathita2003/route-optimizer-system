package com.routeoptimizer.model;

public class Edge 
{
    private String dst;
    private int wt;
    
    public Edge(String dst, int wt)
    {
        this.dst=dst;
        this.wt=wt;
    }

    public String getDestination() {
        return dst;
    }

    public int getWeight() {
        return wt;
    }
    @Override
    public String toString()
    {
        return dst+"("+wt+")";
    }
    
}
