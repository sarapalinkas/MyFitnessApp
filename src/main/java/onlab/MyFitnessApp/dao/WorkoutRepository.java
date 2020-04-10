package onlab.MyFitnessApp.dao;

import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.WorkoutGoal;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface WorkoutRepository extends GoalBaseRepository<WorkoutGoal> {

    @Query("SELECT wg FROM WorkoutGoal wg WHERE wg.user = ?1")
    WorkoutGoal findByUser(User user);
}