package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.ActivityRepository;
import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.VegRepository;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.goaltypes.SleepGoal;
import onlab.MyFitnessApp.entity.goaltypes.VegGoal;
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
public class VegService {
    @Autowired
    VegRepository vegRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public void addVegGoal(VegGoal vegGoal)
    {
       int year = Calendar.getInstance().get(Calendar.YEAR);
       int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
       String currweek = "" + year + week;
        vegGoal.setCurrentWeek(currweek);
        vegGoal.setActive(true);
        vegGoal.setUser(currentUser);
        vegGoal.setHowManyLeft(vegGoal.getGoalQuantity());
        vegGoal.setSucceeded(false);
        vegGoal.setPercentage(0);
        vegGoal.setDaycount(0);
        vegRepository.save(vegGoal);
        currentUser.setVegGoal(vegGoal);
        userRepository.save(currentUser);
    }

    public void updateVegGoal (VegGoal vegGoal)
    {
        VegGoal wg = currentUser.getVegGoal();
        wg.setGoalQuantity(vegGoal.getGoalQuantity());
        wg.setAchievedOnDays(vegGoal.getAchievedOnDays());
        wg.setActivities(vegGoal.getActivities());
        int done = 0;
        if(!wg.getActivities().isEmpty())
        {
            for (int i=0; i<wg.getActivities().size(); i++)
            {
                if(wg.getActivities().get(i).getDay() == Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
                {
                    done += wg.getActivities().get(i).getQuantity();
                    wg.addDay(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
                }
            }
        }
        wg.setHowManyLeft(wg.getGoalQuantity()- done);
        if(wg.getHowManyLeft() <= 0)
        {
            wg.setDaycount(wg.getDaycount()+1);
        }
        double size = wg.getDaycount();
        wg.setPercentage((size/7.0)*100.0);
        if(wg.getPercentage() == 100)
        {
            wg.setSucceeded(true);
            currentUser.setMedals(currentUser.getMedals()+1);
        }
        wg.setActive(vegGoal.isActive());
        userRepository.save(currentUser);
        vegRepository.save(wg);

    }

    public void deleteVegGoal(long id)
    {
        List<Activity> act = activityRepository.findByGoal("Vegetable", currentUser.getVegGoal(),
                Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
        for(int i = 0; i<act.size(); i++)
        {
            activityRepository.delete(act.get(i));

        }
        currentUser.setVegGoal(null);
        userRepository.save(currentUser);
        vegRepository.deleteById(id);

    }

    public VegGoal getVegGoal() {
        if (currentUser.getVegGoal() != null)
        {
            VegGoal sg = currentUser.getVegGoal();
            if(!sg.getAchievedOnDays().contains(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)))
            {
                sg.setHowManyLeft(sg.getGoalQuantity());
            }
            userRepository.save(currentUser);
            vegRepository.save(sg);

            return sg;
        }
        return null;
    }

    public void archiveVegetable()
    {
        VegGoal wg = currentUser.getVegGoal();
        wg.setActive(false);
        updateVegGoal(wg);
        currentUser.addPastVegGoal(wg);
        currentUser.setVegGoal(null);
        userRepository.save(currentUser);

    }
}
