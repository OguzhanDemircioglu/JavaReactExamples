package helper;

public class Regrangular implements AreaCalculator {
    private double length;
    private double width;

    public Regrangular(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}
