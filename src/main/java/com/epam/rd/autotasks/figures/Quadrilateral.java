package com.epam.rd.autotasks.figures;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

class Quadrilateral extends Figure {
    Point a;
    Point b;
    Point c;
    Point d;
    Line firstLine;
    Line secondLine;
    Line thirdLine;
    Line fourthLine;
    Line diagonal;
    List<Point> list = new ArrayList<>();

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException();
        }
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        firstLine = new Line(a, b);
        secondLine = new Line(b, c);
        thirdLine = new Line(c, d);
        fourthLine = new Line(d, a);
        diagonal = new Line(a, c);
        if (area() < 0) {
            throw new IllegalArgumentException();
        }

        Line2D line1 = new Line2D.Float((float) a.getX(), (float) a.getY(), (float) b.getX(), (float) b.getY());
        Line2D line2 = new Line2D.Float((float) b.getX(), (float) b.getY(), (float) c.getX(), (float) c.getY());
        Line2D line3 = new Line2D.Float((float) c.getX(), (float) c.getY(), (float) d.getX(), (float) d.getY());
        Line2D line4 = new Line2D.Float((float) d.getX(), (float) d.getY(), (float) a.getX(), (float) a.getY());
        Line2D diagonale1 = new Line2D.Float((float) a.getX(), (float) a.getY(), (float) c.getX(), (float) c.getY());
        Line2D diagonale2 = new Line2D.Float((float) b.getX(), (float) b.getY(), (float) d.getX(), (float) d.getY());
        boolean result = diagonale1.intersectsLine(diagonale2);
        if (!result) {
            throw new IllegalArgumentException();
        }

        double check1 = angleBetweenLines(line1, line2);
        double check2 = angleBetweenLines(line2, line3);
        double check3 = angleBetweenLines(line3, line4);
        double check4 = angleBetweenLines(line4, line1);
        if (check1 == 0 || check2 == 0 || check3 == 0 || check4 == 0) {
            throw new IllegalArgumentException();
        }
    }

    public static double angleBetweenLines(Line2D line1, Line2D line2) {
        double angle1 = Math.atan2(line1.getY1() - line1.getY2(),
                line1.getX1() - line1.getX2());
        double angle2 = Math.atan2(line2.getY1() - line2.getY2(),
                line2.getX1() - line2.getX2());
        return angle1 - angle2;
    }

    @Override
    public double area() {
        double semiPerimeter1 = (firstLine.length() + secondLine.length() + diagonal.length()) / 2;
        double semiPerimeter2 = (thirdLine.length() + fourthLine.length() + diagonal.length()) / 2;
        return Math.sqrt(semiPerimeter1 * (semiPerimeter1 - firstLine.length())
                * (semiPerimeter1 - secondLine.length()) * (semiPerimeter1 - diagonal.length()))
                + Math.sqrt(semiPerimeter2 * (semiPerimeter2 - thirdLine.length())
                * (semiPerimeter2 - fourthLine.length()) * (semiPerimeter2 - diagonal.length()));
    }

    @Override
    public Point centroid() {
        Triangle triangleFirst = new Triangle(a, b, c);
        Triangle triangleSecond = new Triangle(c, d, a);
        Triangle triangleThird = new Triangle(b, c, d);
        Triangle triangleFourth = new Triangle(d, a, b);
        Line lineFirst = new Line(triangleFirst.centroid(), triangleSecond.centroid());
        Line lineSecond = new Line(triangleThird.centroid(), triangleFourth.centroid());
        return lineFirst.intersection(lineSecond);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure instanceof Quadrilateral && containsAll(list, ((Quadrilateral) figure).list)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsAll(List<Point> firstList, List<Point> secondList) {
        int counter = 0;
        for (int i = 0; i < firstList.size(); i++) {
            for (int j = 0; j < firstList.size(); j++) {
                if (firstList.get(i).compareTo(secondList.get(j))) {
                    counter++;
                }
            }
        }
        return counter == 4;
    }
}

