package onlab.MyFitnessApp.service;

import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.goaltypes.*;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Service
public class StatService {

    public List<WorkoutGoal> getPastWorkoutGoals(){
        return currentUser.getPastWorkoutGoals();
    }

    public List<SleepGoal> getPastSleepGoals(){
        return currentUser.getPastSleepGoals();
    }
    public List<FruitGoal> getPastFruitGoals(){
        return currentUser.getPastFruitGoals();
    }
    public List<VegGoal> getPastVegGoals(){
        return currentUser.getPastVegGoals();
    }
    public List<MeditationGoal> getPastMeditationGoals(){
        return currentUser.getPastMeditationGoals();
    }
    public List<NatureGoal> getPastNatureGoals(){
        return currentUser.getPastNatureGoals();
    }

    public List<Activity> getPastActivitys(String goaltype)
    {
        List<Activity> activities = new ArrayList<>();
        if (goaltype.equals("Workout"))
        {
            for(WorkoutGoal goal : currentUser.getPastWorkoutGoals()){
                for(int j = 0; j< goal.getActivities().size(); j++)
                {
                    activities.add(goal.getActivities().get(j));
                }
            }

        }
        else if (goaltype.equals("Sleep"))
        {
            for(SleepGoal goal : currentUser.getPastSleepGoals()){
                for(int j = 0; j< goal.getActivities().size(); j++)
                {
                    activities.add(goal.getActivities().get(j));
                }
            }
        }
        else if (goaltype.equals("Meditation"))
        {
            for(MeditationGoal goal : currentUser.getPastMeditationGoals()){
                for(int j = 0; j< goal.getActivities().size(); j++)
                {
                    activities.add(goal.getActivities().get(j));
                }
            }
        }
        else if (goaltype.equals("Nature"))
        {
            for(NatureGoal goal : currentUser.getPastNatureGoals()){
                for(int j = 0; j< goal.getActivities().size(); j++)
                {
                    activities.add(goal.getActivities().get(j));
                }
            }
        }
        else if (goaltype.equals("Fruit"))
        {
            for(FruitGoal goal : currentUser.getPastFruitGoals()){
                for(int j = 0; j< goal.getActivities().size(); j++)
                {
                    activities.add(goal.getActivities().get(j));
                }
            }
        }
        else if (goaltype.equals("Vegetable"))
        {
            for(VegGoal goal : currentUser.getPastVegGoals()){
                for(int j = 0; j< goal.getActivities().size(); j++)
                {
                    activities.add(goal.getActivities().get(j));
                }
            }
        }
        return activities;
    }

}
