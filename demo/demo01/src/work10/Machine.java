package work10;

public class Machine{
    public void checkBag(Goods goods) throws DangerException{
        if(goods.isDanger()){
            DangerException danger = new DangerException();
            throw danger;
            }
        }
    }
