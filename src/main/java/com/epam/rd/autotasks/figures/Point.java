package com.epam.rd.autotasks.figures;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean compareTo(Point o) {

        return (Math.round(this.x) == Math.round(o.getX())) & (Math.round(this.y) == Math.round(o.getY()));

    }
}
