package onlab.MyFitnessApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import onlab.MyFitnessApp.entity.goaltypes.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class User {


    private @Id @GeneratedValue Long id;
    private String status;
    private String username;
    private String password;
    private String role;
    private boolean active;
    int medals;

    @OneToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WorkoutGoal workoutGoal;
    @OneToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FruitGoal fruitGoal;
    @OneToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private VegGoal vegGoal;
    @OneToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SleepGoal sleepGoal;
    @OneToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MeditationGoal meditationGoal;
    @OneToOne()
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private NatureGoal natureGoal;

    @OneToMany(fetch = FetchType.EAGER)
    private List<WorkoutGoal> pastWorkoutGoals = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<FruitGoal> pastFruitGoals= new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<VegGoal> pastVegGoals= new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<SleepGoal> pastSleepGoals= new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<MeditationGoal> pastMeditationGoals= new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER)
    private List<NatureGoal> pastNatureGoals= new ArrayList<>();

    public User() {}

    public User(String status) {
        this.status = status;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "USER";
        this.active=true;
        this.medals=0;
    }

    public WorkoutGoal getWorkoutGoal() {
        return workoutGoal;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public void setWorkoutGoal(WorkoutGoal workoutGoal) {
        this.workoutGoal = workoutGoal;
    }

    public FruitGoal getFruitGoal() {
        return fruitGoal;
    }

    public void setFruitGoal(FruitGoal fruitGoal) {
        this.fruitGoal = fruitGoal;
    }

    public VegGoal getVegGoal() {
        return vegGoal;
    }

    public void setVegGoal(VegGoal vegGoal) {
        this.vegGoal = vegGoal;
    }

    public SleepGoal getSleepGoal() {
        return sleepGoal;
    }

    public void setSleepGoal(SleepGoal sleepGoal) {
        this.sleepGoal = sleepGoal;
    }

    public MeditationGoal getMeditationGoal() {
        return meditationGoal;
    }

    public void setMeditationGoal(MeditationGoal meditationGoal) {
        this.meditationGoal = meditationGoal;
    }

    public NatureGoal getNatureGoal() {
        return natureGoal;
    }

    public void setNatureGoal(NatureGoal natureGoal) {
        this.natureGoal = natureGoal;
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

    public void addPastWorkoutGoal(WorkoutGoal workoutGoal)
    {
        pastWorkoutGoals.add(workoutGoal);
    }

    public void addPastFruitGoal(FruitGoal goal)
    {
        pastFruitGoals.add(goal);
    }
    public void addPastVegGoal(VegGoal goal)
    {
        pastVegGoals.add(goal);
    }
    public void addPastSleepGoal(SleepGoal goal)
    {
        pastSleepGoals.add(goal);
    }
    public void addPastNatureGoal(NatureGoal goal)
    {
        pastNatureGoals.add(goal);
    }
    public void addPastMeditationGoal(MeditationGoal goal)
    {
        pastMeditationGoals.add(goal);
    }

    public List<WorkoutGoal> getPastWorkoutGoals() {
        return pastWorkoutGoals;
    }

    public void setPastWorkoutGoals(List<WorkoutGoal> pastWorkoutGoals) {
        this.pastWorkoutGoals = pastWorkoutGoals;
    }

    public List<FruitGoal> getPastFruitGoals() {
        return pastFruitGoals;
    }

    public void setPastFruitGoals(List<FruitGoal> pastFruitGoals) {
        this.pastFruitGoals = pastFruitGoals;
    }

    public List<VegGoal> getPastVegGoals() {
        return pastVegGoals;
    }

    public void setPastVegGoals(List<VegGoal> pastVegGoals) {
        this.pastVegGoals = pastVegGoals;
    }

    public List<SleepGoal> getPastSleepGoals() {
        return pastSleepGoals;
    }

    public void setPastSleepGoals(List<SleepGoal> pastSleepGoals) {
        this.pastSleepGoals = pastSleepGoals;
    }

    public List<MeditationGoal> getPastMeditationGoals() {
        return pastMeditationGoals;
    }

    public void setPastMeditationGoals(List<MeditationGoal> pastMeditationGoals) {
        this.pastMeditationGoals = pastMeditationGoals;
    }

    public List<NatureGoal> getPastNatureGoals() {
        return pastNatureGoals;
    }

    public void setPastNatureGoals(List<NatureGoal> pastNatureGoals) {
        this.pastNatureGoals = pastNatureGoals;
    }
}