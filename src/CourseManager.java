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

    // Generate a new course ID based on the number of existing courses
    private String generateCourseId() {
        ArrayList<Course> courses = db.getCourses();
        return "C" + (courses.size() + 1);
    }

    // Add a new course
    public Course addCourse(String title, String description, String instructorId) {
        if (title == null || title.trim().isEmpty()) 
            return null;

        ArrayList<Course> courses = db.getCourses();
        if (courses == null) {
            courses = new ArrayList<>();
        }

        Course c = new Course(generateCourseId(), title, description, instructorId);
        courses.add(c);  
        db.saveCourses();   
        return c;
    }

    // Edit an existing course
    public boolean editCourse(String id, String newTitle, String newDescription) {
        Course c = getCourse(id);
        if (c == null) 
            return false;

        if (newTitle != null && !newTitle.isEmpty())
            c.setTitle(newTitle);

        if (newDescription != null && !newDescription.isEmpty())
            c.setDescription(newDescription);

        db.saveCourses();
        return true;
    }

    // Delete a course
    public boolean deleteCourse(String id) {
        ArrayList<Course> courses = db.getCourses();
        Course c = getCourse(id);
        if (c == null)
            return false;

        courses.remove(c);  
        db.saveCourses();
        return true;
    }

    // Get a course by ID
    public Course getCourse(String id) {
        for (Course c : db.getCourses()) {
            if (c.getCourseId().equals(id)) 
                return c;
        }
        return null;
    }

    // Get all courses
    public ArrayList<Course> getAllCourses() {
        return db.getCourses();  
    }

    // Enroll a student in a course
    public boolean enrollStudent(String courseId, String studentId) {
        Course c = getCourse(courseId);
        if (c == null) return false;

        if (c.getStudents().contains(studentId)) return false; // already enrolled
        c.getStudents().add(studentId);
        db.saveCourses();
        return true;
    }
}
