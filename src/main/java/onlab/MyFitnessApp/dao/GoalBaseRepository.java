package onlab.MyFitnessApp.dao;

import onlab.MyFitnessApp.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GoalBaseRepository <T extends Goal>
        extends CrudRepository<T, Long>, JpaRepository<T, Long> {


}