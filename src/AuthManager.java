/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.UUID;
/**
 *
 * @author Dell
 */
public class AuthManager {
  private JsonDatabaseManager database=new JsonDatabaseManager();
    public AuthResult login(String email, String password) {
        User user = database.findUserByEmail(email);
        if (user == null) {
            return new AuthResult(false, "User not found");
        }
        if (!PasswordUtil.hash(password).equals(user.getPasswordHash())) {
            return new AuthResult(false, "Incorrect password");
        }
        return new AuthResult(true, "Login successful", user);
    }
 public void logout() {
 
    }
    public AuthResult signup(String username, String email, String password,String role) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new AuthResult(false, "All fields are required");
        }
        if (!email.contains("@")) {
            return new AuthResult(false, "Invalid email format");
        }
        if (database.findUserByEmail(email) != null) {
            return new AuthResult(false, "Email already exists");
        }
        String hashed = PasswordUtil.hash(password);
       String id = UUID.randomUUID().toString();
       User newUser;
    if (role.equalsIgnoreCase("student")) {
        newUser = new Student(id, username, email, hashed);
    } else if(role.equalsIgnoreCase("instructor")) {
        newUser = new Instructor(id, username, email, hashed);
    }
    else
        return new AuthResult(false, "Invalid role");
    database.saveUser(newUser);
        return new AuthResult(true, "Signup successful", newStudent);
    }

}
