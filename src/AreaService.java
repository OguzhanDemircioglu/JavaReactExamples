import helper.Circle;
import helper.Rectang;
import helper.Shape;

import java.util.ArrayList;
import java.util.List;

public class AreaService {

    public static void main(String[] args) throws Exception {

        List<Object> shapes = new ArrayList<>();
        shapes.add(new Rectang(4, 5));
        shapes.add(new Circle(3));
        shapes.add(new Person());

        double sum = 0;
        for (Object s : shapes) {
            if (s instanceof Shape) {
                sum += ((Shape) s).area();
            } else {
                throw new RuntimeException("Şekil Tanımsız");
            }
        }
        System.out.println(sum);

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

