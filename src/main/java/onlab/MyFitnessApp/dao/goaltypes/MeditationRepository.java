package onlab.MyFitnessApp.dao.goaltypes;

import onlab.MyFitnessApp.dao.GoalBaseRepository;
import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.MeditationGoal;
import org.springframework.data.jpa.repository.Query;

public interface MeditationRepository extends GoalBaseRepository<MeditationGoal> {

    @Query("SELECT g FROM MeditationGoal g WHERE g.user = ?1")
    MeditationGoal findByUser(User user);

    @Query("SELECT g FROM MeditationGoal g WHERE g.id = ?1 AND g.active = true")
    MeditationGoal myFindById(Long id);
}
