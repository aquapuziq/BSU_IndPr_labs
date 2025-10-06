import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlane {

    @Test
    void testIntersectionExists() {
        List<Segment> segments = List.of(
                new Segment(0, 0, 5, 5),
                new Segment(0, 5, 5, 0)
        );

        Point p = Plane.findMinIntersection(segments);
        assertNotNull(p);
        assertEquals(2.5, p.x, 1e-6);
        assertEquals(2.5, p.y, 1e-6);
    }

    @Test
    void testNoIntersection() {
        List<Segment> segments = List.of(
                new Segment(0, 0, 1, 1),
                new Segment(2, 2, 3, 3)
        );
        Point p = Plane.findMinIntersection(segments);
        assertNull(p);
    }

    @Test
    void testMultipleIntersections() {
        List<Segment> segments = List.of(
                new Segment(0, 0, 5, 5),
                new Segment(0, 5, 5, 0),
                new Segment(1, 5, 4, 0)
        );

        Point p = Plane.findMinIntersection(segments);
        assertNotNull(p);
        assertEquals(2.5, p.x, 1e-6);
    }
}
