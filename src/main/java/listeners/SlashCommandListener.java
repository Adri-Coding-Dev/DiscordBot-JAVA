package listeners;

import application.CommandRegistry;
import commands.Command;
import commands.CommandContext;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Listener que maneja eventos de interacción de comandos de barra (slash commands).
 * Actúa como enrutador entre los eventos de Discord y los comandos registrados.
 */
public class SlashCommandListener extends ListenerAdapter {

    /**
     * Maneja los eventos de interacción de comandos de barra.
     * @param event Evento de interacción de comando de barra.
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Busca el comando correspondiente en el registro
        Command command = CommandRegistry.get(event.getName());

        // Si no se encuentra el comando, responde con error
        if (command == null) {
            event.reply("Comando no reconocido").setEphemeral(true).queue();
            return;
        }

        // Crea un contexto y ejecuta el comando
        CommandContext context = new CommandContext(event);
        command.execute(context);
    }
}