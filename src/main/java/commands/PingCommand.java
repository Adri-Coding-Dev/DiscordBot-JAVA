package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import java.awt.Color;
import java.time.Instant;

/**
 * Comando de ping que muestra la latencia del bot de Discord.
 * Proporciona información sobre el tiempo de respuesta del gateway y la API REST.
 */
public class PingCommand implements Command {

    /**
     * Devuelve el nombre del comando para Discord.
     * @return "ping" - el nombre del comando.
     */
    @Override
    public String getName() {
        return "ping";
    }

    /**
     * Ejecuta el comando ping, calculando y mostrando las latencias del bot.
     * @param context Contexto del comando con información del evento.
     */
    @Override
    public void execute(CommandContext context) {
        // Obtiene la latencia del gateway (WebSocket)
        long gatewayPing = context.getEvent()
                .getJDA()
                .getGatewayPing();

        // Marca el tiempo actual para calcular la latencia REST
        long startTime = System.currentTimeMillis();

        // Diferir la respuesta para evitar timeout y poder medir la latencia REST
        context.getEvent()
                .deferReply()
                .queue(hook -> {
                    // Calcula la latencia REST (tiempo desde que se envió la solicitud)
                    long restPing = System.currentTimeMillis() - startTime;

                    // Construye el embed con la información de latencia
                    EmbedBuilder embed = buildEmbed(
                            context,
                            gatewayPing,
                            restPing
                    );

                    // Edita el mensaje original con el embed construido
                    hook.editOriginalEmbeds(embed.build()).queue();
                });
    }

    /**
     * Construye un mensaje embed con la información de latencia y estado.
     * @param context Contexto del comando.
     * @param gatewayPing Latencia del gateway en milisegundos.
     * @param restPing Latencia de la API REST en milisegundos.
     * @return EmbedBuilder configurado con la información de latencia.
     */
    private EmbedBuilder buildEmbed(
            CommandContext context,
            long gatewayPing,
            long restPing
    ) {
        String status;  // Estado de la conexión
        Color color;    // Color del embed según el estado

        // Determina el estado y color basado en la latencia del gateway
        if (gatewayPing < 100) {
            status = "🟢 Excelente";
            color = Color.GREEN;
        } else if (gatewayPing < 200) {
            status = "🟡 Aceptable";
            color = Color.ORANGE;
        } else {
            status = "🔴 Lento";
            color = Color.RED;
        }

        // Construye y devuelve el embed
        return new EmbedBuilder()
                .setTitle("🏓 Pong!")
                .setDescription("Estado actual del bot")
                .setColor(color)
                .addField("📡 Gateway Ping", gatewayPing + " ms", true)
                .addField("🌐 REST Ping", restPing + " ms", true)
                .addField("📊 Estado", status, false)
                .setFooter(
                        context.getEvent().getJDA().getSelfUser().getAsTag(),
                        context.getEvent().getJDA().getSelfUser().getAvatarUrl()
                )
                .setTimestamp(Instant.now());
    }
}