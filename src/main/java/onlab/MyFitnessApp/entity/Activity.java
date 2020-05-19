package onlab.MyFitnessApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
public class Activity {

    private @Id @GeneratedValue Long id;
    private int currentWeek;
    private int quantity;
    private String goalType;
    @ManyToOne @JsonIgnore @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) private Goal goal;

    public Activity(String goalType, int quantity)
    {
        this.goalType = goalType;
        this.quantity = quantity;
    }
    public Activity() {
    }

    public String getGoalType() {
        return goalType;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
