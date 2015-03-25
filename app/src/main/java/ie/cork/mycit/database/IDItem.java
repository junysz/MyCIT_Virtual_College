package ie.cork.mycit.database;

import java.io.Serializable;

public class IDItem implements Serializable {

    private static final int serialVersionUID = 60;

    private int id;
    private String item;

    public IDItem(int id, String item){
        this.id = id;
        this.item = item;
    }

    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }

    public String getName(){
        return item;
    }
    public void setName(String item){
        this.item = item;
    }

}
