package onlab.MyFitnessApp.dao;

import onlab.MyFitnessApp.entity.Goal;

import javax.transaction.Transactional;

@Transactional
public interface GoalRepository extends GoalBaseRepository<Goal> {

}

