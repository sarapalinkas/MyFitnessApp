package onlab.MyFitnessApp.controller;

import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/activities" })
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @GetMapping(produces = "application/json", path = "/{goaltype}")
    public List<Activity> getAllActivitiesByGoaltype(@PathVariable("goaltype") String goaltype)
    {
        return activityService.getActivityByGoal(goaltype);
    }

    @DeleteMapping(path = { "/{id}" })
    public void deleteActivity(@PathVariable("id") long id) {
        activityService.deleteActivity(id);
    }

    @PostMapping
    public void addActivity(@RequestBody Activity activity) {
        activityService.addActivity(activity);
    }
}
