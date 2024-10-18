import helper.*;

import java.util.ArrayList;
import java.util.List;

public class AreaService {

    public static void main(String[] args) throws Exception {

        List<Object> shapes = new ArrayList<>();
        shapes.add(new Rectang(4, 5));
        shapes.add(new Circle(3));
//        shapes.add(new Person());

        double sum = 0;
        for (Object s : shapes) {
            if (s instanceof Shape) {
                sum += ((Shape) s).area();
            } else {
                throw new RuntimeException("Şekil Tanımsız");
            }
        }
        System.out.println(
                "areas by shapes: " + sum);

        System.out.println(
                "areas by Manuel: " + calculateAreaManuel(shapes)
        );

        List<AreaCalculator> areas = new ArrayList<>();
        areas.add(new Regrangular(4, 5));
        areas.add(new Circular(3));

        System.out.println(
                "new Area Calculation: " + calculatorArea(areas)
        );

    }

    public static double calculatorArea(List<AreaCalculator> shapes) {
        double area = 0;
        for (AreaCalculator shape : shapes) {
            area += shape.calculateArea();
        }
        return area;
    }

    public static double calculateAreaManuel(List<Object> shapes) {

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

