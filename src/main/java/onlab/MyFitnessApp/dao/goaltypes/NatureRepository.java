package onlab.MyFitnessApp.dao.goaltypes;

import onlab.MyFitnessApp.dao.GoalBaseRepository;
import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.NatureGoal;
import org.springframework.data.jpa.repository.Query;

public interface NatureRepository extends GoalBaseRepository<NatureGoal> {

    @Query("SELECT g FROM NatureGoal g WHERE g.user = ?1")
    NatureGoal findByUser(User user);

    @Query("SELECT g FROM NatureGoal g WHERE g.id = ?1 AND g.active = true")
    NatureGoal myFindById(Long id);
}
