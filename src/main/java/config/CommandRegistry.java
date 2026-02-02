package config;

import commands.Command;
import commands.PingCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Registro de comandos alternativo (en el paquete config).
 * NOTA: Parece haber duplicidad con application.CommandRegistry.
 * Este archivo podría ser redundante o estar desactualizado.
 */
public final class CommandRegistry {

    // Mapa que almacena comandos por nombre
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    // Registra comandos al cargar la clase
    static {
        register(new PingCommand());
    }

    /**
     * Registra un comando en el mapa.
     * @param command Comando a registrar.
     */
    private static void register(Command command) {
        COMMANDS.put(command.getName(), command);
    }

    /**
     * Obtiene un comando por su nombre.
     * @param name Nombre del comando.
     * @return Instancia del comando o null si no existe.
     */
    public static Command get(String name) {
        return COMMANDS.get(name);
    }
}