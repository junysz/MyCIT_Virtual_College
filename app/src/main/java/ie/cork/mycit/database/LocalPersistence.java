package ie.cork.mycit.database;

import android.content.Context;
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

import ie.cork.mycit.group1.R;

public class LocalPersistence {

    private static String FILENAME = "InteralString";
    //Offline File Name = "enofflinebackup260215"
    //en stands for the english back up
    //pl is the polish backup
    //offlinebackup is the name of the file
    //260215 stands for the data this offline file was updated from online file

    public static void offlineFile(Context context, String lang) {

        Resources res;
        InputStream in;
        if(lang.equalsIgnoreCase("en")){
            in = context.getResources().openRawResource(R.raw.enofflinebackup260215);
        }else if(lang.equalsIgnoreCase("pl")){
            in = context.getResources().openRawResource(R.raw.plofflinebackup260215);
        }else{
            in = context.getResources().openRawResource(R.raw.enofflinebackup260215);
        }
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

    public static void writeObjectToFile(Context context, Object object) {

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