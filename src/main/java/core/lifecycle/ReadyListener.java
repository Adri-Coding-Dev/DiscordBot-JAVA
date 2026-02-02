package core.lifecycle;

import config.CommandRegistrar;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener que maneja el evento Ready del bot (cuando se conecta exitosamente a Discord).
 * Se ejecuta una vez cuando el bot está listo para operar.
 */
public class ReadyListener extends ListenerAdapter {

    // Logger para registrar información de conexión
    private static final Logger logger =
            LoggerFactory.getLogger(ReadyListener.class);

    /**
     * Método llamado cuando el bot se conecta exitosamente a Discord.
     * @param event Evento Ready que contiene información de la conexión.
     */
    @Override
    public void onReady(ReadyEvent event) {
        // Registra información de conexión
        logger.info("Bot conectado correctamente como {}",
                event.getJDA().getSelfUser().getAsTag());

        // Registra los comandos de barra en Discord
        CommandRegistrar.register(event.getJDA());
        logger.info("Slash Commands registrados");
    }
}