import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Segment {
    double x1, y1, x2, y2;

    public Segment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
            System.out.print("Отрезок " + i + 1 + ": ");
            double x1 = scanner.nextInt();
            double y1 = scanner.nextInt();
            double x2 = scanner.nextInt();
            double y2 = scanner.nextInt();
            segments.add(new Segment(x1, y1, x2, y2));
        }
    }
}
