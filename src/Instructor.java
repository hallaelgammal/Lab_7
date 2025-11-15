/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7.me;

import java.util.ArrayList;

public class Instructor extends User {
    private ArrayList<Integer> createdCourses; // store IDs of courses created by this instructor
    private ArrayList<Integer> createdLessons; // store IDs of lessons created by this instructor

    // Constructor
    public Instructor(String userId, String username, String email, String passwordHash, ArrayList<Integer> createdCourses) {
        super(userId, Role.INSTRUCTOR, username, email, passwordHash);
        this.createdCourses = (createdCourses != null) ? createdCourses : new ArrayList<>();
        this.createdLessons = new ArrayList<>();
    }

    public int addCourse(CourseManager manager, String title, String description) {
        CourseManager.Course course = manager.addCourse(title, description);
        if (course != null) {
            createdCourses.add(course.getId());
            return course.getId();
        }
        return -1; // failed to create course
    }

    public boolean editCourse(CourseManager manager, int courseId, String newTitle, String newDescription) {
        if (!createdCourses.contains(courseId)) return false;
        return manager.editCourse(courseId, newTitle, newDescription);
    }

    public boolean deleteCourse(CourseManager manager, int courseId) {
        if (!createdCourses.contains(courseId)) return false;
        boolean deleted = manager.deleteCourse(courseId);
        if (deleted) createdCourses.remove(Integer.valueOf(courseId));
        return deleted;
    }

    public int addLesson(LessonManager manager, int courseId, String title, String content, String[] resources) {
        LessonManager.Lesson lesson = manager.addLesson(courseId, title, content, resources);
        if (lesson != null) {
            createdLessons.add(lesson.getLessonId());
            return lesson.getLessonId();
        }
        return -1; // failed to create lesson
    }

    public boolean editLesson(LessonManager manager, int lessonId, String newTitle, String newContent, String[] newResources) {
        if (!createdLessons.contains(lessonId)) return false;
        return manager.editLesson(lessonId, newTitle, newContent, newResources);
    }

    public boolean deleteLesson(LessonManager manager, int lessonId) {
        if (!createdLessons.contains(lessonId)) return false;
        boolean deleted = manager.deleteLesson(lessonId);
        if (deleted) createdLessons.remove(Integer.valueOf(lessonId));
        return deleted;
    }

    // -------------------
    // View Enrolled Students
    // -------------------

    public ArrayList<String> viewEnrolledStudents(CourseManager manager, int courseId) {
        if (!createdCourses.contains(courseId)) return null; // can only view students for own courses
        CourseManager.Course course = manager.getCourse(courseId);
        if (course != null) return course.getEnrolledStudents();
        return null;
    }

    public ArrayList<Integer> getCreatedCourses() {
        return createdCourses;
    }

    public ArrayList<Integer> getCreatedLessons() {
        return createdLessons;
    }
}
