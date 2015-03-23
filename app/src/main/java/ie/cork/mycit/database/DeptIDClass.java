package ie.cork.mycit.database;

public class DeptIDClass {

    private int dept_id;
    private String nameOfClass;

    public DeptIDClass(int dept_id, String nameOfClass){
        this.dept_id = dept_id;
        this.nameOfClass = nameOfClass;
    }

    public int getID(){
        return dept_id;
    }
    public void setID(int dept_id){
        this.dept_id = dept_id;
    }

    public String getName(){
        return nameOfClass;
    }
    public void setName(String nameOfClass){
        this.nameOfClass = nameOfClass;
    }

}
