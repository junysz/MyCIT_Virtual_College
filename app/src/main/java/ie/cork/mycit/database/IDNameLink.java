package ie.cork.mycit.database;

public class IDNameLink {

    private int menu_id;
    private String name;
    private String link;

    public IDNameLink(int menu_id, String name, String link){
        this.menu_id = menu_id;
        this.name = name;
        this.link = link;
    }

    public int getID(){
        return menu_id;
    }
    public void setID(int menu_id){
        this.menu_id = menu_id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link = link;
    }

}
