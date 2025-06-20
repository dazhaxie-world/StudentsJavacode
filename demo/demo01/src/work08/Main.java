package work08;

public class Main {
    public static void main(String[] args) {
        Window[] myWindow={
                new Window(12.5,7.6) {
                    @Override
                    public String getMental() {
                        return "木头";
                    }
                },
                new Window(9.9,8.6) {
                    @Override
                    public String getMental() {
                        return "铁";
                    }
                }
        };
        Building building = new Building();
        building.useWindow(myWindow);
    }
}
