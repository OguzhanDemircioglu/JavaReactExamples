import helper.Circle;
import helper.Rectang;
import helper.Shape;

import java.util.ArrayList;
import java.util.List;

public class AreaService {

    public static void main(String[] args) throws Exception {

        Shape rect = new Rectang(4, 5);
        Shape circle = new Circle(3);

        List<Object> shapes = new ArrayList<>();
        shapes.add(rect);
        shapes.add(circle);

        try {
            double sum = 0;
            for (Object s : shapes) {
                if (s instanceof Shape) {
                    sum += ((Shape) s).area();
                }
            }
            System.out.println(sum);
        } catch (Exception e) {
            throw new Exception("Şekil Tanımsız");
        }

        System.out.println(
                calculateArea(shapes)
        );
    }

    public static double calculateArea(List<Object> shapes) {

        double area = 0;
        for (Object shape : shapes) {
            if (shape instanceof Rectang) {
                Rectang rect = (Rectang) shape;
                area += (rect.getLength() * rect.getWidth());
            } else if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                area += circle.getRadius() * circle.getRadius() * Math.PI;
            } else {
                throw new RuntimeException("Shape not supported");
            }
        }
        return area;
    }
}

