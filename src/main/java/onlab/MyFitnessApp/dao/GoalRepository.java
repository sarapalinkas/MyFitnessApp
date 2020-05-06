package onlab.MyFitnessApp.dao;

import onlab.MyFitnessApp.entity.Goal;
import onlab.MyFitnessApp.entity.goaltypes.WorkoutGoal;

import javax.transaction.Transactional;
import java.util.Optional;


public interface GoalRepository extends GoalBaseRepository<Goal> {



}

