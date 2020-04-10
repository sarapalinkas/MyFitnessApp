package onlab.MyFitnessApp.entity;

import javax.persistence.*;

@Inheritance
@Entity
public abstract class Goal {

    private @Id @GeneratedValue Long id;
    protected Integer goalQuantity;
    private double percentage;
    private Integer howManyLeft;
    private int currentWeek;
    private Boolean isSucceeded;

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

    public Integer getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(Integer currentWeek) {
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
