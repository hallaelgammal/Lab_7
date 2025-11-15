
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdallah
 */
public class Student extends User {
    private ArrayList<Integer> enrolledCourses;
    private HashMap<Integer, ArrayList<String>> progress;
    
    public Student(int userId, String username, String email, String passwordHash, ArrayList<Integer> enrolledCourses, HashMap<Integer, ArrayList<String>> progress)
    { super(userId, "student", username, email, passwordHash);
    this.enrolledCourses = enrolledCourses;
    this.progress= progress;
    
    }
    
    
}
