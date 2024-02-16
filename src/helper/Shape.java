package helper;

public sealed abstract class Shape permits Circle, Rectang {

    public abstract double area();
}
