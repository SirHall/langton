package SirHall.Time;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class Timer {
    public Timer(){
        this.tps = 1;
    }

    /**
     * Ticks per second (target, not strict)
     */
    private long tps = 1;

    private boolean frozen = false;
    private ScheduledExecutorService executorService;

    private Runnable currentRunnable;

    public void StartSimulation(Runnable timedEvent){
        frozen = false; //Force this on
        //Create a new executor as they don't like restarting
        executorService = Executors.newSingleThreadScheduledExecutor();
        this.currentRunnable = timedEvent;
        executorService.scheduleWithFixedDelay(
                () -> timedEvent.run(),
                0,
                (long)((1.0 / (double)tps) * 1e9),
                TimeUnit.NANOSECONDS
        );
    }

    public void StopSimulation(){
        executorService.shutdown();
    }

    public void SetTPS(long tps){this.tps = tps;}
    public long GetTPS(){return tps;}

    public void ApplyTPS(){Freeze(); Unfreeze();} //Very quick and dirty

    public void Freeze(){
        frozen = true;
        StopSimulation();
    }

    public void Unfreeze(){
        frozen = false;
        StartSimulation(currentRunnable);
    }

    public boolean GetFrozen(){
        return executorService.isShutdown() || executorService.isTerminated();
    }
}
