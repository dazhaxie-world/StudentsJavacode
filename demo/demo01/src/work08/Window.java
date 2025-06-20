package work08;

public abstract class Window {
    private double height;
    private double width;
    private String mental;

    public abstract String getMental();
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Window(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public Window() {
    }
}
