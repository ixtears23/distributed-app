package junseok.snr.distributedsystem.scheduler;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DynamicSchedulerCLI {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final List<ScheduledFuture<?>> scheduledTasks = new ArrayList<>();

    private ScheduledFuture<?> scheduleTask (Runnable task,long delayInSeconds){
        return scheduler.schedule(task, delayInSeconds, TimeUnit.SECONDS);
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command (add/cancel/exit):");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    System.out.println("Enter delay in seconds:");
                    long delay = scanner.nextLong();
                    scanner.nextLine(); // Consume newline
                    ScheduledFuture<?> scheduledTask = scheduleTask(() -> System.out.println("Task executed at: " + System.currentTimeMillis()), delay);
                    scheduledTasks.add(scheduledTask);
                    System.out.println("Task scheduled in " + delay + " seconds.");
                    break;
                case "cancel":
                    System.out.println("Enter index of task to cancel:");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    cancelTask(index);
                    break;
                case "exit":
                    shutdown();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }

    }

    private void cancelTask(int index) {
        if (index >= 0 && index < scheduledTasks.size()) {
            ScheduledFuture<?> task = scheduledTasks.get(index);
            if (task != null) {
                task.cancel(true);
                System.out.println("Task " + index + " cancelled.");
            }
        } else {
            System.out.println("Invalid task index.");
        }
    }

    private void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }



    public static void main(String[] args) {
        final DynamicSchedulerCLI dynamicScheduler = new DynamicSchedulerCLI();
        dynamicScheduler.start();
    }
}
