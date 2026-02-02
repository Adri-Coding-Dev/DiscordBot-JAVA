package services;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

/**
 * Servicio para gestionar roles de miembros en un servidor de Discord.
 * Proporciona métodos para añadir y quitar roles de manera asíncrona.
 */
public class RoleService {

    /**
     * Añade un rol a un miembro del servidor.
     * @param member Miembro al que se le añadirá el rol.
     * @param role Rol a añadir.
     */
    public void addRole(Member member, Role role) {
        member.getGuild()
                .addRoleToMember(member, role)
                .queue(); // Ejecuta la operación de forma asíncrona
    }

    /**
     * Quita un rol a un miembro del servidor.
     * @param member Miembro al que se le quitará el rol.
     * @param role Rol a quitar.
     */
    public void removeRole(Member member, Role role) {
        member.getGuild()
                .removeRoleFromMember(member, role)
                .queue(); // Ejecuta la operación de forma asíncrona
    }
}