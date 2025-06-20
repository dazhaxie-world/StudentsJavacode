package work1;

import java.io.*;
import java.util.*;
public class AnalysisResult {
    public static void main(String args[]) {
        File fRead = new File("score.txt");
        File fWrite = new File("socreAnalysis.txt");
        try{ Writer out =new OutputStreamWriter(new FileOutputStream(fWrite));
//            【代码 1】//以尾加方式创建指向文件 fWrite 的 out 流
            BufferedWriter bufferWrite = new BufferedWriter(out);
//            【代码 2】//创建指向 out 的 bufferWrite 流
            Reader in = new FileReader(fRead);
//            【代码 3】//创建指向文件 fRead 的 in 流
            BufferedReader bufferRead =new BufferedReader(in);
//            【代码 4】//创建指向 in 的 bufferRead 流
            String str = null;
            while((str=bufferRead.readLine())!=null) {
                double totalScore=Fenxi.getTotalScore(str);
                str = str+"总成绩:"+totalScore;
                System.out.println(str);
                bufferWrite.write(str);
                bufferWrite.newLine();
            }
            bufferRead.close();
            bufferWrite.close();
        }
        catch(IOException e) {
            System.out.println(e.toString());
        }
    }
}
