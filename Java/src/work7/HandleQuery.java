package work7;

import java.awt.event.*;
import java.util.*;
import java.io.File;
public class HandleQuery implements ActionListener {
    WindowPhone view;
    HashMap<String,String> hashtable;//
    File file=new File("phone.txt");
    Scanner sc=null;
    HandleQuery() {
        hashtable=new HashMap<String,String>();
        try{ sc=new Scanner(file);
            sc.useDelimiter("[#]+");
            while(sc.hasNext()){
                String name=sc.next();
                String phoneMess=sc.next();
                hashtable.put(name,phoneMess);//
            }
        }
        catch(Exception e){}
    }
    public void setView(WindowPhone view) {
        this.view=view;
    }
    public void actionPerformed(ActionEvent e) {
        String name=view.inputText.getText().trim();
        Set<String> keySet = hashtable.keySet(); //得到 hashtable 中的全部 Key
        Iterator<String> iter = keySet.iterator();
        while(iter.hasNext()) {
            String nameKey = iter.next();
            if(nameKey.contains(name)){
                String phoneMess=hashtable.get(nameKey);
                view.showText.append(nameKey+":\n"+phoneMess+"\n");
            }
        }
    }
}