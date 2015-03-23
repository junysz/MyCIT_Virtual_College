package ie.cork.mycit.database;

import java.util.ArrayList;

public class DeptIDNameArrayList {

    private int dept_id;
    private String name;
    private ArrayList<DeptIDClass> classes = new ArrayList<DeptIDClass>();

    public DeptIDNameArrayList(int dept_id, String name, ArrayList<DeptIDClass> classes){
        this.dept_id = dept_id;
        this.name = name;
        this.classes = classes;
    }

    public int getID(){
        return dept_id;
    }
    public void setID(int dept_id){
        this.dept_id = dept_id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public ArrayList getClasses(){
        return classes;
    }
    public void setClasses(ArrayList<DeptIDClass> classes){
        this.classes = classes;
    }

}
