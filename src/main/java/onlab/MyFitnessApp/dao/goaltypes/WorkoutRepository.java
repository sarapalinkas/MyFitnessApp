package onlab.MyFitnessApp.dao.goaltypes;

import onlab.MyFitnessApp.dao.GoalBaseRepository;
import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.VegGoal;
import onlab.MyFitnessApp.entity.goaltypes.WorkoutGoal;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface WorkoutRepository extends GoalBaseRepository<WorkoutGoal> {

    @Query("SELECT wg FROM WorkoutGoal wg WHERE wg.user = ?1 AND wg.active = true AND wg.currentWeek =?2")
    WorkoutGoal findByUser(User user, int week);

    @Query("SELECT g FROM WorkoutGoal g WHERE g.id = ?1 AND g.active = true")
    WorkoutGoal myFindById(Long id);
    //Optional<WorkoutGoal> findById(Long id);

}