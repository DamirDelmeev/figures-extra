package com.epam.rd.autotasks.figures;

import java.math.BigDecimal;


class Circle extends Figure {
    private final static double EPSILON = 7.105427357601002E-15;
    Point center;
    Number doubleRadius;

    public Circle(Point center, Number radius) {
        this.center = center;
        this.doubleRadius = radius;
        if (center == null || (doubleRadius.doubleValue() <= 0)) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean equals(double a, double b) {
        return a == b ? true : Math.abs(a - b) <= EPSILON;
    }

    @Override
    public Point centroid() {
        return this.center;
    }

    @Override
    public boolean isTheSame(Figure figure) {

        Double truncatedDoubleFirst = BigDecimal.valueOf(area())
                .doubleValue();
        Double truncatedDoubleSecond = BigDecimal.valueOf(figure.area())
                .doubleValue();
        if (figure instanceof Circle && (equals(truncatedDoubleFirst, truncatedDoubleSecond) && equals(center.getX(),
                ((Circle) figure).center.getX()) && equals(center.getY(), ((Circle) figure).center.getY()))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(doubleRadius.doubleValue(), 2);
    }

}
