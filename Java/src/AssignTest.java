public class AssignTest {
    public static void main(String[] args) {
//1.Declare and Assign
        int a;
        a=100;
        System.out.println("a="+a);
        int b=100;
        System.out.println("b="+b);
        int c=a;
        System.out.println("c="+c);
//2.boolean
        boolean flag=true;
//boolean flag1=1;
        System.out.println("flag="+flag);
//3.text type
// char cA='A';
//char cA1="A";
        char cA2=65;
        System.out.println("cA="+cA2);
        String s1="Hello world";
        String s2="Hello \n world";
        System.out.println("s1="+s1);
        System.out.println("s2="+s2);
//4.integer type
        byte iA=100;
//byte iB=200;
        short iC=1000;
        int iD=10000;
        long iE=1000;
//int iF=10000L;
        System.out.println("iA="+iA+"\n"
                +"iC="+iC+"\n"
                +"iD="+iD+"\n"
                +"iE="+iE+"\n");
//5.floating-point type
        float fA=3.14F;
//float fB=3.14;
        double fC=3.14;
        double fD=3.14D;
        double fE=2E2;
        double fF=123.4E+306D;
        System.out.println("fA="+fA+"\n"
                +"fC="+fC+"\n"
                +"fD="+fD+"\n"
                +"fE="+fE+"\n"
                +"fF="+fF+"\n");
    }
}
