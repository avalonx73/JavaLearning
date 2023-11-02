package classes;

abstract public class ColoredPoint extends Point implements Colorable {
    // Compile error
    public abstract void setColor(int color);
}
