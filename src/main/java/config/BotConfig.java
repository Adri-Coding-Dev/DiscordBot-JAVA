package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase de configuración del bot que carga credenciales y configuraciones de forma segura.
 * Mejorado para cargar el token desde variables de entorno o archivo de configuración.
 */
public final class BotConfig {

    private final String token;
    private final String prefix;

    /**
     * Constructor privado para forzar el uso del método de carga.
     * @param token Token del bot de Discord.
     * @param prefix Prefijo para comandos de texto (si se usan).
     */
    private BotConfig(String token, String prefix) {
        this.token = token;
        this.prefix = prefix;
    }

    /**
     * Carga la configuración del bot con prioridad de fuentes:
     * 1. Variable de entorno DISCORD_BOT_TOKEN
     * 2. Archivo config.properties en el directorio del proyecto
     * 3. Archivo config.properties en resources
     * @return Instancia de BotConfig con las configuraciones cargadas.
     * @throws IllegalStateException Si no se puede encontrar el token.
     */
    public static BotConfig load() {
        String token = null;
        String prefix = "!"; // Prefijo por defecto

        // 1. Intentar cargar desde variable de entorno (más seguro)
        token = System.getenv("DISCORD_BOT_TOKEN");

        if (token == null || token.isEmpty()) {
            // 2. Intentar cargar desde archivo de propiedades
            Properties prop = new Properties();

            try {
                // Primero buscar en el directorio del proyecto
                try (InputStream input = new FileInputStream("config.properties")) {
                    prop.load(input);
                }
            } catch (IOException e1) {
                // Si no existe en el directorio, buscar en resources
                try (InputStream input = BotConfig.class.getClassLoader()
                        .getResourceAsStream("config.properties")) {

                    if (input != null) {
                        prop.load(input);
                    } else {
                        // Archivo no encontrado en resources
                        System.err.println("Archivo config.properties no encontrado en resources");
                    }
                } catch (IOException e2) {
                    // Error al leer el archivo de resources
                    System.err.println("Error al leer config.properties: " + e2.getMessage());
                }
            }

            // Obtener valores del archivo de propiedades
            token = prop.getProperty("discord.bot.token");
            prefix = prop.getProperty("discord.bot.prefix", prefix);
        }

        // Validar que se haya obtenido un token
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException(
                    "No se pudo cargar el token del bot. Por favor:\n" +
                            "1. Establece la variable de entorno DISCORD_BOT_TOKEN\n" +
                            "2. O crea un archivo config.properties con 'discord.bot.token=TU_TOKEN'\n" +
                            "El archivo config.properties debe estar en el directorio del proyecto o en resources/"
            );
        }

        // Limpiar posibles espacios en blanco
        token = token.trim();

        // Validar formato básico del token (opcional)
        if (!token.matches("[\\w-]{24,}\\.[\\w-]{6}\\.[\\w-]{27,}")) {
            System.err.println("ADVERTENCIA: El token no parece tener el formato estándar de Discord");
        }

        return new BotConfig(token, prefix);
    }

    /**
     * Obtiene el token del bot.
     * @return Token del bot de Discord.
     */
    public String getToken() {
        return token;
    }

    /**
     * Obtiene el prefijo para comandos de texto.
     * @return Prefijo para comandos (ej. "!").
     */
    public String getPrefix() {
        return prefix;
    }
}