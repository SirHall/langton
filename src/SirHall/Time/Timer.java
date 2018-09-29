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

    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public void StartSimulation(Runnable timedEvent){

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
}
