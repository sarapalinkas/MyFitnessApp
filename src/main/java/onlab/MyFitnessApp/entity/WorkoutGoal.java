package onlab.MyFitnessApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import java.util.List;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Entity
public class WorkoutGoal extends Goal {

    private @Id @GeneratedValue Long id;
    private Integer frequency;

    @OneToOne
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Activity> activities;

    public WorkoutGoal() {
    }

    public WorkoutGoal(Integer quantity, Integer frequency)
    {
        this.setGoalQuantity(quantity);
        this.frequency = frequency;
        this.user = currentUser;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Long getId() {
        return this.id;
    }
}
