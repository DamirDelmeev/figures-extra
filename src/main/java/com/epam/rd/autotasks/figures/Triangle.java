package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    Point a;
    Point b;
    Point c;
    Line firstLine;
    Line secondLine;
    Line thirdLine;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException();
        }
        firstLine = new Line(a, b);
        secondLine = new Line(b, c);
        thirdLine = new Line(c, a);
        double areaTriangle = area();
        if (areaTriangle < 0) {
            throw new IllegalArgumentException();
        }
        double Tol2 = 1e-20;
        if (Math.pow((c.getX() - a.getX()) / (b.getX() - a.getX())
                - (c.getY() - a.getY()) / (b.getY() - a.getY()), 2) <= Tol2) {
            throw new IllegalArgumentException();
        }
        if (firstLine.length() + secondLine.length() == thirdLine.length()
                || firstLine.length() + thirdLine.length() == secondLine.length()
                || secondLine.length() + thirdLine.length() == firstLine.length()) {
            throw new IllegalArgumentException();
        }
    }

    public double area() {
        double semiPerimeter = (firstLine.length() + secondLine.length() + thirdLine.length()) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - firstLine.length()) * (semiPerimeter - secondLine.length())
                * (semiPerimeter - thirdLine.length()));
    }

    @Override
    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;
        return new Point(x, y);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return figure instanceof Triangle && (this.a.equals(((Triangle) figure).a))
                & (this.b.equals(((Triangle) figure).b))
                & (this.c.equals(((Triangle) figure).c));
    }
}
