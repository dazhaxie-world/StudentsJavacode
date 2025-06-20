package work08;

public class Building {
    private Window[] window={
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
    public void useWindow(Window[] w){
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < window.length; j++) {
                if(window[j].getHeight()==w[i].getHeight()&&w[i].getWidth()==window[j].getWidth()){
                    System.out.println("使用了"+window[j].getMental()+"质"+"高"+window[j].getHeight()+"宽"+window[j].getWidth());
                }
            }
        }
    }
}
