package onlab.MyFitnessApp.dao.goaltypes;

import onlab.MyFitnessApp.dao.GoalBaseRepository;
import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.SleepGoal;
import onlab.MyFitnessApp.entity.goaltypes.WorkoutGoal;
import org.springframework.data.jpa.repository.Query;

public interface SleepRepository extends GoalBaseRepository<SleepGoal> {

    @Query("SELECT wg FROM SleepGoal wg WHERE wg.user = ?1 AND wg.active = true AND wg.currentWeek =?2")
    SleepGoal findByUser(User user, int week);

    @Query("SELECT g FROM SleepGoal g WHERE g.id = ?1 AND g.active = true")
    SleepGoal myFindById(Long id);
}
