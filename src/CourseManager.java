/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author New Eng
 */
import java.util.ArrayList;

public class CourseManager {

    private JsonDatabaseManager db;

    public CourseManager(JsonDatabaseManager db) {
        this.db = db;
    }

    private String generateCourseId() {
        ArrayList<CourseManager> courses = db.getCourses();
        return "C" + (courses.size() + 1);
    }

    public Course addCourse(String title, String description, String instructorId) {
        if (title == null || title.trim().isEmpty()) 
            return null;

        ArrayList<CourseManager> courses = db.getCourses();

        
        if (courses == null) {
            courses = new ArrayList<>();
        }

        
        Course c = new Course(generateCourseId(), title, description, instructorId);
        courses.add(c);  
        db.saveCourses();   
        return c;
    }

    public boolean editCourse(String id, String newTitle, String newDescription) {
        CourseManager c = getCourse(id);
        if (c == null) 
            return false;

        if (newTitle != null && !newTitle.isEmpty())
            c.setTitle(newTitle);

        if (newDescription != null && !newDescription.isEmpty())
            c.setDescription(newDescription);

        db.saveCourses();
        return true;
    }

    public boolean deleteCourse(String id) {
        ArrayList<CourseManager> courses = db.getCourses();
        CourseManager c = getCourse(id);
        if (c == null)
            return false;

        courses.remove(c);  
        db.saveCourses();

        return true;
    }

    public CourseManager getCourse(String id) {
        for (CourseManager c : db.getCourses()) {
            if (c.getCourseId().equals(id)) 
                return c;
        }
        return null;
    }

    public ArrayList<CourseManager> getAllCourses() {
        return db.getCourses();  
    }

    private Object getCourseId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setTitle(String newTitle) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setDescription(String newDescription) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
