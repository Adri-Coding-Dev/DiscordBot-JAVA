package commands;

/**
 * Interfaz que define la estructura base de un comando del bot.
 * Todos los comandos deben implementar esta interfaz.
 */
public interface Command {
    /**
     * Devuelve el nombre del comando (como se usa en Discord).
     * @return Nombre del comando (ej. "ping").
     */
    String getName();

    /**
     * Ejecuta la lógica principal del comando.
     * @param context Contexto del comando que contiene información del evento.
     */
    void execute(CommandContext context);
}