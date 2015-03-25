package ie.cork.mycit.database;

import java.io.Serializable;
import java.util.ArrayList;

public class TableData implements Serializable {

    private static final int serialVersionUID = 50;

    private ArrayList<IDNameLink> academicInfoArray = new ArrayList<IDNameLink>();
    private ArrayList<IDItem> classesArray = new ArrayList<IDItem>();
    private ArrayList<IDItem> departmentArray = new ArrayList<IDItem>();
    private ArrayList<IDNameLink> importantDocsArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> itServicesArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> mapsArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> newStudentsArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> otherCollegesArray = new ArrayList<IDNameLink>();
    private ArrayList<IDItem> sideMenuArray = new ArrayList<IDItem>();
    private ArrayList<IDNameLink> studentAppsArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> studentHandbooksArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> studentNewsArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> supportServicesArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> usefulResourcesArray = new ArrayList<IDNameLink>();
    private ArrayList<IDNameLink> videosArray = new ArrayList<IDNameLink>();

    public ArrayList<IDItem> getClassesArray() {
        return classesArray;
    }
    public void setClassesArray(ArrayList<IDItem> classesArray) {
        this.classesArray = classesArray;
    }

    public ArrayList<IDNameLink> getAcademicInfoArray() {
        return academicInfoArray;
    }
    public void setAcademicInfoArray(ArrayList<IDNameLink> academicInfoArray) {
        this.academicInfoArray = academicInfoArray;
    }

    public ArrayList<IDItem> getDepartmentArray() {
        return departmentArray;
    }
    public void setDepartmentArray(ArrayList<IDItem> departmentArray) {
        this.departmentArray = departmentArray;
    }

    public ArrayList<IDNameLink> getImportantDocsArray() {
        return importantDocsArray;
    }
    public void setImportantDocsArray(ArrayList<IDNameLink> importantDocsArray) {
        this.importantDocsArray = importantDocsArray;
    }

    public ArrayList<IDNameLink> getItServicesArray() {
        return itServicesArray;
    }
    public void setItServicesArray(ArrayList<IDNameLink> itServicesArray) {
        this.itServicesArray = itServicesArray;
    }

    public ArrayList<IDNameLink> getMapsArray() {
        return mapsArray;
    }
    public void setMapsArray(ArrayList<IDNameLink> mapsArray) {
        this.mapsArray = mapsArray;
    }

    public ArrayList<IDNameLink> getNewStudentsArray() {
        return newStudentsArray;
    }
    public void setNewStudentsArray(ArrayList<IDNameLink> newStudentsArray) {
        this.newStudentsArray = newStudentsArray;
    }

    public ArrayList<IDNameLink> getOtherCollegesArray() {
        return otherCollegesArray;
    }
    public void setOtherCollegesArray(ArrayList<IDNameLink> otherCollegesArray) {
        this.otherCollegesArray = otherCollegesArray;
    }

    public ArrayList<IDItem> getSideMenuArray() {
        return sideMenuArray;
    }
    public void setSideMenuArray(ArrayList<IDItem> sideMenuArray) {
        this.sideMenuArray = sideMenuArray;
    }

    public ArrayList<IDNameLink> getStudentAppsArray() {
        return studentAppsArray;
    }
    public void setStudentAppsArray(ArrayList<IDNameLink> studentAppsArray) {
        this.studentAppsArray = studentAppsArray;
    }

    public ArrayList<IDNameLink> getStudentHandbooksArray() {
        return studentHandbooksArray;
    }
    public void setStudentHandbooksArray(ArrayList<IDNameLink> studentHandbooksArray) {
        this.studentHandbooksArray = studentHandbooksArray;
    }

    public ArrayList<IDNameLink> getStudentNewsArray() {
        return studentNewsArray;
    }
    public void setStudentNewsArray(ArrayList<IDNameLink> studentNewsArray) {
        this.studentNewsArray = studentNewsArray;
    }

    public ArrayList<IDNameLink> getSupportServicesArray() {
        return supportServicesArray;
    }
    public void setSupportServicesArray(ArrayList<IDNameLink> supportServicesArray) {
        this.supportServicesArray = supportServicesArray;
    }

    public ArrayList<IDNameLink> getUsefulResourcesArray() {
        return usefulResourcesArray;
    }
    public void setUsefulResourcesArray(ArrayList<IDNameLink> usefulResourcesArray) {
        this.usefulResourcesArray = usefulResourcesArray;
    }

    public ArrayList<IDNameLink> getVideosArray() {
        return videosArray;
    }
    public void setVideosArray(ArrayList<IDNameLink> videosArray) {
        this.videosArray = videosArray;
    }

}
