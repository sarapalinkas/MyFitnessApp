package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.VegRepository;
import onlab.MyFitnessApp.entity.goaltypes.VegGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@Service
@Transactional
public class VegService {
    @Autowired
    VegRepository vegRepository;

    @Autowired
    UserRepository userRepository;

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
        currentUser.setVegGoal(vegRepository.save(vegGoal));
        userRepository.save(currentUser);
    }

    public void updateVegGoal (VegGoal vegGoal)
    {
        VegGoal wg = getVegGoal();
        wg.setGoalQuantity(vegGoal.getGoalQuantity());
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
        wg.setActive(vegGoal.isActive());
        userRepository.save(currentUser);
        vegRepository.save(wg);

    }

    public void deleteVegGoal(long id)
    {
        currentUser.setVegGoal(null);
        userRepository.save(currentUser);
        vegRepository.deleteById(id);
    }

    public VegGoal getVegGoal() {
        if (currentUser.getVegGoal() != null)
        {
            Long wgid = currentUser.getVegGoal().getId();
            return vegRepository.myFindById(wgid);
        }
        return null;
    }
}
