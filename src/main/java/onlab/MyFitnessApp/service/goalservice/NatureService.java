package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.NatureRepository;
import onlab.MyFitnessApp.entity.goaltypes.NatureGoal;
import onlab.MyFitnessApp.entity.goaltypes.WorkoutGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Service
@Transactional
public class NatureService {
    @Autowired
    NatureRepository natureRepository;

    @Autowired
    UserRepository userRepository;

    public void addNatureGoal(NatureGoal natureGoal)
    {
       int year = Calendar.getInstance().get(Calendar.YEAR);
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        String currweek = "" + year + week;
        natureGoal.setCurrentWeek(currweek);
        natureGoal.setActive(true);
        natureGoal.setUser(currentUser);
        natureGoal.setHowManyLeft(natureGoal.getFrequency());
        natureGoal.setSucceeded(false);
        natureGoal.setPercentage(0);
        currentUser.setNatureGoal(natureRepository.save(natureGoal));
        userRepository.save(currentUser);
    }

    public void updateNatureGoal (NatureGoal natureGoal)
    {
        NatureGoal wg = getNatureGoal();
        wg.setFrequency(natureGoal.getFrequency());
        wg.setGoalQuantity(natureGoal.getGoalQuantity());
        wg.setActivities(natureGoal.getActivities());
        int done = 0;
        if(!wg.getActivities().isEmpty())
        {
            for (int i=0; i<wg.getActivities().size(); i++)
            {
                if(wg.getActivities().get(i).getQuantity() >= wg.getGoalQuantity())
                {
                    done++;
                }
            }
        }
        wg.setHowManyLeft(wg.getFrequency()- done);
        wg.setPercentage((wg.getFrequency()-wg.getHowManyLeft())*100.0/wg.getFrequency());
        if(wg.getPercentage() == 100)
        {
            wg.setSucceeded(true);
            currentUser.setMedals(currentUser.getMedals()+1);
        }
        wg.setActive(natureGoal.isActive());
        userRepository.save(currentUser);
        natureRepository.save(wg);

    }

    public void deleteNatureGoal(long id)
    {
        currentUser.setNatureGoal(null);
        userRepository.save(currentUser);
        natureRepository.deleteById(id);
    }

    public NatureGoal getNatureGoal() {
        if (currentUser.getNatureGoal() != null)
        {
            Long wgid = currentUser.getNatureGoal().getId();
            return natureRepository.myFindById(wgid);
        }
        return null;
    }

    public void archiveNature()
    {
        NatureGoal wg = currentUser.getNatureGoal();
        wg.setActive(false);
        updateNatureGoal(wg);
        currentUser.addPastNatureGoal(wg);
        currentUser.setNatureGoal(null);
        userRepository.save(currentUser);

    }
}
