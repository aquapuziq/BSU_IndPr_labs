import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

class Segment {
    double x1, y1, x2, y2;

    public Segment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}

public class Plane {
    public static void initialization(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ввелите количество отрезков: ");
        int N = scanner.nextInt();
        List<Segment> segments = new ArrayList<>();
        System.out.println("Введите координаты отрезков " +
                "(x1,y1 - начало отрезка; x2,y2 - конец отрезка");

        for (int i = 0; i < N; i++){
            System.out.print("Отрезок " + (i + 1) + ": ");
            double x1 = scanner.nextInt();
            double y1 = scanner.nextInt();
            double x2 = scanner.nextInt();
            double y2 = scanner.nextInt();
            segments.add(new Segment(x1, y1, x2, y2));
        }
        Point minIntersection = findMinIntersection(segments);
        if (minIntersection != null)
            System.out.println("Минимальная точка пересечения: " + minIntersection);
        else
            System.out.println("Пересечений нет.");
    }

    public static Point findMinIntersection(List<Segment> segments){
        TreeMap<Double, Point> intersections = new TreeMap<>();
        for (int i = 0; i < segments.size(); i++) {
            for (int j = i + 1; j < segments.size(); j++) {
                Point p = getIntersection(segments.get(i), segments.get(j));
                if (p != null) {
                    intersections.put(p.x, p);
                }
            }
        }

        if (intersections.isEmpty()) return null;
        return intersections.firstEntry().getValue();
    }

    private static Point getIntersection(Segment s1, Segment s2) {
        double x1 = s1.x1, y1 = s1.y1, x2 = s1.x2, y2 = s1.y2;
        double x3 = s2.x1, y3 = s2.y1, x4 = s2.x2, y4 = s2.y2;

        double denom = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4);
        if (denom == 0)
            return null;

        double px = ((x1*y2 - y1*x2)*(x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4)) / denom;
        double py = ((x1*y2 - y1*x2)*(y3 - y4) - (y1 - y2)*(x3*y4 - y3*x4)) / denom;

        if (isBetween(px, py, s1) && isBetween(px, py, s2)) {
            return new Point(px, py);
        }
        return null;
    }

    private static boolean isBetween(double px, double py, Segment s) {
        return (px >= Math.min(s.x1, s.x2) && px <= Math.max(s.x1, s.x2)
                && py >= Math.min(s.y1, s.y2) && py <= Math.max(s.y1, s.y2));
    }
}
