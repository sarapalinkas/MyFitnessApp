package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.ActivityRepository;
import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.FruitRepository;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.goaltypes.FruitGoal;
import onlab.MyFitnessApp.entity.goaltypes.SleepGoal;
import onlab.MyFitnessApp.entity.goaltypes.WorkoutGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Service
@Transactional
public class FruitService {
    @Autowired
    FruitRepository fruitRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public void addFruitGoal(FruitGoal fruitGoal)
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        String currweek = "" + year + week;
        fruitGoal.setCurrentWeek(currweek);
        fruitGoal.setActive(true);
        fruitGoal.setUser(currentUser);
        fruitGoal.setHowManyLeft(fruitGoal.getGoalQuantity());
        fruitGoal.setSucceeded(false);
        fruitGoal.setPercentage(0);
        currentUser.setFruitGoal(fruitRepository.save(fruitGoal));
        userRepository.save(currentUser);
    }

    public void updateFruitGoal (FruitGoal fruitGoal)
    {
        FruitGoal wg = fruitRepository.findById(fruitGoal.getId()).get();
        wg.setGoalQuantity(fruitGoal.getGoalQuantity());
        wg.setActivities(fruitGoal.getActivities());
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
        wg.setActive(fruitGoal.isActive());
        userRepository.save(currentUser);
        fruitRepository.save(wg);

    }

    public void deleteFruitGoal(long id)
    {
        List<Activity> act = activityRepository.findByGoal("Fruit", currentUser.getFruitGoal());
        for(int i = 0; i<act.size(); i++)
        {
            activityRepository.delete(act.get(i));

        }
        currentUser.setFruitGoal(null);
        userRepository.save(currentUser);
        fruitRepository.deleteById(id);
    }

    public FruitGoal getFruitGoal() {
        if (currentUser.getFruitGoal() != null)
        {
            FruitGoal sg = currentUser.getFruitGoal();
            if(!sg.getAchievedOnDays().contains(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)))
            {
                sg.setHowManyLeft(sg.getGoalQuantity());
            }
            userRepository.save(currentUser);
            fruitRepository.save(sg);

            return sg;
        }
        return null;
    }

    public void archiveFruit()
    {
        FruitGoal wg = currentUser.getFruitGoal();
        wg.setActive(false);
        updateFruitGoal(wg);
        currentUser.addPastFruitGoal(wg);
        currentUser.setFruitGoal(null);
        userRepository.save(currentUser);

    }
}
