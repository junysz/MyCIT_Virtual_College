package ie.cork.mycit.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import ie.cork.mycit.group1.R;
public class LocalPersistence {

    //Offline File Name = "enofflinebackup"
    //en stands for the english back up
    //pl is the polish backup
    //offlinebackup is the name of the file
    //260215 stands for the data this offline file was updated from online file

    private static String sysLang;

    private static String en = "en";
    private static String enFile = "enDB";

    private static String pl = "pl";
    private static String plFile = "plDB";

    public static void offlineFile(Context context) {

        sysLang = Locale.getDefault().getLanguage();
        int number;
        if(sysLang.equalsIgnoreCase(en)){
            if(getFromSP(context, en)){
                Log.i("Files", "read en saved file");
            }else{
                offLineFile(context, enFile, R.raw.enofflinebackup);
                Log.i("Files", "read en backup");
            }
        }else if(sysLang.equalsIgnoreCase(pl)){
            if(getFromSP(context, pl)){
                Log.i("Files", "read pl saved file");
            }else{
                offLineFile(context, plFile, R.raw.plofflinebackup);
                Log.i("Files", "read pl backup");
            }
        }else{
            if(getFromSP(context, en)){
                Log.i("Files", "read en saved file");
            }else{
                offLineFile(context, enFile, R.raw.enofflinebackup);
                Log.i("Files", "read en backup");
            }
        }

    }

    public static void offLineFile(Context context, String FILENAME, int BackupFile) {

        InputStream in = context.getResources().openRawResource(BackupFile);
        FileOutputStream out = null;
        try {
            out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            Log.i("result", "Offline file Failed FileNotFoundException");
            Log.i("result", e.getMessage());
        }

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            Log.i("result", "Offline file Failed IOException");
            Log.i("result", e.getMessage());
        }

    }

    private static boolean getFromSP(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    private static void saveInSp(Context context, String key, boolean value){
        SharedPreferences preferences = context.getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void writeObjectToFile(Context context, Object object) {

        sysLang = Locale.getDefault().getLanguage();
        String FILENAME;
        if(sysLang.equalsIgnoreCase(en)){
            FILENAME = enFile;
            saveInSp(context, en, true);
            Log.i("Files", "Saved preference en");
        }else if(sysLang.equalsIgnoreCase(pl)){
            FILENAME = plFile;
            saveInSp(context, pl, true);
            Log.i("Files", "Saved preference pl");
        }else{
            FILENAME = enFile;
            saveInSp(context, en, true);
            Log.i("Files", "Saved preference en");
        }
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(object);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.i("result", e.getMessage());
        } catch (IOException e) {
            Log.i("result", e.getMessage());
        }

    }


    public static TableData readObjectFromFile(Context context) {

        sysLang = Locale.getDefault().getLanguage();
        int number;
        if(sysLang.equalsIgnoreCase(en)){
            return readObjectFromFileCode(context, enFile);
        }else if(sysLang.equalsIgnoreCase(pl)){
            return readObjectFromFileCode(context, plFile);
        }else{
            return readObjectFromFileCode(context, enFile);
        }

    }

    public static TableData readObjectFromFileCode(Context context, String FILENAME) {

        ObjectInputStream objectIn = null;
        TableData object = null;
        try {
            FileInputStream fileIn = context.getApplicationContext().openFileInput(FILENAME);
            objectIn = new ObjectInputStream(fileIn);
            object = (TableData) objectIn.readObject();
        } catch (FileNotFoundException e) {
            Log.i("result", e.getMessage());
        } catch (IOException e) {
            Log.i("result", e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.i("result", e.getMessage());
        } finally {
            if (objectIn != null){
                try {
                    objectIn.close();
                } catch (IOException e){
                }
            }
        }
        return object;
    }

    public static List<String> readIDNameLink(int switchCase, ArrayList<IDNameLink> data){
        List<String> list = new ArrayList<String>();
        ListIterator<IDNameLink> ltr = data.listIterator();
        switch (switchCase) {
            case 1:
                while(ltr.hasNext()){
                    IDNameLink item = ltr.next();
                    list.add(Integer.toString(item.getID()));
                }
                break;
            case 2:
                while(ltr.hasNext()){
                    IDNameLink item = ltr.next();
                    list.add(item.getName());
                }
                break;
            case 3:
                while(ltr.hasNext()){
                    IDNameLink item = ltr.next();
                    list.add(item.getLink());
                }
                break;
        }
        return list;
    }

    public static List<String> readIDItem(int switchCase, ArrayList<IDItem> data){
        List<String> list = new ArrayList<String>();
        ListIterator<IDItem> ltr = data.listIterator();
        switch (switchCase) {
            case 1:
                while(ltr.hasNext()){
                    IDItem item = ltr.next();
                    list.add(Integer.toString(item.getID()));
                }
                break;
            case 2:
                while(ltr.hasNext()){
                    IDItem item = ltr.next();
                    list.add(item.getName());
                }
                break;
        }
        return list;
    }

}