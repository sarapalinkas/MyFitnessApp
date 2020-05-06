package onlab.MyFitnessApp.entity;

import javax.persistence.*;
import java.util.List;

@Inheritance
@Entity
public abstract class Goal {

    private @Id @GeneratedValue Long id;
    protected Integer goalQuantity;
    private double percentage;
    private Integer howManyLeft;
    private String currentWeek;
    private boolean active;
    private Boolean isSucceeded;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Activity> activities;

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }


    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Integer getGoalQuantity() {
        return goalQuantity;
    }

    public void setGoalQuantity(Integer goalQuantity) {
        this.goalQuantity = goalQuantity;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public Long getId() {
        return id;
    }

    public Integer getHowManyLeft() {
        return howManyLeft;
    }

    public void setHowManyLeft(Integer howManyLeft) {
        this.howManyLeft = howManyLeft;
    }

    public Boolean getSucceeded() {
        return isSucceeded;
    }

    public void setSucceeded(Boolean succeeded) {
        isSucceeded = succeeded;
    }
}
