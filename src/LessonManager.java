/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7.me;

/**
 *
 * @author Gehad
 */
import java.util.ArrayList;
import java.util.List;

public class LessonManager {

    private List<Lesson> lessons = new ArrayList<>(); // stores all lessons across all courses

    public static class Lesson {
        private int lessonId;
        private int courseId;          // which course this lesson belongs to
        private String title;
        private String content;
        private String[] resources;    

        public Lesson(int lessonId, int courseId, String title, String content, String[] resources) {
            this.lessonId = lessonId;
            this.courseId = courseId;
            this.title = title;
            this.content = content;
            this.resources = resources;
        }

        public int getLessonId() { return lessonId; }
        public int getCourseId() { return courseId; }
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public String[] getResources() { return resources; }

        public void setTitle(String title) { this.title = title; }
        public void setContent(String content) { this.content = content; }
        public void setResources(String[] resources) { this.resources = resources; }

        @Override
        public String toString() {
            return lessonId + " | Course " + courseId + ": " + title;
        }
    }

  
    // Generate next lesson ID
    private int getNextLessonId() {
        return lessons.size() + 1;
    }

    // Add a lesson to a course
    public Lesson addLesson(int courseId, String title, String content, String[] resources) {
        if (title == null || title.isEmpty()) return null;
        Lesson lesson = new Lesson(getNextLessonId(), courseId, title, content, resources);
        lessons.add(lesson);
        return lesson;
    }

    // Edit a lesson
    public boolean editLesson(int lessonId, String newTitle, String newContent, String[] newResources) {
        Lesson l = getLesson(lessonId);
        if (l == null) return false;
        if (newTitle != null && !newTitle.isEmpty()) l.setTitle(newTitle);
        if (newContent != null && !newContent.isEmpty()) l.setContent(newContent);
        if (newResources != null) l.setResources(newResources);
        return true;
    }

    // Delete a lesson
    public boolean deleteLesson(int lessonId) {
        Lesson l = getLesson(lessonId);
        if (l == null) return false;
        lessons.remove(l);
        return true;
    }

    // Get lesson by ID
    public Lesson getLesson(int lessonId) {
        for (Lesson l : lessons) {
            if (l.getLessonId() == lessonId) return l;
        }
        return null;
    }

    // Fetch lessons by course
    public List<Lesson> getLessonsByCourse(int courseId) {
        List<Lesson> courseLessons = new ArrayList<>();
        for (Lesson l : lessons) {
            if (l.getCourseId() == courseId) courseLessons.add(l);
        }
        return courseLessons;
    }

    // Fetch all lessons
    public List<Lesson> getAllLessons() {
        return lessons;
    }
}
