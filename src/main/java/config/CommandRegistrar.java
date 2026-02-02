package config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * Clase responsable de registrar los comandos de barra (slash commands) en Discord.
 * Los comandos registrados aquí estarán disponibles globalmente después de un breve retraso.
 */
public final class CommandRegistrar {

    // Constructor privado para evitar instanciación
    private CommandRegistrar() {}

    /**
     * Registra los comandos de barra en el bot de Discord.
     * @param jda Instancia de JDA (Java Discord API) conectada a Discord.
     */
    public static void register(JDA jda) {
        // Actualiza los comandos globales del bot
        jda.updateCommands()
                // Añade el comando /ping con su descripción
                .addCommands(Commands.slash("ping", "Muestra la latencia del bot"))
                .queue(); // Envía la solicitud a Discord de forma asíncrona
    }
}