package ie.cork.mycit.database;

public class IDItem {

    private int menu_id;
    private String item;

    public IDItem(int menu_id, String item){
        this.menu_id = menu_id;
        this.item = item;
    }

    public int getID(){
        return menu_id;
    }
    public void setID(int menu_id){
        this.menu_id = menu_id;
    }

    public String getName(){
        return item;
    }
    public void setName(String item){
        this.item = item;
    }

}
