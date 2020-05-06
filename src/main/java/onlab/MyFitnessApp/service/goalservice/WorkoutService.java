package onlab.MyFitnessApp.service.goalservice;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.goaltypes.WorkoutRepository;
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
public class WorkoutService {
    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;


    public void addWorkoutGoal(WorkoutGoal workoutGoal)
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        String currweek = "" + year + week;
        workoutGoal.setCurrentWeek(currweek);
        workoutGoal.setUser(currentUser);
        workoutGoal.setActive(true);
        workoutGoal.setHowManyLeft(workoutGoal.getFrequency());
        workoutGoal.setSucceeded(false);
        workoutGoal.setPercentage(0);
        currentUser.setWorkoutGoal(workoutRepository.save(workoutGoal));
        userRepository.save(currentUser);
    }

    public void updateWorkoutGoal (WorkoutGoal workoutGoal)
    {
        WorkoutGoal wg = workoutRepository.findById(workoutGoal.getId()).get();
        wg.setFrequency(workoutGoal.getFrequency());
        wg.setGoalQuantity(workoutGoal.getGoalQuantity());
        int done = 0;
        if(!wg.getActivities().isEmpty())
        {
            for (int i=0; i<wg.getActivities().size(); i++)
            {
                done += wg.getActivities().get(i).getQuantity();
            }
        }
        wg.setHowManyLeft(wg.getFrequency()- done);
        wg.setPercentage((wg.getFrequency()-wg.getHowManyLeft())*100.0/wg.getFrequency());
        if(wg.getPercentage() == 100)
        {
            wg.setSucceeded(true);
            currentUser.setMedals(currentUser.getMedals()+1);
        }
        wg.setActive(workoutGoal.isActive());
        userRepository.save(currentUser);
        workoutRepository.save(wg);

    }

    public void deleteWorkoutGoal(long id)
    {
        currentUser.setWorkoutGoal(null);
        userRepository.save(currentUser);
        workoutRepository.deleteById(id);
    }

    public WorkoutGoal getWorkoutGoal() {
        if (currentUser.getWorkoutGoal() != null)
        {
            Long wgid = currentUser.getWorkoutGoal().getId();
            WorkoutGoal wg = workoutRepository.myFindById(wgid);
            return wg;
        }
        return null;
    }
}
