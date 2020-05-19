package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.MeditationRepository;
import onlab.MyFitnessApp.entity.goaltypes.MeditationGoal;
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
public class MeditationService {
    @Autowired
    MeditationRepository meditationRepository;

    @Autowired
    UserRepository userRepository;


    public void addMeditationGoal(MeditationGoal meditationGoal)
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        String currweek = "" + year + week;
        meditationGoal.setCurrentWeek(currweek);
        meditationGoal.setActive(true);
        meditationGoal.setUser(currentUser);
        meditationGoal.setHowManyLeft(meditationGoal.getFrequency());
        meditationGoal.setSucceeded(false);
        meditationGoal.setPercentage(0);
        currentUser.setMeditationGoal(meditationRepository.save(meditationGoal));
        userRepository.save(currentUser);
    }

    public void updateMeditationGoal (MeditationGoal meditationGoal)
    {
        MeditationGoal wg = getMeditationGoal();
        wg.setFrequency(meditationGoal.getFrequency());
        wg.setGoalQuantity(meditationGoal.getGoalQuantity());
        wg.setActivities(meditationGoal.getActivities());
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
        wg.setActive(meditationGoal.isActive());
        userRepository.save(currentUser);
        meditationRepository.save(wg);

    }

    public void deleteMeditationGoal(long id)
    {
        currentUser.setMeditationGoal(null);
        userRepository.save(currentUser);
        meditationRepository.deleteById(id);
    }

    public MeditationGoal getMeditationGoal() {
        if (currentUser.getMeditationGoal() != null)
        {
            Long wgid = currentUser.getMeditationGoal().getId();
            return meditationRepository.myFindById(wgid);
        }
        return null;
    }
    public void archiveMeditation()
    {
        MeditationGoal wg = currentUser.getMeditationGoal();
        wg.setActive(false);
        updateMeditationGoal(wg);
        currentUser.addPastMeditationGoal(wg);
        currentUser.setMeditationGoal(null);
        userRepository.save(currentUser);

    }
}
