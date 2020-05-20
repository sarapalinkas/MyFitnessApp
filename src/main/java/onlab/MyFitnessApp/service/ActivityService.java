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
        activity.setDay(today);
        if(activity.getGoalType().equals("Workout") && currentUser.getWorkoutGoal()!= null &&
                currentUser.getWorkoutGoal().getHowManyLeft()!=0)
        {
            WorkoutGoal workoutGoal = currentUser.getWorkoutGoal();
            activity.setGoal(workoutGoal);
            activityRepository.save(activity);
            workoutGoal.addActivity(activity);
            workoutService.updateWorkoutGoal(workoutGoal);
        }
        if(activity.getGoalType().equals("Meditation") && currentUser.getMeditationGoal() != null &&
                currentUser.getMeditationGoal().getHowManyLeft()!=0)
        {
            MeditationGoal meditationGoal = currentUser.getMeditationGoal();
            activity.setGoal(meditationGoal);
            activityRepository.save(activity);
            meditationGoal.addActivity(activity);
            meditationService.updateMeditationGoal(meditationGoal);
        }
        if(activity.getGoalType().equals("Nature") && currentUser.getNatureGoal() != null &&
                currentUser.getNatureGoal().getHowManyLeft()!=0)
        {
            NatureGoal natureGoal = currentUser.getNatureGoal();
            activity.setGoal(natureGoal);
            activityRepository.save(activity);
            natureGoal.addActivity(activity);
            natureService.updateNatureGoal(natureGoal);
        }
        if(activity.getGoalType().equals("Sleep") && currentUser.getSleepGoal() != null &&
                currentUser.getSleepGoal().getHowManyLeft()!=0)
        {
            SleepGoal sleepGoal = currentUser.getSleepGoal();
            activity.setGoal(sleepGoal);
            activityRepository.save(activity);
            sleepGoal.addActivity(activity);
            sleepService.updateSleepGoal(sleepGoal);
        }
        if(activity.getGoalType().equals("Fruit") && currentUser.getFruitGoal() != null &&
                currentUser.getFruitGoal().getHowManyLeft()!=0)
        {
            FruitGoal fruitGoal = currentUser.getFruitGoal();
            activity.setGoal(fruitGoal);
            activityRepository.save(activity);
            fruitGoal.addActivity(activity);
            fruitService.updateFruitGoal(fruitGoal);
        }
        if(activity.getGoalType().equals("Vegetable") && currentUser.getVegGoal() != null &&
                currentUser.getVegGoal().getHowManyLeft()!=0)
        {
            VegGoal vegGoal = currentUser.getVegGoal();
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
            WorkoutGoal workoutGoal = currentUser.getWorkoutGoal();
            workoutGoal.getActivities().remove(id);
            workoutService.updateWorkoutGoal(workoutGoal);
        }
        if(activity.getGoalType().equals("Sleep"))
        {
            SleepGoal sleepGoal = currentUser.getSleepGoal();
            sleepGoal.getActivities().remove(id);
            sleepGoal.getAchievedOnDays().remove(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            sleepGoal.setDaycount(sleepGoal.getDaycount() -1);
            sleepService.updateSleepGoal(sleepGoal);
        }
        if(activity.getGoalType().equals("Meditation"))
        {
            MeditationGoal meditationGoal = currentUser.getMeditationGoal();
            meditationGoal.getActivities().remove(id);
            meditationService.updateMeditationGoal(meditationGoal);
        }
        if(activity.getGoalType().equals("Nature"))
        {
            NatureGoal natureGoal = currentUser.getNatureGoal();
            for(int i = 0; i<natureGoal.getActivities().size(); i++)
            {
                if(id == natureGoal.getActivities().get(i).getId())
                {
                    natureGoal.getActivities().remove(natureGoal.getActivities().get(i));
                }
            }
                    //  natureGoal.getActivities().remove(activity);
          //  natureGoal.getActivities().remove(Math.toIntExact(id));
            activityRepository.deleteById(id);
            natureService.updateNatureGoal(natureGoal);
        }
        if(activity.getGoalType().equals("Fruit"))
        {
            FruitGoal fruitGoal = currentUser.getFruitGoal();
            fruitGoal.getActivities().remove(id);
            fruitGoal.getAchievedOnDays().remove(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            fruitGoal.setDaycount(fruitGoal.getDaycount() -1);
            fruitService.updateFruitGoal(fruitGoal);
        }
        if(activity.getGoalType().equals("Vegetable"))
        {
            VegGoal vegGoal = currentUser.getVegGoal();
            vegGoal.getActivities().remove(id);
            vegGoal.getAchievedOnDays().remove(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            vegGoal.setDaycount(vegGoal.getDaycount() -1);
            vegService.updateVegGoal(vegGoal);
        }
        activityRepository.deleteById(id);

    }



}
