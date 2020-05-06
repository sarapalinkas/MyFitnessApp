package onlab.MyFitnessApp.entity.goaltypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.Goal;
import onlab.MyFitnessApp.entity.User;

import javax.persistence.*;
import java.util.List;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Entity
public class NatureGoal extends Goal {

    private Integer frequency;

    @OneToOne
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;


    public NatureGoal() {
    }

    public NatureGoal(Integer quantity, Integer frequency)
    {
        this.setGoalQuantity(quantity);
        this.frequency = frequency;
        this.user = currentUser;
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

}
