package onlab.MyFitnessApp.controller;

import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.WorkoutGoal;
import onlab.MyFitnessApp.service.MyUserDetailsService;
import onlab.MyFitnessApp.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/workoutgoals" })
public class MainController {

    @Autowired
    private WorkoutService workoutService;



    public MainController(WorkoutService workoutService)
    {
        this.workoutService = workoutService;
    }

    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public User validateLogin() {
        return new User("User successfully authenticated");
    }

    @GetMapping(produces = "application/json")
    public WorkoutGoal getWorkoutgoal() {
        return workoutService.getWorkoutGoal();
    }

    @DeleteMapping(path = { "/{id}" })
    public void deleteWorkoutGoal(@PathVariable("id") long id) {
        workoutService.deleteWorkoutGoal(id);
    }

    @PostMapping
    public void addWorkoutGoal(@RequestBody WorkoutGoal workoutGoal) {
        workoutService.addWorkoutGoal(workoutGoal);
    }

}

