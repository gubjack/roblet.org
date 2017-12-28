
import  genRob.genControl.client.Client;
import  java.io.Serializable;
import  myunit.HelloUnit;
import  org.roblet.Roblet;
import  org.roblet.Robot;

public class  HelloUnitTest
{

    public static void  main (String[] args)
        throws Exception
    {
        System.out.println (
            new Client (). getServer ("localhost").
                                getSlot (). run (new RobletImpl ()));
    }

    private static class  RobletImpl
        implements Roblet, Serializable
    {
        public Object  execute (Robot rRobot)
        {
            HelloUnit  rHelloUnit
                            = (HelloUnit) rRobot. getUnit (HelloUnit.class);
            return rHelloUnit. getHelloWorld ();
        }
    }

}
