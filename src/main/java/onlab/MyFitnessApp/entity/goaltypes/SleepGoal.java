package onlab.MyFitnessApp.entity.goaltypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.Goal;
import onlab.MyFitnessApp.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Entity
public class SleepGoal extends Goal {

    @OneToOne
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ElementCollection
    @Temporal(TemporalType.DATE)
    public List<Date> achievedOnDays;


    public SleepGoal() {
    }

    public SleepGoal(Integer quantity, Integer frequency)
    {
        this.setGoalQuantity(quantity);
        this.user = currentUser;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Date> getAchievedOnDays() {
        return achievedOnDays;
    }

    public void setAchievedOnDays(List<Date> achievedOnDays) {
        this.achievedOnDays = achievedOnDays;
    }

    public void addDay(Date day)
    {
        this.achievedOnDays.add(day);
    }
}
