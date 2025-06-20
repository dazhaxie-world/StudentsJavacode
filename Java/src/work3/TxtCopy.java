package work3;

import java.io.*;

public class TxtCopy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("FileInputTxt.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("FileOutputTxt.txt"));
        String str;
        while ((str=reader.readLine())!=null){
            writer.write(str+"\n");
        }
        reader.close();
        writer.close();
    }
}
