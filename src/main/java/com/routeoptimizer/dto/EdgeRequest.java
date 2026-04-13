package com.routeoptimizer.dto;

public class EdgeRequest {
    private String src;
    private String dst;
    private int wt;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public int getWt() {
        return wt;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }
    
    

}
