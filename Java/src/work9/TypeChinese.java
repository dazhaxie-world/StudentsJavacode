package work9;

public class TypeChinese {
    public static void main(String args[]) {
        System.out.println("输入汉字练习(输入#结束程序)");
        System.out.printf("输入显示的汉字(回车)\n");
        Chinese hanzi;
        hanzi = new Chinese();
        GiveChineseThread giveHanzi;
        InputChineseThread typeHanzi;
        giveHanzi=new GiveChineseThread();
// 【代码 1】创建线程 giveHanzi
        giveHanzi.setChinese(hanzi);
        giveHanzi.setSleepLength(6000);
        typeHanzi=new InputChineseThread();
// 【代码 2】创建线程 typeHanzi
        typeHanzi.setChinese(hanzi);
        giveHanzi.start();
        try{
            Thread.sleep(200);
        }
        catch(Exception exp){}
        typeHanzi.start();
    }
}
