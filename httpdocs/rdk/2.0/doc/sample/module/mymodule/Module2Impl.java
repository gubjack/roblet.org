
package mymodule;

import  genRob.genControl.modules.Logger;
import  genRob.genControl.modules.Module2;
import  genRob.genControl.modules.ModuleContext;
import  genRob.genControl.modules.RobletHandle;
import  myunit.HelloUnit;
import  org.roblet.Unit;


/**
 * Diese Klasse stellt ein Modul dar, da es die Modul-Schnittstelle des
 * Roblet®-Servers implementiert.
 * <P>
 * Der Name dieser Klasse (einschließlich möglicher <TT>package</TT>-Namen)
 * wird beim Start des Servers angegeben, damit er dieses Modul behandelt.
 * Der Server lädt dann diese Klasse und erzeugt über den parameterlosen
 * Konstruktor (der auch dann für eine Klasse vom Compiler erzeugt wurde,
 * wenn er nicht definiert wurde) eine Instanz.
 * Diese Instanz stellt im folgenden das Modul innerhalb des Servers dar.
 */
public class  Module2Impl
    implements Module2
{

    /**
     * Diese Methode wird vom Server aufgerufen, nachdem die Klasse
     * erfolgreich erzeugt worden ist.
     * <P>
     * Initialisierungen für ein Modul sollten hier stattfinden und möglichst
     * nicht im Konstruktor - am besten man läßt einen Konstruktor weg.
     * <P>
     * Diese Methode wird nur einmal aufgerufen.
     *
     * @param   context  Modul-Kontext, erlaubt Zugriff auf den Server
     */
    // implementiert genRob.genControl.modules.Module2
    public void  moduleInit (ModuleContext context)
        throws Exception
    {
        // Vermerke Instanz zum Loggen
        m_rLogger = context. getLogger ();

        // Damit wir etwas auf der Konsole sehen ...
        m_rLogger. log (null, this, "moduleInit(...)");

        // ... hier kommen die initialisierende Aktivitäten hinein.
        // Roblets® laufen noch nicht.
        m_iRoblet = 0;

        // In unserem Falle:
        System. setProperty ("mymodule.hello", "Hello World!");
    }
    /**
     * Instanz zum Loggen.
     */
    private Logger  m_rLogger;
    /**
     * Variable zum Zählen von Roblets®.
     */
    private int  m_iRoblet;

    /**
     * Diese Methode wird vom Server aufgerufen, bevor er seine Arbeit beendet.
     * Dies erlaubt dem Modul benutzte Ressourcen freizugeben bzw. in einen
     * guten Zustand zurückzuversetzen.
     * <P>
     * Diese Methode wird nur einmal aufgerufen.
     */
    // implementiert genRob.genControl.modules.Module2
    public void  moduleDone ()
    {
        // Damit wir etwas auf der Konsole sehen ...
        m_rLogger. log (null, this, "moduleDone()");

        // ... hier kommen die finalen Aktivitäten hinein.
        // Roblets® laufen hier und danach nicht mehr.

        // In unserem Falle:
        // Es gibt nichts globales aufzuräumen.
    }

    /**
     * Wenn ein Roblet® eine Einheiten-Instanz anfordert, dann wird daraufhin
     * diese Methode vom Server aufgerufen wird.
     * <P>
     * Die Module werden der Reihe nach befragt.
     * Gibt eines eine Einheiten-Instanz zurück, so endet die Befragung
     * insgesamt.
     *
     * @param   rClass  Schnittstelle (<TT>interface</TT>) zu dem eine
     *                  Einheiten-Instanz gefragt ist.
     * @param   rUse    Nutzungszähler, der von der allen Instanz zu benutzen
     *                  ist, die von Threads des Roblets® aufgerufen werden
     *                  können.
     * @param   rSlot   Fach, in dem das Roblet® läuft
     * @return  Einheiten-Instanz, sofern das Modul eine solche parat hat.
     *          Ansonsten <TT>null</TT>.
     */
    // implementiert genRob.genControl.modules.Module2
    public Unit  getUnit (Class clazz, RobletHandle handle)
    {
        // Damit wir etwas auf der Konsole sehen ...
        m_rLogger. log (null, this, "getUnit(" + clazz + "," + handle + ")");

        // ...  hier kann man nun eine passende Einheiten-Instanz zurückgeben.

        // In unserem Fall:
        // Ist die angegebene Einheiten-Definition bekannt?
        if (clazz == HelloUnit.class)
            // Dann geben wir eine neue Instanz zurück.
            return new HelloUnitImpl (m_rLogger);
                // In unserem Falle geben wir bei jedem Aufruf eine neue Instanz
                // zurück.  Der Server optimiert nach Möglichkeit und reduziert
                // so die nötigen Aufrufe von getUnit(...) sowieso.

        // Ansonsten:  Wir kennen die Einheiten-Definition nicht.
        return null;
    }

    /**
     * Diese Methode ermöglicht Handlungen, bevor ein neues Roblet losläuft.
     * @param handle  Roblet®-Repräsentant
     */
    // implementiert genRob.genControl.modules.Module2
    public void  robletInit (RobletHandle handle)
    {
        // Erhöhe den Zähler
        ++m_iRoblet;

        // Daten, die das Modul an den Roblet®-Repräsentanten anhängen will
        Integer  integer = new Integer (m_iRoblet);

        // Füge Daten zum Roblet®-Repräsentant hinzu
        handle. put (this, integer);

        // Damit wir etwas auf der Konsole sehen ...
        m_rLogger. log (null, this, "robletInit(" + handle + ") -> " + integer);
    }

    /**
     * Ermöglicht Handlungen nachdem ein Roblet® endete.
     * @param handle  Roblet®-Repräsentant
     */
    // implementiert genRob.genControl.modules.Module2
    public void  robletDone (RobletHandle handle)
    {
        Integer  integer = (Integer) handle. remove (this);

        // Damit wir etwas auf der Konsole sehen ...
        m_rLogger. log (null, this, "robletDone(" + handle + ") -> " + integer);
    }

}
