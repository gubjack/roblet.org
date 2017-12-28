
import  genRob.genSite.Converter;
import  genRob.genSite.data.Image;
import  genRob.genSite.data.Link;
import  genRob.genSite.data.Top;


/**
 * Dieses Java-Programm erzeugt die Website.
 */
public class  Generator
{

    /**
     * Methode, die beim Bau aufgerufen wird.
     * @param args [0] erstes Jahr<BR>
     *             [1] aktuelles Jahr
     */
    public static void  main (String[] args)
        throws Exception
    {
        httpdocs (args);
    }

    private final static Link  mfs_rLink_Contact
    = new Link ("Kontakt",          "contact.html");
    private final static Link  mfs_rLink_Download
    = new Link ("Download",         "download.html");
    private final static Link  mfs_rLink_Download_2dot
    = new Link ("Download",          "../de/download.html");
    private final static Link  mfs_rLink_Download_Book
    = new Link ("Buch",             "index.html");
    private final static Link  mfs_rLink_Download_Book_2dot
    = new Link ("Buch",             "../book/index.html");
    private final static Link  mfs_rLink_Download_Lecture
    = new Link ("Vortrag",          "index.html");
    private final static Link  mfs_rLink_Download_Lecture_2dot
    = new Link ("Vortrag",          "../lecture/index.html");
    private final static Link  mfs_rLink_Download_RDK
    = new Link ("RDK",              "index.html");
    private final static Link  mfs_rLink_Download_RDK_2dot
    = new Link ("RDK",              "../rdk/index.html");
    private final static Link  mfs_rLink_Home
    = new Link ("Home",             "index.html");
    private final static Link  mfs_rLink_Home_2dot
    = new Link ("Home",             "../de/index.html");
    private final static Link  mfs_rLink_Mailing
    = new Link ("Mailing",          "mailing.html");
    private final static Link  mfs_rLink_News
    = new Link ("Neuigkeiten",      "news.html");
    private final static Link  mfs_rLink_Offer
    = new Link ("Angebot",          "offer.html");
    private final static Link  mfs_rLink_Organization
    = new Link ("Organisation",     "organization.html");
    private final static Link  mfs_rLink_References
    = new Link ("Referenzen",       "references.html");
    private final static Link  mfs_rLink_Technique
    = new Link ("Technik",          "technique.html");
    private final static Link  mfs_rLink_Technique_Abstract
    = new Link ("Übersicht",        "technique.abstract.html");
    private final static Link  mfs_rLink_Technique_Abstract_Directories
    = new Link ("Verzeichnisse",    "technique.abstract.directories.html");
    private final static Link  mfs_rLink_Technique_Abstract_FAQ
    = new Link ("FAQ",              "technique.abstract.faq.html");
    private final static Link  mfs_rLink_Technique_Abstract_Future
    = new Link ("Zukunft",          "technique.abstract.future.html");
    private final static Link  mfs_rLink_Technique_Abstract_Proxies
    = new Link ("Vertreter",        "technique.abstract.proxies.html");
    private final static Link  mfs_rLink_Technique_Abstract_Units
    = new Link ("Einheiten",        "technique.abstract.units.html");
    private final static Link  mfs_rLink_Technique_Book
    = new Link ("Buch",             "technique.book.html");
    private final static Link  mfs_rLink_Technique_Preface
    = new Link ("Vorwort",          "technique.preface.html");
    private final static Link  mfs_rLink_Technique_Sample
    = new Link ("Hello World!",     "technique.sample.html");
    private final static Link  mfs_rLink_Technique_Software
    = new Link ("Software",         "technique.software.html");


    private static void  httpdocs (String[] args)
        throws Exception
    {
        String  strYear_from = args [0];
        String  strYear_to = args [1];
        Image  rImage_Home = new Image ("../home.png", mfs_rLink_Home);
        Image  rImage_Home_2dot = new Image ("../home.png", mfs_rLink_Home_2dot);
        new Converter_de (rImage_Home, strYear_from, strYear_to)
                . convert ();
        new Converter_RDK (rImage_Home_2dot, strYear_from, strYear_to)
                . convert ();
        new Converter_book (rImage_Home_2dot, strYear_from, strYear_to)
                . convert ();
        new Converter_lecture (rImage_Home_2dot, strYear_from, strYear_to)
                . convert ();
    }

    private static class  DocConverter
        extends Converter
    {
        DocConverter (String strSubDir
                    , Image rImage_Home
                    , String strYear_from, String strYear_to)
        {
            super ("doc/" + strSubDir           // Quelle
                    ,"httpdocs/" + strSubDir    // Ziel
                    , rImage_Home               // oben links
                    , strYear_from, strYear_to
                    , "roblet®.org, Hagen Stanek"
                    , new Link[]                // Statuszeilenverknüpfungen
                        {
                            mfs_rLink_Contact,
                        }
                    , false             // keine Erzeugungszeit
                    , true);            // begrenzungslos
        }
    }

    private static class  Converter_de
        extends DocConverter
    {
        Converter_de (Image rImage_Home
                            , String strYear_from, String strYear_to)
        {
            super ("de", rImage_Home, strYear_from, strYear_to);
        }
        void  convert ()
            throws Exception
        {
            Link[]  aLink = 
            {
                mfs_rLink_Home,
                mfs_rLink_Organization,
                mfs_rLink_Offer,
                mfs_rLink_Technique,
                mfs_rLink_Download,
                mfs_rLink_Mailing,
            };
            Top[]  aTop =
            {
            };

            convert (aTop, aLink, 0);
            convert_organization ();
            convert (aTop, aLink, 2);
            convert_technique ();
            convert_download ();
            convert (aTop, aLink, 5);
        }
        private void  convert_download ()
            throws Exception
        {
            Link[]  aLink =
            {
                mfs_rLink_Download,
                mfs_rLink_Download_RDK_2dot,
                mfs_rLink_Download_Book_2dot,
                mfs_rLink_Download_Lecture_2dot,
            };
            Top[]  aTop =
            {
                new Top (mfs_rLink_Home),
            };

            convert (aTop, aLink, 0);
        }
        private void  convert_organization ()
            throws Exception
        {
            Link[]  aLink = 
            {
                mfs_rLink_Organization,
                mfs_rLink_News,
                mfs_rLink_Contact,
                mfs_rLink_References,
            };
            Top[]  aTop =
            {
                new Top (mfs_rLink_Home),
            };

            convert (aTop, aLink, 0);
            convert (aTop, aLink, 1);
            convert (aTop, aLink, 2);
            convert (aTop, aLink, 3);
        }
        private void  convert_technique ()
            throws Exception
        {
            Link[]  aLink = 
            {
                mfs_rLink_Technique,
                mfs_rLink_Technique_Preface,
                mfs_rLink_Technique_Abstract,
                mfs_rLink_Technique_Sample,
                mfs_rLink_Technique_Book,
                mfs_rLink_Technique_Software,
            };
            Top[]  aTop =
            {
                new Top (mfs_rLink_Home),
            };

            convert (aTop, aLink, 0);
            convert (aTop, aLink, 1);
            convert_technique_abstract ();
            convert (aTop, aLink, 3);
            convert (aTop, aLink, 4);
            convert (aTop, aLink, 5);
        }
        private void  convert_technique_abstract ()
            throws Exception
        {
            Link[]  aLink = 
            {
                mfs_rLink_Technique_Abstract,
                mfs_rLink_Technique_Abstract_Units,
                mfs_rLink_Technique_Abstract_Directories,
                mfs_rLink_Technique_Abstract_Proxies,
                mfs_rLink_Technique_Abstract_Future,
                mfs_rLink_Technique_Abstract_FAQ,
            };
            Top[]  aTop =
            {
                new Top (mfs_rLink_Home),
                new Top (mfs_rLink_Technique),
            };

            convert (aTop, aLink, 0);
            convert (aTop, aLink, 1);
            convert (aTop, aLink, 2);
            convert (aTop, aLink, 3);
            convert (aTop, aLink, 4);
            convert (aTop, aLink, 5);
        }
    }
    private static class  Converter_RDK
        extends DocConverter
    {
        Converter_RDK (Image rImage_Home
                            , String strYear_from, String strYear_to)
        {
            super ("RDK", rImage_Home, strYear_from, strYear_to);
        }
        void  convert ()
            throws Exception
        {
            Link[]  aLink =
            {
                mfs_rLink_Download_2dot,
                mfs_rLink_Download_RDK,
                mfs_rLink_Download_Book_2dot,
                mfs_rLink_Download_Lecture_2dot,
            };
            Top[]  aTop =
            {
                new Top (mfs_rLink_Home_2dot),
            };

            convert (aTop, aLink, 1);
        }
    }
    private static class  Converter_book
        extends DocConverter
    {
        Converter_book (Image rImage_Home
                            , String strYear_from, String strYear_to)
        {
            super ("book", rImage_Home, strYear_from, strYear_to);
        }
        void  convert ()
            throws Exception
        {
            Link[]  aLink =
            {
                mfs_rLink_Download_2dot,
                mfs_rLink_Download_RDK_2dot,
                mfs_rLink_Download_Book,
                mfs_rLink_Download_Lecture_2dot,
            };
            Top[]  aTop =
            {
                new Top (mfs_rLink_Home_2dot),
            };

            convert (aTop, aLink, 2);
        }
    }
    private static class  Converter_lecture
        extends DocConverter
    {
        Converter_lecture (Image rImage_Home
                            , String strYear_from, String strYear_to)
        {
            super ("lecture", rImage_Home, strYear_from, strYear_to);
        }
        void  convert ()
            throws Exception
        {
            Link[]  aLink =
            {
                mfs_rLink_Download_2dot,
                mfs_rLink_Download_RDK_2dot,
                mfs_rLink_Download_Book_2dot,
                mfs_rLink_Download_Lecture,
            };
            Top[]  aTop =
            {
                new Top (mfs_rLink_Home_2dot),
            };

            convert (aTop, aLink, 3);
        }
    }

}
