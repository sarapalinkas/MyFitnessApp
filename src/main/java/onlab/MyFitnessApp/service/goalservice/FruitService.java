package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.FruitRepository;
import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.goaltypes.FruitGoal;
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
        FruitGoal wg = getFruitGoal();
        wg.setGoalQuantity(fruitGoal.getGoalQuantity());
        int done = 0;
        if(!wg.getActivities().isEmpty())
        {
            for (int i=0; i<wg.getActivities().size(); i++)
            {
                done += wg.getActivities().get(i).getQuantity();
            }
        }
        wg.setHowManyLeft(wg.getGoalQuantity()- done);
        wg.setPercentage((7-wg.getAchievedOnDays().size())*100.0/7);
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
        currentUser.setFruitGoal(null);
        userRepository.save(currentUser);
        fruitRepository.deleteById(id);
    }

    public FruitGoal getFruitGoal() {
        if (currentUser.getFruitGoal() != null)
        {
            Long wgid = currentUser.getFruitGoal().getId();
            return fruitRepository.myFindById(wgid);
        }
        return null;
    }
}
