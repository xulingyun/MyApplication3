package wc.xulingyun.com.xly.myapplication.dao;

/**
 * Created by 徐玲郓 on 2016/12/28.
 * 描述：
 */

public class ImageDao {

    String name;
    int width;
    int height;
    String path;

    public ImageDao(String $Name, String $Path, int $Width, int $Height) {
        height = $Height;
        name = $Name;
        path = $Path;
        width = $Width;
    }

    public ImageDao(String $Name, String $Path) {
        name = $Name;
        path = $Path;
    }

    @Override
    public String toString() {
        return "ImageDao{" +
                "height=" + height +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", path='" + path + '\'' +
                '}';
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int $Height) {
        height = $Height;
    }

    public String getName() {
        return name;
    }

    public void setName(String $Name) {
        name = $Name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String $Path) {
        path = $Path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int $Width) {
        width = $Width;
    }
}
