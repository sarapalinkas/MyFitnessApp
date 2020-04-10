package onlab.MyFitnessApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class User {


    private @Id @GeneratedValue Long id;
    private String status;
    private String username;
    private String password;
    private String role;
    private boolean active;

    @OneToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WorkoutGoal workoutGoal;

    public User() {}

    public User(String status) {
        this.status = status;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "USER";
        this.active=true;
    }

    public WorkoutGoal getWorkoutGoal() {
        return workoutGoal;
    }

    public void setWorkoutGoal(WorkoutGoal workoutGoal) {
        this.workoutGoal = workoutGoal;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}