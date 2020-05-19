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
    public List<WorkoutGoal> getPastWorkoutGoals()
    {
        return statService.getPastWorkoutGoals();
    }

    @GetMapping(path = "/sleepgoal")
    public List<SleepGoal> getPastSleepGoals()
    {
        return statService.getPastSleepGoals();
    }
    @GetMapping(path = "/fruitgoal")
    public List<FruitGoal> getPastFruitGoals()
    {
        return statService.getPastFruitGoals();
    }
    @GetMapping(path = "/veggoal")
    public List<VegGoal> getPastVegGoals()
    {
        return statService.getPastVegGoals();
    }
    @GetMapping(path = "/meditationgoal")
    public List<MeditationGoal> getPastMeditationGoals()
    {
        return statService.getPastMeditationGoals();
    }
    @GetMapping(path = "/naturegoal")
    public List<NatureGoal> getPastNatureGoals()
    {
        return statService.getPastNatureGoals();
    }
}
