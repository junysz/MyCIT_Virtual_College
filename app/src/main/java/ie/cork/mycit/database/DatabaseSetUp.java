package ie.cork.mycit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseSetUp extends SQLiteOpenHelper {

    private static final String LOGTAG = "CITDATABASE";
    private static final String DATABASE_NAME = "citdatabase.db";
    private static final int DATABASE_VERSION = 1;

    public static final String T_ACADEMICINFO = "acamdemicinformation";
    public static final String T_CLASSES = "classes";
    public static final String T_DEPARTMENT = "department";
    public static final String T_IMPORTANTDOCS = "importantdocuments";
    public static final String T_ITSERVICES = "itservices";
    public static final String T_MAPS = "maps";
    public static final String T_NEWSTUDENTS = "newstudents";
    public static final String T_OTHERCOLLEGES = "othercolleges";
    public static final String T_SIDEMENU = "sidemenu";
    public static final String T_STUDENTAPPS = "studentapplications";
    public static final String T_STUHANDBOOKS = "studenthandbooks";
    public static final String T_SUPPORTSERVICES = "supportservices";
    public static final String T_USEFULRES = "usefulresources";
    public static final String T_VIDEOS = "videos";

    public static final String COLUMN_MENUID = "menu_id";
    public static final String COLUMN_MENUITEM = "menu_item";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LINK = "link";
    public static final String COLUMN_DEPTID = "dept_id";
    public static final String COLUMN_CLASS = "class";

    public static final String INT_TYPE = " INT(10)";
    public static final String SVCHAR_TYPE = " VARCHAR(100)";
    public static final String LVCHAR_TYPE = " VARCHAR(200)";

    private static final String CREATE_ACADEMICINFO =
            "CREATE TABLE " + T_ACADEMICINFO + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_CLASSES =
            "CREATE TABLE " + T_CLASSES + " (" +
                    COLUMN_DEPTID + INT_TYPE + ", " +
                    COLUMN_CLASS + SVCHAR_TYPE + " PRIMARY KEY" + ")";
    private static final String CREATE_DEPARTMENT =
            "CREATE TABLE " + T_DEPARTMENT + " (" +
                    COLUMN_DEPTID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY" + ")";
    private static final String CREATE_IMPORTANTDOCS =
            "CREATE TABLE " + T_IMPORTANTDOCS + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_ITSERVICES =
            "CREATE TABLE " + T_ITSERVICES + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_MAPS =
            "CREATE TABLE " + T_MAPS + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_OTHERCOLLEGES =
            "CREATE TABLE " + T_OTHERCOLLEGES + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_NEWSTUDENTS =
            "CREATE TABLE " + T_NEWSTUDENTS + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_SIDEMENU =
            "CREATE TABLE " + T_SIDEMENU + " (" +
                    COLUMN_MENUID + INT_TYPE + " PRIMARY KEY, " +
                    COLUMN_MENUITEM + SVCHAR_TYPE + ")";
    private static final String CREATE_STUDENTAPPS =
            "CREATE TABLE " + T_STUDENTAPPS + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_STUHANDBOOKS =
            "CREATE TABLE " + T_STUHANDBOOKS + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_SUPPORTSERVICES =
            "CREATE TABLE " + T_SUPPORTSERVICES + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_USEFULRES =
            "CREATE TABLE " + T_USEFULRES + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";
    private static final String CREATE_VIDEOS =
            "CREATE TABLE " + T_VIDEOS + " (" +
                    COLUMN_MENUID + INT_TYPE + ", " +
                    COLUMN_NAME + SVCHAR_TYPE + " PRIMARY KEY," +
                    COLUMN_LINK + LVCHAR_TYPE + ")";

    public DatabaseSetUp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ACADEMICINFO);
        sqLiteDatabase.execSQL(CREATE_CLASSES);
        sqLiteDatabase.execSQL(CREATE_DEPARTMENT);
        sqLiteDatabase.execSQL(CREATE_IMPORTANTDOCS);
        sqLiteDatabase.execSQL(CREATE_ITSERVICES);
        sqLiteDatabase.execSQL(CREATE_MAPS);
        sqLiteDatabase.execSQL(CREATE_OTHERCOLLEGES);
        sqLiteDatabase.execSQL(CREATE_NEWSTUDENTS);
        sqLiteDatabase.execSQL(CREATE_SIDEMENU);
        sqLiteDatabase.execSQL(CREATE_STUDENTAPPS);
        sqLiteDatabase.execSQL(CREATE_STUHANDBOOKS);
        sqLiteDatabase.execSQL(CREATE_SUPPORTSERVICES);
        sqLiteDatabase.execSQL(CREATE_USEFULRES);
        sqLiteDatabase.execSQL(CREATE_VIDEOS);
        Log.i(LOGTAG, "TABLES WERE CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_ACADEMICINFO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_CLASSES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_DEPARTMENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_IMPORTANTDOCS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_ITSERVICES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_MAPS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_OTHERCOLLEGES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_NEWSTUDENTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_SIDEMENU);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_STUDENTAPPS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_STUHANDBOOKS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_SUPPORTSERVICES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_USEFULRES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_VIDEOS);
        onCreate(sqLiteDatabase);
        Log.i(LOGTAG, "TABLES WERE UPDATED");
    }
}
