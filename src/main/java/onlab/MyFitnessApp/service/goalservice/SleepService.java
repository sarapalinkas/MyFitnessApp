package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.SleepRepository;
import onlab.MyFitnessApp.entity.goaltypes.SleepGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Service
@Transactional
public class SleepService {
    @Autowired
    SleepRepository sleepRepository;

    @Autowired
    UserRepository userRepository;

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
        SleepGoal wg = getSleepGoal();
        wg.setGoalQuantity(sleepGoal.getGoalQuantity());
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
        wg.setActive(sleepGoal.isActive());
        userRepository.save(currentUser);
        sleepRepository.save(wg);

    }

    public void deleteSleepGoal(long id)
    {
        currentUser.setSleepGoal(null);
        userRepository.save(currentUser);
        sleepRepository.deleteById(id);
    }

    public SleepGoal getSleepGoal() {
        if (currentUser.getSleepGoal() != null)
        {
            Long wgid = currentUser.getSleepGoal().getId();
            return sleepRepository.myFindById(wgid);
        }
        return null;
    }
}