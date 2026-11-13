public record Incidencia(
        String equipo,
        String ubicacion,
        String descripcion,
        Prioridad prioridad,
        Estado estado) {

    public Incidencia {
        equipo = validarTexto(equipo, "equipo");
        ubicacion = validarTexto(ubicacion, "ubicación");
        descripcion = validarTexto(descripcion, "descripción");

        if (prioridad == null) {
            throw new IllegalArgumentException("La prioridad no puede ser nula.");
        }
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
    }

    private static String validarTexto(String valor, String nombreCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    "El campo " + nombreCampo + " no puede ser nulo ni estar vacío.");
        }
        return valor.strip();
    }
}
