package work6;

import java.util.*;
public class FindStudent {
    public static void main(String args[ ]) {
        List<Student> list = new LinkedList<Student>();
        list.add(new Student("张三",188));
        list.add(new Student("李四",178));
        list.add(new Student("周五",198));
        Iterator<Student> iter=list.iterator();
        System.out.println("排序前,链表中的数据");
        while(iter.hasNext()){
            Student stu=iter.next();
            System.out.println(stu.name+ "身高:"+stu.height);
        }
        Collections.sort(list);
        System.out.println("排序后,链表中的数据");
        iter=list.iterator();
        while(iter.hasNext()){
            Student stu=iter.next();
            System.out.println(stu.name+ "身高:"+stu.height);
        }
        Student zhaoLin = new Student("zhao xiao lin",178);
        int index = Collections.binarySearch(list,zhaoLin,null);
        if(index>=0) {
            System.out.println(zhaoLin.name+"和链表中"+list.get(index).name+"身高相同");
        }
    }
}
