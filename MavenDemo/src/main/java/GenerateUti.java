import util.SqlUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateUti implements ActionListener {
    private static int TOTAL = 100;  // 总数据量
    private static Random RANDOM = new Random();

    public static void main(String[] args) throws SQLException {
        generateData();
    }

    public static void generateData() throws SQLException {
        Set<String> idSet = new HashSet<>();  // 保证学号唯一
            for (int i = 0; i < TOTAL; i++) {
                String studentId = generateUniqueId(idSet);  // 生成唯一学号
                String name = generateRandomName();          // 生成随机姓名
                int year=generateBirthday_year();
                int month=generateBirthday_month();
                int day=generateBirthday_day(month);
                int score1 = generateNormalScore(80, 8);      // 生成正态分布成绩
                int score2 = generateNormalScore(80, 8);
                int score3 = generateNormalScore(80, 8);
                int score4 = generateNormalScore(80, 8);
                String sql="INSERT INTO students(学号,`name`,birthday_year,birthday_month,birthday_day,chinese,math,java,PE) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,studentId);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,year+"");
                preparedStatement.setString(4,month+"");
                preparedStatement.setString(5,day+"");
                preparedStatement.setString(6,score1+"");
                preparedStatement.setString(7,score2+"");
                preparedStatement.setString(8,score3+"");
                preparedStatement.setString(9,score4+"");
                preparedStatement.executeUpdate();
            }
            MainView.pubMainView.reload();
    }


    // 生成唯一学号
    private static String generateUniqueId(Set<String> idSet) {
        String id;
        do {
            id = String.format("%04d%02d%04d", RANDOM.nextInt(10)+2015,RANDOM.nextInt(100),RANDOM.nextInt(10000));
        } while (idSet.contains(id));
        idSet.add(id);
        return id;
    }

    // 生成随机姓名（姓氏+性别+名字）
    private static String generateRandomName() {
        String[] surnames = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈","秦","董","丁","田","齐"};
        String gender = RANDOM.nextBoolean() ? "男" : "女";
        String name = surnames[RANDOM.nextInt(surnames.length)];

        String[] maleNames = {"强", "伟", "杰", "涛", "磊", "超", "峰", "辉", "宇", "浩","子","国","骁","飞","宇","镇","文",""};
        String[] femaleNames = {"芳", "丽", "娟", "敏", "静", "秀", "娜", "丽", "慧", "婷","秀","华",""};

        name += gender.equals("男")
                ? maleNames[RANDOM.nextInt(maleNames.length)]
                : femaleNames[RANDOM.nextInt(femaleNames.length)];
        name += gender.equals("男")
                ? maleNames[RANDOM.nextInt(maleNames.length)]
                : femaleNames[RANDOM.nextInt(femaleNames.length)];
        return name;
    }

    // 生成正态分布成绩（均值80，标准差8，范围60-100）
    private static int generateNormalScore(double mean, double stdDev) {
        double score;
        do {
            score = mean + stdDev * RANDOM.nextGaussian();
        } while (score < 60 || score > 100);
        return (int) Math.round(score);
    }
    private static int generateBirthday_year(){
        return RANDOM.nextInt(5)+2002;
    }
    private static int generateBirthday_month(){
        return RANDOM.nextInt(12)+1;
    }
    private static int generateBirthday_day(int month){
        if (month!=2){
            if (month==1||month==3||month==5||month==7||month==8||month==10||month==12){
                return RANDOM.nextInt(31)+1;
            }
            else {
                return RANDOM.nextInt(30)+1;
            }
        }
        else {
            return RANDOM.nextInt(28)+1;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            generateData();
            System.out.println("123");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
