package onlab.MyFitnessApp.dao.goaltypes;

import onlab.MyFitnessApp.dao.GoalBaseRepository;
import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.VegGoal;
import onlab.MyFitnessApp.entity.goaltypes.WorkoutGoal;
import org.springframework.data.jpa.repository.Query;

public interface VegRepository extends GoalBaseRepository<VegGoal> {

    @Query("SELECT g FROM VegGoal g WHERE g.user = ?1")
    VegGoal findByUser(User user);

    @Query("SELECT g FROM VegGoal g WHERE g.id = ?1 AND g.active = true")
    VegGoal myFindById(Long id);
}