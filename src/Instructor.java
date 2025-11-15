/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.util.ArrayList;

public class Instructor extends User {

    private ArrayList<Integer> createdCourses; // IDs of courses this instructor created
    private ArrayList<Integer> createdLessons; // IDs of lessons this instructor created

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
        return -1;
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

    public int addLesson(LessonManager manager, int courseId, String title, String content) {
        if (!createdCourses.contains(courseId)) return -1; // can only add lessons to own courses
        LessonManager.Lesson lesson = manager.addLesson(courseId, title, content);
        if (lesson != null) {
            createdLessons.add(lesson.getId());
            return lesson.getId();
        }
        return -1;
    }

    public boolean editLesson(LessonManager manager, int courseId, int lessonId, String newTitle, String newContent) {
        if (!createdLessons.contains(lessonId)) return false;
        return manager.editLesson(courseId, lessonId, newTitle, newContent);
    }

    public boolean deleteLesson(LessonManager manager, int courseId, int lessonId) {
        if (!createdLessons.contains(lessonId)) return false;
        boolean deleted = manager.deleteLesson(courseId, lessonId);
        if (deleted) createdLessons.remove(Integer.valueOf(lessonId));
        return deleted;
    }

    public ArrayList<String> viewEnrolledStudents(CourseManager manager, int courseId) {
        if (!createdCourses.contains(courseId)) return null;
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
