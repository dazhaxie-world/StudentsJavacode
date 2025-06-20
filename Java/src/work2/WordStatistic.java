package work2;

import java.io.*;
import java.util.*;
public class WordStatistic {
    Vector<String> allWord,noSameWord;
    File file = new File("english.txt");
    Scanner sc = null;
    String regex;
    WordStatistic() {
        allWord = new Vector<String>();
        noSameWord = new Vector<String>();
        //regex 是由空格、数字和符号(!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~)组成的正则表达式
        regex= "[\\s\\d\\p{Punct}]+";
        try{ sc = new Scanner(file);
//        【代码 1】 //创建指向 file 的 sc
            sc.useDelimiter(regex);
// 【代码 2】//sc 调用 useDelimiter(String regex)方法,向参数传递 regex
        }
        catch(IOException exp) {
            System.out.println(exp.toString());
        }
    }
    void setFileName(String name) {
        file = new File(name);
        try{ sc = new Scanner(file);
            sc.useDelimiter(regex);
        }
        catch(IOException exp) {
            System.out.println(exp.toString());
        }
    }
    public void wordStatistic() {
        try{ while(sc.hasNext()){
            String word = sc.next();
            allWord.add(word);
            if(!noSameWord.contains(word))
                noSameWord.add(word);
        }
        }
        catch(Exception e){}
    }
    public Vector<String> getAllWord() {
        return allWord;
    }
    public Vector<String> getNoSameWord() {
        return noSameWord;
    }
}
