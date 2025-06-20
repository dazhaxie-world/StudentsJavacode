package work4;

import java.io.*;

public class Produce {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("goods.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("goodsVolume.txt"));
        String str;
        String l;
        String w;
        String h;
        while ((str=reader.readLine())!=null){
            l=str.substring(str.indexOf("length")+7,str.indexOf("cm"));
            w=str.substring(str.indexOf("width")+6,str.indexOf("cm",str.indexOf("cm")+1));
            h=str.substring(str.indexOf("height")+7,str.indexOf("cm",str.indexOf("cm",str.indexOf("cm")+1)+1));
            int v = Integer.parseInt(l) * Integer.parseInt(w) * Integer.parseInt(h);
            writer.write(str+",Volume:"+ v +"\n");
        }
        reader.close();
        writer.close();
    }
}
