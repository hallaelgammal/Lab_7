/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author New Eng
 */

import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    

   private List<Course> courses = new ArrayList<>();  // dah small database kol mara b3ml add l course by3ml save gowa el list dih

    public static class Course { // dah el static inner class lel course el feeh el unique number w el course name w el details
        private int id;
        private String title; 
        private String description; 

        public Course(int id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        public int getId() { 
            return id;
        }
        public String getTitle(){ 
            return title; 
        }
        public String getDescription() { 
            return description; 
        }
        public void setTitle(String title) { 
            this.title = title;
        }
        public void setDescription(String description) { 
            this.description = description;
        }

        @Override
        public String toString() { // 3shan lama agy a3ml print 
            return id + ": " + title + " - " + description;
        }
    }

    private int getNextCourseId() { // bgeeb number el next course el ana hzwdo fa hzwd one 3la el size abl ama a3ml add
        return courses.size() + 1;
    }
    //by3ml check law el title empty hn3ml return null law la han3ml new object bl unique id el zwdnah fo2 w a3ml add course lel list w b3den a3ml return l list el courses
    public Course addCourse(String title, String description) { 
        if (title == null || title.isEmpty()) 
        return null;
        Course c = new Course(getNextCourseId(), title, description);
        courses.add(c);
        return c;
    }

    public boolean editCourse(int id, String newTitle, String newDescription) {
        Course c = getCourse(id);
        if (c == null) 
        return false;
        if (newTitle != null && !newTitle.isEmpty()) 
        c.setTitle(newTitle);
        if (newDescription != null && !newDescription.isEmpty())
        c.setDescription(newDescription);
        return true;
    }

    public boolean deleteCourse(int id) {
        Course c = getCourse(id);
        if (c == null) 
        return false;
        courses.remove(c);
        return true;
    }

    public Course getCourse(int id) {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public List<Course> getAllCourses() { // b3ml f el akher return l kol el list
        return courses;
    }
}


    

