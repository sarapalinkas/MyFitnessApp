package onlab.MyFitnessApp.entity.goaltypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.Goal;
import onlab.MyFitnessApp.entity.User;

import javax.persistence.*;
import java.util.*;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Entity
public class FruitGoal extends Goal {
    @OneToOne
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    public Set<Integer> achievedOnDays = new HashSet<>();

    public Integer daycount;

    public FruitGoal() {
    }

    public FruitGoal(Integer quantity, Integer frequency)
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

    public Set<Integer> getAchievedOnDays() {
        return achievedOnDays;
    }

    public void setAchievedOnDays(Set<Integer> achievedOnDays) {
        this.achievedOnDays = achievedOnDays;
    }

    public void addDay(Integer day) { this.achievedOnDays.add(day);}

    @JsonIgnore
    public int getDaycount() {
        return daycount;
    }

    public void setDaycount(int daycount) {
        this.daycount = daycount;
    }
}