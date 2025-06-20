package work8;

//Program.java
import java.time.LocalDateTime;
public class Program implements Comparable<Program> {
    LocalDateTime time=null;
    String name;
    Program(String name,LocalDateTime time) {
        this.name = name;
        this.time = time;
    }
    public int compareTo(Program b) { // 确定 Program 对象之间的大小关系
        return time.compareTo(b.getLocalDateTime());
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getLocalDateTime() {
        return time;
    }
}
