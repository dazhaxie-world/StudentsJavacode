package work05;

public class WeatherForecast { //主类
    public static void main(String args[]) {
        Weather weatherBeijing =new Weather();
        System.out.print("\n 今天白天:");
        weatherBeijing.setState(new CloudyDayState());
        weatherBeijing.show();
        System.out.print("\n 今天夜间:");
        weatherBeijing.setState(new LightRainState());
        weatherBeijing.show();
        System.out.print("转:");
        weatherBeijing.setState(new HeavyRainState());
        weatherBeijing.show();
        System.out.print("\n 明天白天:");
        weatherBeijing.setState(new LightRainState());
        weatherBeijing.show();
        System.out.print("\n 明天夜间:");
        weatherBeijing.setState(new CloudyLittleState());
        weatherBeijing.show();
    }
}
