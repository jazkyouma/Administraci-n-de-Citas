
import java.io.*;
import java.util.*;

class Doctor {
    private final String id;
    private final String nombre;
    private final String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }

    @Override
    public String toString() {
        return id + "," + nombre + "," + especialidad;
    }
}

class Paciente {
    private final String id;
    private final String nombre;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return id + "," + nombre;
    }
}

class Cita {
    private final String id;
    private final String fechaHora;
    private final String motivo;
    private final Doctor doctor;
    private final Paciente paciente;

    public Cita(String id, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return id + "," + fechaHora + "," + motivo + "," + doctor.getId() + "," + paciente.getId();
    }
}

public class SistemaCitas {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Doctor> doctores = new ArrayList<>();
    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final List<Cita> citas = new ArrayList<>();

    private static final String ADMIN_ID = "admin911";
    private static final String ADMIN_PASSWORD = "paracetamol";

    public static void main(String[] args) {
        if (!autenticar()) {
            System.out.println("Acceso denegado. Saliendo...");
            return;
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1 -> agregarDoctor();
                case 2 -> agregarPaciente();
                case 3 -> crearCita();
                case 4 -> guardarDatos();
                case 5 -> mostrarCitas();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private static boolean autenticar() {
        System.out.print("Ingrese el ID de administrador: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contraseña = scanner.nextLine();
        return ADMIN_ID.equals(id) && ADMIN_PASSWORD.equals(contraseña);
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú del Sistema de Citas");
        System.out.println("1. Agregar Doctor");
        System.out.println("2. Agregar Paciente");
        System.out.println("3. Crear Cita");
        System.out.println("4. Guardar Datos");
        System.out.println("5. Mostrar Citas");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarDoctor() {
        System.out.print("Ingrese el ID del Doctor: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el Nombre del Doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la Especialidad del Doctor: ");
        String especialidad = scanner.nextLine();

        doctores.add(new Doctor(id, nombre, especialidad));
        System.out.println("Doctor agregado exitosamente.");
    }

    private static void agregarPaciente() {
        System.out.print("Ingrese el ID del Paciente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el Nombre del Paciente: ");
        String nombre = scanner.nextLine();

        pacientes.add(new Paciente(id, nombre));
        System.out.println("Paciente agregado exitosamente.");
    }

    private static void crearCita() {
        try {
            System.out.print("Ingrese el ID de la Cita: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese la Fecha y Hora (yyyy-MM-dd HH:mm): ");
            String fechaHora = scanner.nextLine();
            System.out.print("Ingrese el Motivo de la Cita: ");
            String motivo = scanner.nextLine();

            System.out.print("Seleccione el ID del Doctor: ");
            Doctor doctor = seleccionarDoctor();
            if (doctor == null) return;

            System.out.print("Seleccione el ID del Paciente: ");
            Paciente paciente = seleccionarPaciente();
            if (paciente == null) return;

            citas.add(new Cita(id, fechaHora, motivo, doctor, paciente));
            System.out.println("Cita creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear la cita: " + e.getMessage());
        }
    }

    private static Doctor seleccionarDoctor() {
        for (Doctor doctor : doctores) {
            System.out.println(doctor.getId() + " - " + doctor.getNombre());
        }
        String doctorId = scanner.nextLine();
        return doctores.stream().filter(d -> d.getId().equals(doctorId)).findFirst().orElse(null);
    }

    private static Paciente seleccionarPaciente() {
        for (Paciente paciente : pacientes) {
            System.out.println(paciente.getId() + " - " + paciente.getNombre());
        }
        String pacienteId = scanner.nextLine();
        return pacientes.stream().filter(p -> p.getId().equals(pacienteId)).findFirst().orElse(null);
    }

    private static void mostrarCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            System.out.println("\nLista de Citas:");
            for (Cita cita : citas) {
                System.out.println(cita);
            }
        }
    }

    private static void guardarDatos() {
        try {
            guardarEnArchivo("doctores.csv", doctores);
            guardarEnArchivo("pacientes.csv", pacientes);
            guardarEnArchivo("citas.csv", citas);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    private static void guardarEnArchivo(String nombreArchivo, List<?> datos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Object registro : datos) {
                writer.write(registro.toString());
                writer.newLine();
            }
        }
    }
}
