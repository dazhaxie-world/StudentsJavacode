package work12;

public class FindMess {
    public static void main(String args[]) {
        String mess = "书名:Java 程序设计,出版时间:2011.10.01,"+
                "出版社:清华大学出版社,价格:29.8 元,页数:389 页";
        if(mess!=null) { //判断 mess 中是否含有"程序"
            System.out.println("图书信息包含有\"程序\"");
        }
        int index=mess.indexOf(":",mess.indexOf(":")+1);//mess 调用 indexOf(String s,int start)返回 mess 中第 2 个冒号的位置
        String date = mess.substring(index+1,index+11);
        System.out.println(date);
        int pricePosition=mess.indexOf("价格");//mess 调用 indexOf(String s)返回首次出现"价格"的位置
        int endPosition=mess.indexOf("元");
        String priceMess = mess.substring(pricePosition+3,endPosition);
        System.out.println("图书价格:"+priceMess);
        double price=Double.parseDouble(priceMess);
        if(price>=29) {
            System.out.println("图书价格"+price+"大于或等于 29 元");
        }
        else {
            System.out.println("图书价格"+price+"小于 29 元");
        }
        index =mess.lastIndexOf(":");//mess 调用 laseIndexOf(String s,int start)返回最后一个冒号位置
        endPosition=mess.lastIndexOf("页");
        String pageMess = mess.substring(index+1,endPosition-1);
        int p = Integer.parseInt(pageMess);
        if(p>=360) {
            System.out.println("图书的页数"+p+"大于或等于 360");
        }
        else {
            System.out.println("图书的页数"+p+"小于 360");
        }
    }
}
