package work07;

abstract class Printer{
    private String printerType;
    Printer(String printerType){
        this.printerType=printerType;
    }
    abstract void print(String txt);
    void showMyType(){
        System.out.println("My Type is:"+printerType);
    }

    public Printer() {
    }
}
