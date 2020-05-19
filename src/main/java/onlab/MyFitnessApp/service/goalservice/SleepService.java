package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.ActivityRepository;
import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.SleepRepository;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.goaltypes.SleepGoal;
import onlab.MyFitnessApp.entity.goaltypes.WorkoutGoal;
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
public class SleepService {
    @Autowired
    SleepRepository sleepRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public void addSleepGoal(SleepGoal sleepGoal)
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
       String currweek = "" + year + week;
        sleepGoal.setCurrentWeek(currweek);
        sleepGoal.setActive(true);
        sleepGoal.setUser(currentUser);
        sleepGoal.setHowManyLeft(sleepGoal.getGoalQuantity());
        sleepGoal.setSucceeded(false);
        sleepGoal.setPercentage(0);
        currentUser.setSleepGoal(sleepRepository.save(sleepGoal));
        userRepository.save(currentUser);
    }

    public void updateSleepGoal (SleepGoal sleepGoal)
    {
        SleepGoal wg = sleepRepository.findById(sleepGoal.getId()).get();
        wg.setGoalQuantity(sleepGoal.getGoalQuantity());
        wg.setActivities(sleepGoal.getActivities());
        int done = 0;
        if(!wg.getActivities().isEmpty())
        {
            for (int i=0; i<wg.getActivities().size(); i++)
            {
                done += wg.getActivities().get(i).getQuantity();
            }
        }
        wg.setHowManyLeft(wg.getGoalQuantity()- done);
        if(wg.getHowManyLeft() <= 0)
        {
            wg.addDay(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        }
        double size = wg.getAchievedOnDays().size();
        wg.setPercentage((size/7.0)*100.0);
        if(wg.getPercentage() == 100)
        {
            wg.setSucceeded(true);
            currentUser.setMedals(currentUser.getMedals()+1);
        }
        wg.setActive(sleepGoal.isActive());
        userRepository.save(currentUser);
        sleepRepository.save(wg);

    }

    public void deleteSleepGoal(long id)
    {
        List<Activity> act = activityRepository.findByGoal("Sleep", currentUser.getSleepGoal());
        for(int i = 0; i<act.size(); i++)
        {
            activityRepository.delete(act.get(i));

        }
        currentUser.setSleepGoal(null);
        userRepository.save(currentUser);
        sleepRepository.deleteById(id);
    }

    public SleepGoal getSleepGoal() {
        if (currentUser.getSleepGoal() != null)
        {
            SleepGoal sg = currentUser.getSleepGoal();
            if(!sg.getAchievedOnDays().contains(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)))
            {
                sg.setHowManyLeft(sg.getGoalQuantity());
            }
            userRepository.save(currentUser);
            sleepRepository.save(sg);

            return sg;
        }
        return null;
    }

    public void archiveSleep()
    {
        SleepGoal wg = currentUser.getSleepGoal();
        wg.setActive(false);
        updateSleepGoal(wg);
        currentUser.addPastSleepGoal(wg);
        currentUser.setSleepGoal(null);
        userRepository.save(currentUser);

    }
}