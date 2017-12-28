
package mymodule;

import  genRob.genControl.modules.Log;
import  genRob.genControl.modules.Module;
import  genRob.genControl.modules.Registry;
import  genRob.genControl.modules.Slot;
import  genRob.genControl.modules.Supervisor;
import  genRob.genControl.modules.Use;
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
public class  ModuleImpl
    implements Module
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
     * @param   rSupervisor  Server-Kontext, erlaubt Zugriff auf den Server
     * @param   rUse    (veraltet, unbenutzt)
     */
    // implementiert genRob.genControl.modules.Module
    public void  moduleInit (Supervisor rSupervisor, Use rUse)
        throws Exception
    {
        // Vermerke Instanz zum Loggen
        m_rLog = rSupervisor. getLog ();

        // Damit wir etwas auf der Konsole sehen ...
        m_rLog. out (null, getClass (), "moduleInit(...)");

        // ... hier kommen die initialisierende Aktivitäten hinein.
        // Roblets® laufen noch nicht.

        // In unserem Falle:
        System. setProperty ("mymodule.hello", "Hello World!");
    }
    /**
     * Instanz zum Loggen.
     */
    private Log  m_rLog;

    /**
     * Diese Methode wird vom Server aufgerufen, bevor er seine Arbeit beendet.
     * Dies erlaubt dem Modul benutzte Ressourcen freizugeben bzw. in einen
     * guten Zustand zurückzuversetzen.
     * <P>
     * Diese Methode wird nur einmal aufgerufen.
     */
    // implementiert genRob.genControl.modules.Module
    public void  moduleDone ()
    {
        // Damit wir etwas auf der Konsole sehen ...
        m_rLog. out (null, getClass (), "moduleDone()");

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
     *                  Einheit-Instanz gefragt ist.
     * @param   rUse    Nutzungszähler, der von der allen Instanz zu benutzen
     *                  ist, die von Threads des Roblets® aufgerufen werden
     *                  können.
     * @param   rSlot   Fach, in dem das Roblet® läuft
     * @return  Einheiten-Instanz, sofern das Modul eine solche parat hat.
     *          Ansonsten <TT>null</TT>.
     */
    // implementiert genRob.genControl.modules.Module
    public Unit  getUnit4Slot (Class rClass, Use rUse, Slot rSlot)
    {
        // Damit wir etwas auf der Konsole sehen ...
        m_rLog. out (null, getClass ()
                            , "getUnit4Slot(" + rClass + "," + rUse + ")");

        // ...  hier kann man nun eine passende Einheiten-Instanz zurückgeben.

        // In unserem Fall:
        // Ist die angegebene Einheiten-Definition bekannt?
        if (rClass == HelloUnit.class)
            // Dann geben wir eine neue Instanz zurück.
            return new HelloUnitImpl (rUse, m_rLog);
                // In unserem Falle geben wir bei jedem Aufruf eine neue Instanz
                // zurück.  Das ist nicht optimal, aber durchaus erlaubt.
                // Optimal wäre, pro Roblet® nur eine Instanz zu haben.

        // Ansonsten:  Wir kennen die Schnittstelle nicht.
        return null;
    }

    /**
     * Wurde ein Roblet® angehalten, so wird danach vom Server diese Routine
     * für jede Einheiten-Instanz, die vom Roblet® benutzt wurde, aufgerufen.
     * Dadurch kann das Modul notfalls entsprechende Aufräumarbeiten
     * durchführen.
     * <P>
     * Der Server erlaubt erst dann wieder, daß ein neues Roblet®
     * geladen und gestartet wird, wenn dieser Vorgang für alle vom letzten
     * Roblet® benutzten Einheiten-Instanzen durchgeführt wurde.
     *
     * @param   rUnit   Einheiten-Instanz, die eventuell zurückgesetzt
     *                  werden soll
     * @return  <TT>true</TT>, wenn die Einheiten-Instanz von diesem Modul kam
     *          (und eventuell zurückgesetzt wurde)
     */
    // implementiert genRob.genControl.modules.Module
    public boolean  resetUnit4Slot (Unit rUnit)
    {
        // Damit wir etwas auf der Konsole sehen ...
        m_rLog. out (null, getClass (), "resetUnit4Slot(" + rUnit + ")");

        // ...  hier kann man nun die Einheiten passend zurücksetzen
        // und intern alles auf weitere Roblets® vorbereiten.

        // In unserem Fall:
        // Ist die Instanz von der Einheiten-Implementierung, die wir benutzen?
        if (rUnit instanceof HelloUnitImpl)
        {
            // Rufe die zugehörige Aufräum-Methode auf.
            ((HelloUnitImpl) rUnit). reset ();

            // Der Server braucht keine weiteren Einheiten befragen.
            return true;
        }


        // Ansonsten:  Wir kennen die Einheit nicht.
        return false;
    }


    //  Nicht mehr verwendete Methoden

    // implementiert genRob.genControl.modules.Module
    public Unit  getUnit (Class rClass) { return null; }
    // implementiert genRob.genControl.modules.Module
    public Registry  getRegistry ()     { return null; }

}
