public class InteractionArea implements Rectangle {
    String function;
    double x,y,height,width;
    InteractionArea(double x, double y, double width, double height, String function){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.function = function;
    }

    @Override
    public double getX() {
        return x;
    }
    @Override
    public double getY() {
        return y;
    }
    @Override
    public double getWidth() {
        return width;
    }
    @Override
    public double getHeight() {
        return height;
    }
    public String getFunction(){
        return function;
    }
}
