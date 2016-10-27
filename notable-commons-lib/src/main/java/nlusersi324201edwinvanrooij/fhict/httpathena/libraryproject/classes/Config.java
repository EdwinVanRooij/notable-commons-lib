package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes;

/**
 * Created by Edwin on 10/27/2015.
 */
public class Config {
    //General settings
    //Keys
    public static final String KEY_NOTE = "note";
    //Pusher
    public static final String KEY_CHANNEL_NOTES = "Note";
    public static final String KEY_EVENT_NOTE_NEW = "New";
    private static final String KEY_EVENT_NOTE_UPDATE = "Update";
    public static final String KEY_EVENT_NOTE_DELETE = "Delete";
    public static final String[] KEY_EVENTS_NOTES = {KEY_EVENT_NOTE_NEW, KEY_EVENT_NOTE_UPDATE, KEY_EVENT_NOTE_DELETE};
    //region Internet connection

    //Keys
    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_LIST = "list";

    //region PHP script URLs
    private static final String default_url = "http://athena.fhict.nl/users/i324201/dotnet/dotnet1/php/";
    public static final String URL_SELECT_QUERY = default_url + "selectQuery.php";
    public static final String URL_MODIFY_QUERY = default_url + "modifyQuery.php";

    //Default database values
    public static final String DEFAULT_SUBJECT = "Onbekend";
    public static final String DEFAULT_TEXT = "Leeg";

    //Pusher
    public static final String PUSHER_APP_ID = "152700";
    public static final String PUSHER_KEY = "dfb1ee7f4027ed8ca2ab";
    public static final String PUSHER_SECRET = "794c4715344b5bc7ae36";

    //Etc
    public static final String URL_PUSHER = "http://athena.fhict.nl/users/i324201/dotnet/dotnet1/pusher/sendPush.php";
}