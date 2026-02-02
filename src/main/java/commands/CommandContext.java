package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Contexto de un comando que envuelve el evento de interacción de comando.
 * Proporciona métodos de conveniencia para acceder a datos comunes del evento.
 */
public class CommandContext {
    private final SlashCommandInteractionEvent event;

    /**
     * Constructor que recibe el evento de interacción de comando.
     * @param event Evento de interacción de comando de barra (slash command).
     */
    public CommandContext(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    /**
     * Obtiene el evento de interacción original.
     * @return El evento SlashCommandInteractionEvent.
     */
    public SlashCommandInteractionEvent getEvent() {
        return event;
    }

    /**
     * Obtiene el miembro (usuario con roles en el servidor) que ejecutó el comando.
     * @return Objeto Member del usuario que ejecutó el comando.
     */
    public Member getMember() {
        return event.getMember();
    }

    /**
     * Obtiene el canal de texto donde se ejecutó el comando.
     * @return El canal de texto como objeto TextChannel.
     */
    public TextChannel getChannel() {
        return event.getChannel().asTextChannel();
    }
}