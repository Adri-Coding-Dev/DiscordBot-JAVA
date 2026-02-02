package application;

import commands.Command;
import commands.PingCommand;
import commands.ScheduleMessageCommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Registro centralizado de comandos del bot.
 * Mantiene un mapa de nombres de comandos a instancias de Command.
 * Inicializa y proporciona acceso a todos los comandos registrados.
 */
public final class CommandRegistry {

    // Mapa inmutable que almacena todos los comandos registrados por su nombre
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    // Bloque estático para registrar comandos al cargar la clase
    static {
        register(new PingCommand());               // Registra el comando /ping
        register(new ScheduleMessageCommand());    // Registra el comando /schedule
    }

    /**
     * Registra un comando en el registro.
     * @param command Instancia del comando a registrar.
     */
    private static void register(Command command) {
        COMMANDS.put(command.getName(), command);
    }

    /**
     * Obtiene un comando por su nombre.
     * @param name Nombre del comando (ej. "ping").
     * @return Instancia del comando o null si no existe.
     */
    public static Command get(String name) {
        return COMMANDS.get(name);
    }

    /**
     * Devuelve una colección con todos los comandos registrados.
     * @return Colección de comandos.
     */
    public static Collection<Command> all() {
        return COMMANDS.values();
    }
}