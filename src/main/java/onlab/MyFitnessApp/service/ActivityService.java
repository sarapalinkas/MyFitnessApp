package onlab.MyFitnessApp.service;

import onlab.MyFitnessApp.dao.ActivityRepository;
import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.WorkoutRepository;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.Goal;
import onlab.MyFitnessApp.entity.WorkoutGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Service
@Transactional
public class ActivityService {
    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    WorkoutService workoutService;

    public List<Activity> getActivityByGoal(String goaltype)
    {
        if (goaltype.equals("WorkoutGoal"))
        {
            return activityRepository.findByGoal(goaltype, currentUser.getWorkoutGoal());
        }
        return null;
    }


    public void addActivity(Activity activity)
    {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        activity.setCurrentWeek(calendar.get(Calendar.WEEK_OF_YEAR));
        if(activity.getGoalType().equals("WorkoutGoal"))
        {
            Long wgid = currentUser.getWorkoutGoal().getId();
            WorkoutGoal workoutGoal = workoutRepository.getOne(wgid);
            activity.setGoal(workoutGoal);
            activityRepository.save(activity);
            workoutGoal.addActivity(activity);
            workoutService.addWorkoutGoal(workoutGoal);
        }
    }

    public void deleteActivity(long id)
    {
        Activity activity = activityRepository.findById(id).get();
        if(activity.getGoalType().equals("WorkoutGoal"))
        {
            Long wgid = currentUser.getWorkoutGoal().getId();
            WorkoutGoal workoutGoal = workoutRepository.getOne(wgid);
            workoutGoal.getActivities().remove(activity);
            workoutService.addWorkoutGoal(workoutGoal);
        }
        activityRepository.deleteById(id);

    }



}
