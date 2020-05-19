package onlab.MyFitnessApp.controller;

import onlab.MyFitnessApp.entity.User;
import onlab.MyFitnessApp.entity.goaltypes.*;
import onlab.MyFitnessApp.service.goalservice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

import static onlab.MyFitnessApp.service.MyUserDetailsService.currentUser;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/goals" })
public class GoalController {

    @Autowired
    private WorkoutService workoutService;
    @Autowired
    private SleepService sleepService;
    @Autowired
    private MeditationService meditationService;
    @Autowired
    private NatureService natureService;
    @Autowired
    private FruitService fruitService;
    @Autowired
    private VegService vegService;

    public GoalController(
            WorkoutService workoutService,
            SleepService sleepService,
            MeditationService meditationService,
            NatureService natureService,
            FruitService fruitService,
            VegService vegService
    )
    {
        this.workoutService = workoutService;
        this.sleepService = sleepService;
        this.meditationService = meditationService;
        this.natureService = natureService;
        this.fruitService = fruitService;
        this.vegService = vegService;
    }

    @GetMapping()
    @RequestMapping({ "/validateLogin" })
    public User validateLogin() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        String currweek = "" + year + week;
        if(currentUser.getWorkoutGoal()!= null && !currentUser.getWorkoutGoal().getCurrentWeek().equals(currweek))
        {
            workoutService.archiveWorkout();
            currentUser.setMedals(0);
        }
        if(currentUser.getSleepGoal()!= null && !currentUser.getSleepGoal().getCurrentWeek().equals(currweek))
        {
            sleepService.archiveSleep();
            currentUser.setMedals(0);
        }
        if(currentUser.getMeditationGoal()!= null && !currentUser.getMeditationGoal().getCurrentWeek().equals(currweek))
        {
            meditationService.archiveMeditation();
            currentUser.setMedals(0);
        }
        if(currentUser.getNatureGoal()!= null && !currentUser.getNatureGoal().getCurrentWeek().equals(currweek))
        {
            natureService.archiveNature();
            currentUser.setMedals(0);
        }
        if(currentUser.getFruitGoal()!= null && !currentUser.getFruitGoal().getCurrentWeek().equals(currweek))
        {
            fruitService.archiveFruit();
            currentUser.setMedals(0);
        }
        if(currentUser.getVegGoal()!= null && !currentUser.getVegGoal().getCurrentWeek().equals(currweek))
        {
            vegService.archiveVegetable();
            currentUser.setMedals(0);
        }
        return new User("User successfully authenticated");
    }

    @GetMapping(value="/medals")
    public Integer getMedals() {return currentUser.getMedals();}


    @GetMapping(value="/workoutgoal")
    public WorkoutGoal getWorkoutgoal() {return workoutService.getWorkoutGoal();}
    @GetMapping(value="/sleepgoal")
    public SleepGoal getSleepgoal() {return sleepService.getSleepGoal();}
    @GetMapping(value="/meditationgoal")
    public MeditationGoal getMeditationgoal() {return meditationService.getMeditationGoal();}
    @GetMapping(value="/naturegoal")
    public NatureGoal getNaturegoal() {return natureService.getNatureGoal();}
    @GetMapping(value="/fruitgoal")
    public FruitGoal getFruitgoal() {return fruitService.getFruitGoal();}
    @GetMapping(value="/veggoal")
    public VegGoal getVeggoal() {return vegService.getVegGoal();}

    @DeleteMapping(path = { "/workoutgoal/{id}" })
    public void deleteWorkoutGoal(@PathVariable("id") long id) {workoutService.deleteWorkoutGoal(id);}
    @DeleteMapping(path = { "/sleepgoal/{id}" })
    public void deleteSleepgoal(@PathVariable("id") long id) {sleepService.deleteSleepGoal(id);}
    @DeleteMapping(path = { "/meditationgoal/{id}" })
    public void deleteMeditationgoal(@PathVariable("id") long id) {meditationService.deleteMeditationGoal(id);}
    @DeleteMapping(path = { "/naturegoal/{id}" })
    public void deleteNaturegoal(@PathVariable("id") long id) {natureService.deleteNatureGoal(id);}
    @DeleteMapping(path = { "/fruitgoal/{id}" })
    public void deleteFruitgoal(@PathVariable("id") long id) {fruitService.deleteFruitGoal(id);}
    @DeleteMapping(path = { "/veggoal/{id}" })
    public void deleteVeggoal(@PathVariable("id") long id) {vegService.deleteVegGoal(id);}

    @PostMapping(value="/workoutgoal")
    public void addWorkoutGoal(@RequestBody WorkoutGoal workoutGoal) {
        workoutService.addWorkoutGoal(workoutGoal);
    }
    @PostMapping(value="/sleepgoal")
    public void addSleepGoal(@RequestBody SleepGoal sleepgoal) {
        sleepService.addSleepGoal(sleepgoal);
    }
    @PostMapping(value="/meditationgoal")
    public void addMeditationGoal(@RequestBody MeditationGoal meditationGoal) {
        meditationService.addMeditationGoal(meditationGoal);
    }
    @PostMapping(value="/naturegoal")
    public void addNatureGoal(@RequestBody NatureGoal natureGoal) {
        natureService.addNatureGoal(natureGoal);
    }
    @PostMapping(value="/fruitgoal")
    public void addFruitGoal(@RequestBody FruitGoal fruitGoal) {
        fruitService.addFruitGoal(fruitGoal);
    }    @PostMapping(value="/veggoal")
    public void addVegGoal(@RequestBody VegGoal vegGoal) {
        vegService.addVegGoal(vegGoal);
    }

    @PutMapping(value="/workoutgoal")
    public void updateWorkoutGoal(@RequestBody WorkoutGoal workoutGoal) {workoutService.updateWorkoutGoal(workoutGoal);}
    @PutMapping(value="/sleepgoal")
    public void updateSleepgoal(@RequestBody SleepGoal sleepGoal) {sleepService.updateSleepGoal(sleepGoal);}
    @PutMapping(value="/meditationgoal")
    public void updateMeditationgoal(@RequestBody MeditationGoal meditationGoal) {meditationService.updateMeditationGoal(meditationGoal);}
    @PutMapping(value="/naturegoal")
    public void updateNaturegoal(@RequestBody NatureGoal natureGoal) {natureService.updateNatureGoal(natureGoal);}
    @PutMapping(value="/fruitgoal")
    public void updateFruitgoal(@RequestBody FruitGoal fruitGoal) {fruitService.updateFruitGoal(fruitGoal);}
    @PutMapping(value="/veggoal")
    public void updateVeggoal(@RequestBody VegGoal vegGoal) {vegService.updateVegGoal(vegGoal);}

}