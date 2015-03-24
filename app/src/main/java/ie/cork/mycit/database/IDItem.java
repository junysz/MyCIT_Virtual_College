package ie.cork.mycit.database;

public class IDItem {

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
