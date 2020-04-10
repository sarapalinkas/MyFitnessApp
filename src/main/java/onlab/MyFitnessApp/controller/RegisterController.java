package onlab.MyFitnessApp.controller;

import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.WorkoutGoal;
import onlab.MyFitnessApp.service.MyUserDetailsService;
import onlab.MyFitnessApp.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/register" })
public class RegisterController
{
    @Autowired
    MyUserDetailsService userDetailsService;

    public RegisterController(MyUserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public void addUser(@RequestBody User user)
    {
        userDetailsService.addUser(user);
    }
}
