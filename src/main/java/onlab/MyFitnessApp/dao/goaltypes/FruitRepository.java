package onlab.MyFitnessApp.dao.goaltypes;

import onlab.MyFitnessApp.dao.GoalBaseRepository;
import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.FruitGoal;
import org.springframework.data.jpa.repository.Query;

public interface FruitRepository extends GoalBaseRepository<FruitGoal> {

    @Query("SELECT g FROM FruitGoal g WHERE g.user = ?1")
    FruitGoal findByUser(User user);

    @Query("SELECT g FROM FruitGoal g WHERE g.id = ?1 AND g.active = true")
    FruitGoal myFindById(Long id);
}