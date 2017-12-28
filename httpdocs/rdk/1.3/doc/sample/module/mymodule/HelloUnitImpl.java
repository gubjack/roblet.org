
package mymodule;

import  genRob.genControl.modules.Logger;
import  myunit.HelloUnit;

/**
 * Implementiert die Einheiten-Definition.
 * Diese Klasse muß <TT>public</TT> sein.
 */
public class  HelloUnitImpl
    implements HelloUnit
{

    /**
     * Erstellt eine Instanz.
     * @param   rLogger    Instanz zum Loggen
     */
    HelloUnitImpl (Logger rLogger)
    {
        // Zum Loggen
        m_rLogger = rLogger;
    }
    /**
     * Instanz zum Loggen.
     */
    private Logger  m_rLogger;


    /**
     * Gibt "Hello World!" zurück.
     *
     * @return  "Hello World!"
     */
    // implementiert HelloUnit
    public String  getHelloWorld ()
    {
        // Der Aufruf kommt aus einem Roblet®.

        // Damit wir etwas auf der Konsole sehen ...
        m_rLogger. log (null, this, "getHelloWorld()");

        // In unserem Fall:
        return System. getProperty ("mymodule.hello");
    }

}
