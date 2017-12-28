import  book.unit.*;
import  genRob.genControl.client.*;
import  java.io.*;
import  org.roblet.*;

public class  Hello implements Roblet, Serializable {
    public static void  main (String[] args) throws Exception {
        new Client (). getServer ("roblet.org:2001"). getSlot ().
                run (new Hello ());
    }
    public Object  execute (Robot robot) {
        HelloUnit  hu = (HelloUnit) robot. getUnit (HelloUnit.class);
        hu. sayHello ();
        return null;
    }
}