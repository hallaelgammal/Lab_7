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

public class LessonManager {

    public static class Lesson {
        private int id;
        private String title;
        private String content;

        public Lesson(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() { //3shan lama agy a3ml print
            return id + ": " + title + " - " + content;
        }
    }

    
    private final List<CourseLessons> coursesLessons = new ArrayList<>();


    private static class CourseLessons {
        int courseId;
        List<Lesson> lessons = new ArrayList<>();
        int nextLessonId = 1;

        CourseLessons(int courseId) {
            this.courseId = courseId;
        }
    }

    private CourseLessons getCourseLessons(int courseId) {
        for (CourseLessons cl : coursesLessons) {
            if (cl.courseId == courseId) return cl;
        }
        return null;
    }

    
    public Lesson addLesson(int courseId, String title, String content) {
        if (title == null || title.trim().isEmpty()) return null;

        CourseLessons cl = getCourseLessons(courseId);
        if (cl == null) {
            cl = new CourseLessons(courseId);
            coursesLessons.add(cl);
        }

        Lesson lesson = new Lesson(cl.nextLessonId++, title, content);
        cl.lessons.add(lesson);
        return lesson;
    }

    public boolean editLesson(int courseId, int lessonId, String newTitle, String newContent) {
        CourseLessons cl = getCourseLessons(courseId);
        if (cl == null) return false;

        for (Lesson l : cl.lessons) {
            if (l.getId() == lessonId) {
                if (newTitle != null && !newTitle.trim().isEmpty()) l.setTitle(newTitle);
                if (newContent != null && !newContent.trim().isEmpty()) l.setContent(newContent);
                return true;
            }
        }
        return false;
    }

    public boolean deleteLesson(int courseId, int lessonId) {
        CourseLessons cl = getCourseLessons(courseId);
        if (cl == null) return false;

        return cl.lessons.removeIf(l -> l.getId() == lessonId);
    }

 
    public Lesson getLesson(int courseId, int lessonId) {
        CourseLessons cl = getCourseLessons(courseId);
        if (cl == null) return null;

        for (Lesson l : cl.lessons) {
            if (l.getId() == lessonId) return l;
        }
        return null;
    }

    public List<Lesson> getAllLessons(int courseId) {
        CourseLessons cl = getCourseLessons(courseId);
        if (cl == null) return new ArrayList<>();
        return cl.lessons;
    }
}

