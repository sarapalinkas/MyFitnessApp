package onlab.MyFitnessApp.service;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.dao.WorkoutRepository;
import onlab.MyFitnessApp.entity.WorkoutGoal;
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


    public void addWorkoutGoal(WorkoutGoal workoutGoal) {
        if (getWorkoutGoal() != null)
        {
            WorkoutGoal ogWorkout = getWorkoutGoal();
            ogWorkout.setFrequency(workoutGoal.getFrequency());
            ogWorkout.setGoalQuantity(workoutGoal.getGoalQuantity());
            for (int i=0; i<ogWorkout.getActivities().size(); i++)
            {
                ogWorkout.setHowManyLeft(ogWorkout.getHowManyLeft() - ogWorkout.getActivities().get(i).getQuantity());
            }
            ogWorkout.setPercentage((ogWorkout.getFrequency()-ogWorkout.getHowManyLeft())*100.0/ogWorkout.getFrequency());
            if(ogWorkout.getPercentage() == 100) ogWorkout.setSucceeded(true);
            workoutRepository.save(ogWorkout);
        }
        else
        {
            Calendar calendar = new GregorianCalendar();
            Date trialTime = new Date();
            calendar.setTime(trialTime);
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            workoutGoal.setCurrentWeek(week);
            workoutGoal.setUser(currentUser);
            workoutGoal.setHowManyLeft(workoutGoal.getFrequency());
            workoutGoal.setSucceeded(false);
            workoutGoal.setPercentage(0);
            currentUser.setWorkoutGoal(workoutRepository.save(workoutGoal));
            userRepository.save(currentUser);
        }

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
            return workoutRepository.getOne(wgid);
        }
        return null;
    }
}
