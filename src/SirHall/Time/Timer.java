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

    /**
     * Starts the simulation
     * @param timedEvent
     */
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

    /**
     * Stops the simulation
     */
    public void StopSimulation(){
        executorService.shutdown();
    }

    /**
     * Returns the ticks per second
     * @return
     */
    public long GetTPS(){return tps;}

    /**
     * Changes the ticks per second
     * @param tps
     */
    public void SetTPS(long tps){this.tps = tps;}

    /**
     * Applies the current ticks per second
     */
    public void ApplyTPS(){Freeze(); Unfreeze();} //Very quick and dirty

    /**
     * Freezes the simulation
     */
    public void Freeze(){
        frozen = true;
        StopSimulation();
    }

    /**
     * Unfreezes the simulation
     */
    public void Unfreeze(){
        frozen = false;
        StartSimulation(currentRunnable);
    }

    /**
     * Returns whether or not the simulation is frozen
     * @return
     */
    public boolean GetFrozen(){
        return executorService.isShutdown() || executorService.isTerminated();
    }
}
