
package mymodule;

import  genRob.genControl.modules.Log;
import  genRob.genControl.modules.Use;
import  myunit.HelloUnit;
import  java.security.AccessController;
import  java.security.PrivilegedAction;

/**
 * Implementiert die Einheiten-Definition.
 */
class  HelloUnitImpl
    implements HelloUnit
{

    /**
     * Erstellt eine Instanz (es wird eine pro Fach benötigt).
     * @param   rUse    Nutzungszähler des Fachs
     *                  (benötigt für die implementierte Methode)
     * @param   rLog    Instanz zum Loggen
     */
    HelloUnitImpl (Use rUse, Log rLog)
    {
        // Wir merken uns den Nutzungszähler, da wir ihn für die Methode
        // brauchen
        m_rUse = rUse;
        // Zum Loggen
        m_rLog = rLog;
    }
    /**
     * Der Nutzungszähler, mit dem unser Modul steuert, wann Threads in die
     * Einheit eintreten dürfen - und wann nicht.
     */
    private Use  m_rUse;
    /**
     * Instanz zum Loggen.
     */
    private Log  m_rLog;

    /**
     * Unsere Modul-Implementierung ruft diese Routine auf, wenn diese
     * Einheit zurückgesetzt werden soll, d.h. das Roblet® endete.
     */
    void  reset ()
    {
        // Damit wir etwas auf der Konsole sehen ...
        m_rLog. out (null, getClass (), "reset()");

        // In unserem Fall:
        // Unsere Einheiten-Implementierung benötigt kein Rücksetzen.
    }


    /**
     * Gibt "Hello World!" zurück.
     *
     * @return  "Hello World!"
     */
    // implementiert HelloUnit
    public String  getHelloWorld ()
    {
        // Der Nutzungszähler testet, ob der Thread des Roblets® hier durch
        // darf.  Wenn nicht, wird automatisch eine Ausnahme erzeugt.
        // Ansonsten wird der Nutzungszähler erhöht.
        m_rUse. raise ();

        try
        {
            // Hier implementieren wird, was die Methode machen soll.
            // Der Aufruf kann nur aus einem Roblet® kommen.

            // Damit wir etwas auf der Konsole sehen ...
            m_rLog. out (null, getClass (), "getHelloWorld()");

            // Der folgende Ressource-Zugriff muß nun privilegiert werden,
            // da die Methode vom Thread eines Roblets® aus gerufen wird.
            return (String) AccessController. doPrivileged
            (
                new PrivilegedAction ()
                {
                    public Object  run ()
                    {
                        // In unserem Fall:
                        return System. getProperty ("mymodule.hello");
                    }
                }
            );
        }
        // Das folgende wird nun in jedem Falle (also auch bei einer Ausnahme
        // in unserer Implementation) ausgeführt.
        finally
        {
            // Der Nutzungszähler wird hier erniedrigt.
            m_rUse. lower ();
        }
    }

}
