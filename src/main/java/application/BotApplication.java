package application;

import config.BotConfig;
import core.lifecycle.ReadyListener;
import listeners.SlashCommandListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * Clase principal de la aplicación del bot de Discord.
 * Inicializa y configura el bot utilizando la librería JDA.
 */
public final class BotApplication {

    /**
     * Punto de entrada principal de la aplicación.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Cargar la configuración del bot desde BotConfig
        BotConfig config = BotConfig.load();

        // Crear el constructor del bot con el token cargado
        JDABuilder builder = JDABuilder.createDefault(config.getToken());
        // Habilitar el intent para acceder al contenido de los mensajes
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        // Añadir listeners (oyentes) de eventos
        builder.addEventListeners(
                new ReadyListener(),          // Maneja el evento cuando el bot está listo
                new SlashCommandListener()    // Maneja los comandos de barra (slash commands)
        );

        // Construir y conectar el bot a Discord
        builder.build();
    }
}