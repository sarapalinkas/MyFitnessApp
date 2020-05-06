package onlab.MyFitnessApp.dao.goaltypes;

import onlab.MyFitnessApp.dao.GoalBaseRepository;
import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.SleepGoal;
import org.springframework.data.jpa.repository.Query;

public interface SleepRepository extends GoalBaseRepository<SleepGoal> {

    @Query("SELECT g FROM SleepGoal g WHERE g.user = ?1")
    SleepGoal findByUser(User user);

    @Query("SELECT g FROM SleepGoal g WHERE g.id = ?1 AND g.active = true")
    SleepGoal myFindById(Long id);
}
