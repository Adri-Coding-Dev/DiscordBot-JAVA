package commands;

import core.scheduler.TaskScheduler;

import java.util.concurrent.TimeUnit;

/**
 * Comando para programar mensajes en el futuro.
 * Actualmente programa un mensaje fijo después de un retraso predefinido.
 */
public class ScheduleMessageCommand implements Command {

    /**
     * Devuelve el nombre del comando para Discord.
     * @return "schedule" - el nombre del comando.
     */
    @Override
    public String getName() {
        return "schedule";
    }

    /**
     * Ejecuta el comando schedule, programando un mensaje para enviarse después de un retraso.
     * @param context Contexto del comando con información del evento.
     */
    @Override
    public void execute(CommandContext context) {
        // Retraso fijo de 60 segundos (posteriormente vendrá de argumentos)
        long delaySeconds = 60;

        // Programa una tarea para enviar un mensaje después del retraso
        TaskScheduler.schedule(() -> {
            context.getChannel()
                    .sendMessage("⏰ Mensaje programado")
                    .queue();
        }, delaySeconds, TimeUnit.SECONDS);

        // Responde al usuario confirmando la programación (mensaje efímero)
        context.getEvent()
                .reply("Mensaje programado correctamente")
                .setEphemeral(true)
                .queue();
    }
}