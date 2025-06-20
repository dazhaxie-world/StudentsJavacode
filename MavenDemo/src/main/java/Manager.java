import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    String name;
    String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Manager(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public Manager() {

    }
}
