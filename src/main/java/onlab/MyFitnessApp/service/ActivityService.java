package onlab.MyFitnessApp.service;

import onlab.MyFitnessApp.dao.ActivityRepository;
import onlab.MyFitnessApp.dao.goaltypes.*;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.goaltypes.*;
import onlab.MyFitnessApp.service.goalservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Service
public class ActivityService {
    @Autowired
    WorkoutRepository workoutRepository;
    @Autowired
    SleepRepository sleepRepository;
    @Autowired
    MeditationRepository meditationRepository;
    @Autowired
    NatureRepository natureRepository;
    @Autowired
    FruitRepository fruitRepository;
    @Autowired
    VegRepository vegRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    WorkoutService workoutService;
    @Autowired
    SleepService sleepService;
    @Autowired
    MeditationService meditationService;
    @Autowired
    NatureService natureService;
    @Autowired
    FruitService fruitService;
    @Autowired
    VegService vegService;



    public List<Activity> getActivityByGoal(String goaltype)
    {
        if (goaltype.equals("Workout"))
        {
            return activityRepository.findByGoal(goaltype, currentUser.getWorkoutGoal());
        }
        if (goaltype.equals("Sleep"))
        {
            return activityRepository.findByGoal(goaltype, currentUser.getSleepGoal());
        }
        if (goaltype.equals("Meditation"))
        {
            return activityRepository.findByGoal(goaltype, currentUser.getMeditationGoal());
        }
        if (goaltype.equals("Nature"))
        {
            return activityRepository.findByGoal(goaltype, currentUser.getNatureGoal());
        }
        if (goaltype.equals("Fruit"))
        {
            return activityRepository.findByGoal(goaltype, currentUser.getFruitGoal());
        }
        if (goaltype.equals("Vegetable"))
        {
            return activityRepository.findByGoal(goaltype, currentUser.getVegGoal());
        }
        return null;
    }


    public void addActivity(Activity activity)
    {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        activity.setCurrentWeek(calendar.get(Calendar.WEEK_OF_YEAR));
        int today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if(activity.getGoalType().equals("Workout") && currentUser.getWorkoutGoal()!= null &&
                currentUser.getWorkoutGoal().getHowManyLeft()!=0)
        {
            WorkoutGoal workoutGoal = workoutService.getWorkoutGoal();
            activity.setGoal(workoutGoal);
            activityRepository.save(activity);
            workoutGoal.addActivity(activity);
            workoutService.updateWorkoutGoal(workoutGoal);
        }
        if(activity.getGoalType().equals("Meditation") && currentUser.getMeditationGoal() != null &&
                currentUser.getMeditationGoal().getHowManyLeft()!=0)
        {
            Long wgid = currentUser.getMeditationGoal().getId();
            MeditationGoal meditationGoal = meditationRepository.myFindById(wgid);
            activity.setGoal(meditationGoal);
            activityRepository.save(activity);
            meditationGoal.addActivity(activity);
            meditationService.updateMeditationGoal(meditationGoal);
        }
        if(activity.getGoalType().equals("Nature") && currentUser.getNatureGoal() != null &&
                currentUser.getNatureGoal().getHowManyLeft()!=0)
        {
            Long wgid = currentUser.getNatureGoal().getId();
            NatureGoal natureGoal = natureRepository.myFindById(wgid);
            activity.setGoal(natureGoal);
            activityRepository.save(activity);
            natureGoal.addActivity(activity);
            natureService.updateNatureGoal(natureGoal);
        }
        if(activity.getGoalType().equals("Sleep") && currentUser.getSleepGoal() != null &&
                currentUser.getSleepGoal().getHowManyLeft()!=0)
        {
            Long wgid = currentUser.getSleepGoal() .getId();
            SleepGoal sleepGoal = sleepRepository.myFindById(wgid);
            activity.setGoal(sleepGoal);
            activityRepository.save(activity);
            sleepGoal.addActivity(activity);
            sleepService.updateSleepGoal(sleepGoal);
        }
        if(activity.getGoalType().equals("Fruit") && currentUser.getFruitGoal() != null &&
                currentUser.getFruitGoal().getHowManyLeft()!=0)
        {
            Long wgid = currentUser.getFruitGoal().getId();
            FruitGoal fruitGoal = fruitRepository.myFindById(wgid);
            activity.setGoal(fruitGoal);
            activityRepository.save(activity);
            fruitGoal.addActivity(activity);
            fruitService.updateFruitGoal(fruitGoal);
        }
        if(activity.getGoalType().equals("Vegetable") && currentUser.getVegGoal() != null &&
                currentUser.getVegGoal().getHowManyLeft()!=0)
        {
            Long wgid = currentUser.getVegGoal().getId();
            VegGoal vegGoal = vegRepository.myFindById(wgid);
            activity.setGoal(vegGoal);
            activityRepository.save(activity);
            vegGoal.addActivity(activity);
            vegService.updateVegGoal(vegGoal);
        }
    }

    public void deleteActivity(long id)
    {
        Activity activity = activityRepository.findById(id).get();
        if(activity.getGoalType().equals("Workout"))
        {
            Long wgid = currentUser.getWorkoutGoal().getId();
            WorkoutGoal workoutGoal = workoutRepository.findById(wgid).get();
            workoutGoal.getActivities().remove(activity);
            workoutService.updateWorkoutGoal(workoutGoal);
        }
        if(activity.getGoalType().equals("Sleep"))
        {
            Long wgid = currentUser.getSleepGoal() .getId();
            SleepGoal sleepGoal = sleepRepository.myFindById(wgid);
            sleepGoal.getActivities().remove(activity);
            sleepGoal.getAchievedOnDays().remove(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            sleepService.updateSleepGoal(sleepGoal);
        }
        if(activity.getGoalType().equals("Meditation"))
        {
            Long wgid = currentUser.getMeditationGoal().getId();
            MeditationGoal meditationGoal = meditationRepository.myFindById(wgid);
            meditationGoal.getActivities().remove(activity);
            meditationService.updateMeditationGoal(meditationGoal);
        }
        if(activity.getGoalType().equals("Nature"))
        {
            Long wgid = currentUser.getNatureGoal().getId();
            NatureGoal natureGoal = natureRepository.myFindById(wgid);
            natureGoal.getActivities().remove(activity);
            natureService.updateNatureGoal(natureGoal);
        }
        if(activity.getGoalType().equals("Fruit"))
        {
            Long wgid = currentUser.getFruitGoal().getId();
            FruitGoal fruitGoal = fruitRepository.myFindById(wgid);
            fruitGoal.getActivities().remove(activity);
            fruitGoal.getAchievedOnDays().remove(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            fruitService.updateFruitGoal(fruitGoal);
        }
        if(activity.getGoalType().equals("Vegetable"))
        {
            Long wgid = currentUser.getWorkoutGoal().getId();
            VegGoal vegGoal = vegRepository.myFindById(wgid);
            vegGoal.getActivities().remove(activity);
            vegGoal.getAchievedOnDays().remove(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            vegService.updateVegGoal(vegGoal);
        }
        activityRepository.deleteById(id);

    }



}
