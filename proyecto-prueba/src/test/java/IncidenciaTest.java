import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IncidenciaTest {

    @Test
    void trimsTextFieldsAndPreservesEnums() {
        Incidencia incidencia = new Incidencia(
                "  Equipo A  ",
                "  Planta baja  ",
                "  Falla de conexión  ",
                Prioridad.ALTA,
                Estado.PENDIENTE);

        assertEquals("Equipo A", incidencia.equipo());
        assertEquals("Planta baja", incidencia.ubicacion());
        assertEquals("Falla de conexión", incidencia.descripcion());
        assertEquals(Prioridad.ALTA, incidencia.prioridad());
        assertEquals(Estado.PENDIENTE, incidencia.estado());
    }

    @Test
    void rejectsNullOrBlankEquipo() {
        assertThrows(IllegalArgumentException.class, () -> create(null, "ubicación", "descripción"));
        assertThrows(IllegalArgumentException.class, () -> create("  ", "ubicación", "descripción"));
    }

    @Test
    void rejectsNullOrBlankUbicacion() {
        assertThrows(IllegalArgumentException.class, () -> create("equipo", null, "descripción"));
        assertThrows(IllegalArgumentException.class, () -> create("equipo", " \t ", "descripción"));
    }

    @Test
    void rejectsNullOrBlankDescripcion() {
        assertThrows(IllegalArgumentException.class, () -> create("equipo", "ubicación", null));
        assertThrows(IllegalArgumentException.class, () -> create("equipo", "ubicación", "\n"));
    }

    @Test
    void rejectsNullPrioridad() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Incidencia("equipo", "ubicación", "descripción", null, Estado.PENDIENTE));
    }

    @Test
    void rejectsNullEstado() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Incidencia("equipo", "ubicación", "descripción", Prioridad.MEDIA, null));
    }

    private Incidencia create(String equipo, String ubicacion, String descripcion) {
        return new Incidencia(equipo, ubicacion, descripcion, Prioridad.MEDIA, Estado.PENDIENTE);
    }
}
