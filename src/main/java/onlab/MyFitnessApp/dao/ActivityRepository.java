package onlab.MyFitnessApp.dao;

import onlab.MyFitnessApp.entity.Activity;
import onlab.MyFitnessApp.entity.Goal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

    @Query("SELECT activity FROM Activity activity WHERE activity.goalType= ?1 AND activity.goal = ?2 AND activity.currentWeek=?3")
    List<Activity> findByGoal(String goaltype, Goal goal, Integer week);

    @Query("DELETE FROM Activity activity WHERE activity.goalType= ?1 AND activity.goal = ?2")
    void deleteActivity(String goaltype, Goal goal);

}
