package core.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Programador de tareas (scheduler) que utiliza un ScheduledExecutorService de un solo hilo.
 * Permite programar tareas para ejecución futura o periódica.
 */
public final class TaskScheduler {

    // Executor de un solo hilo para programar tareas
    private static final ScheduledExecutorService EXECUTOR =
            Executors.newSingleThreadScheduledExecutor();

    // Constructor privado para evitar instanciación
    private TaskScheduler() {}

    /**
     * Programa una tarea para ejecutarse después de un retraso específico.
     * @param task Tarea (Runnable) a ejecutar.
     * @param delay Cantidad de tiempo a esperar antes de la ejecución.
     * @param unit Unidad de tiempo del retraso (segundos, minutos, etc.).
     */
    public static void schedule(Runnable task, long delay, TimeUnit unit) {
        EXECUTOR.schedule(task, delay, unit);
    }
}