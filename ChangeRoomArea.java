public class ChangeRoomArea implements Rectangle{

    String floor;
    double x,y,height,width,spawnX,spawnY;
    ChangeRoomArea(double x, double y, double width, double height, String floor, double spawnX, double spawnY){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.floor = floor;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
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
    public String getNewRoom(){
        return floor;
    }
    public double getSpawnX(){
        return spawnX;
    }
    public double getSpawnY(){
        return spawnY;
    }
}
