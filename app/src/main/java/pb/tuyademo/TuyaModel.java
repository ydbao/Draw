package pb.tuyademo;

public class TuyaModel {

    private boolean change;
    private int color;
    private float width;

    public TuyaModel() {

    }

    public TuyaModel(int color, float width) {
        this.color = color;
        this.width = width;
    }

    public TuyaModel(int color, float width, boolean change) {
        this.color = color;
        this.width = width;
        this.change = change;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public int getColor() {
        return color;
    }

    public float getWidth() {
        return width;
    }

    public boolean isChange() {
        return change;
    }
}
