package onlab.MyFitnessApp.controller;

import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.goaltypes.*;
import onlab.MyFitnessApp.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/stat" })
public class StatController {

    @Autowired
    private StatService statService;

    @GetMapping(path = "/{goaltype}")
    public List<Activity> getPastActivitys(@PathVariable("goaltype") String goaltype)
    {
        return statService.getPastActivitys(goaltype);
    }

    @GetMapping(path = "/workoutgoal")
    public Set<WorkoutGoal> getPastWorkoutGoals()
    {
        return statService.getPastWorkoutGoals();
    }

    @GetMapping(path = "/sleepgoal")
    public Set<SleepGoal> getPastSleepGoals()
    {
        return statService.getPastSleepGoals();
    }
    @GetMapping(path = "/fruitgoal")
    public Set<FruitGoal> getPastFruitGoals()
    {
        return statService.getPastFruitGoals();
    }
    @GetMapping(path = "/veggoal")
    public Set<VegGoal> getPastVegGoals()
    {
        return statService.getPastVegGoals();
    }
    @GetMapping(path = "/meditationgoal")
    public Set<MeditationGoal> getPastMeditationGoals()
    {
        return statService.getPastMeditationGoals();
    }
    @GetMapping(path = "/naturegoal")
    public Set<NatureGoal> getPastNatureGoals()
    {
        return statService.getPastNatureGoals();
    }
}
