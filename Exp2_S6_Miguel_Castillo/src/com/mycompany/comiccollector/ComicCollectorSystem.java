package com.mycompany.comiccollector; 

import java.io.*;          
import java.util.ArrayList; 
import java.util.HashMap;   
import java.util.HashSet;   
import java.util.InputMismatchException; 
import java.util.List;      
import java.util.Map;       
import java.util.Scanner;   
import java.util.Set;       
import java.util.TreeSet;   

public class ComicCollectorSystem {

    // Declaración de Colecciones
    
    private List<Comic> comics; 
    private Map<String, Usuario> usuarios; 
                                          

    
    private Set<String> editorialesUnicas; // guardar las editoriales sin repeticiones.
    private Set<String> titulosOrdenados;  // guardar los títulos de los cómics y que se ordenen automáticamente.

    
    public ComicCollectorSystem() {
        this.comics = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.editorialesUnicas = new HashSet<>();
        this.titulosOrdenados = new TreeSet<>();
    }

    // MÉTODOS PARA MANEJAR ARCHIVOS

    
    public void cargarComicsDesdeCSV(String rutaArchivo) {
        System.out.println("Intentando cargar cómics desde: " + rutaArchivo);
        System.out.println("Ruta absoluta que se intenta abrir para comics: " + new File(rutaArchivo).getAbsolutePath());
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            // Lee el archivo línea por línea hasta que no haya más líneas 
            while ((linea = br.readLine()) != null) {
                // Bloque try/catch interno para manejar errores en cada línea individual del CSV.
                try {
                    String[] datos = linea.split(","); // Divide la línea en partes usando la coma como separador
                    // Verificamos que tengamos el número correcto de partes para crear un Comic
                    if (datos.length == 6) {
                        // Convertimos las partes de texto a los tipos de datos correctos (int, String)
                        int id = Integer.parseInt(datos[0].trim()); // .trim() quita espacios en blanco al inicio/final
                        String titulo = datos[1].trim();
                        String editorial = datos[2].trim();
                        String tipo = datos[3].trim();
                        String autor = datos[4].trim();
                        int anoPublicacion = Integer.parseInt(datos[5].trim());

                        // Creamos un nuevo objeto Comic con los datos leídos
                        Comic comic = new Comic(id, titulo, editorial, tipo, autor, anoPublicacion);
                        comics.add(comic); // Añadimos el Comic a nuestra lista de cómics

                        // También añadimos la editorial y el título a los conjuntos para unicidad/ordenación
                        editorialesUnicas.add(editorial);
                        titulosOrdenados.add(titulo);
                    } else {
                        // Si una línea no tiene el formato esperado, mostramos una advertencia
                        System.err.println("Advertencia: Línea con formato incorrecto, saltando: " + linea);
                    }
                } catch (NumberFormatException e) {
                    // Captura errores si un texto no puede convertirse a número (ej. "abc" en lugar de "123")
                    System.err.println("Error de formato numérico en la línea: " + linea + " -> " + e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Captura errores si faltan datos en la línea (ej. menos de 6 partes)
                    System.err.println("Error de índice (datos incompletos) en la línea: " + linea + " -> " + e.getMessage());
                }
            }
            System.out.println("Carga de cómics completada. Total cargados: " + comics.size());
        } catch (FileNotFoundException e) {
            // Captura el error si el archivo CSV no se encuentra en la ruta especificada
            System.err.println("Error: Archivo CSV no encontrado en " + rutaArchivo + ". Asegúrate de que el archivo existe y la ruta es correcta.");
            // --- LÍNEA DE DEPURACIÓN AÑADIDA AQUÍ ---
            System.err.println("Ruta buscada para comics: " + new File(rutaArchivo).getAbsolutePath());
            // ----------------------------------------
        } catch (IOException e) {
            // Captura otros errores generales de lectura/escritura de archivos
            System.err.println("Error de lectura del archivo CSV: " + e.getMessage());
        }
    }

    
    public void guardarUsuariosEnArchivo(String rutaArchivo) {
        System.out.println("Intentando guardar usuarios en: " + rutaArchivo);
        // --- LÍNEA DE DEPURACIÓN AÑADIDA AQUÍ ---
        System.out.println("Ruta absoluta donde se intenta guardar usuarios: " + new File(rutaArchivo).getAbsolutePath());
        // ----------------------------------------
        // try-with-resources: Asegura que el 'FileWriter' se cierre automáticamente.
        try (FileWriter fw = new FileWriter(rutaArchivo)) {
            // Iteramos sobre todos los objetos Usuario que están en nuestro HashMap
            for (Usuario usuario : usuarios.values()) {
                // Escribimos la representación en cadena del usuario (generada por toFileString())
                // seguida de un salto de línea para que cada usuario esté en una línea diferente.
                fw.write(usuario.toFileString() + "\n");
            }
            System.out.println("Usuarios guardados exitosamente.");
        } catch (IOException e) {
            // Captura errores si hay problemas al escribir en el archivo (ej. permisos).
            System.err.println("Error al escribir en el archivo de usuarios: " + e.getMessage());
            // --- LÍNEA DE DEPURACIÓN AÑADIDA AQUÍ ---
            System.err.println("Ruta al intentar guardar usuarios: " + new File(rutaArchivo).getAbsolutePath());
            // ----------------------------------------
        }
    }

    
    public void cargarUsuariosDesdeArchivo(String rutaArchivo) {
        System.out.println("Intentando cargar usuarios desde: " + rutaArchivo);
        // --- LÍNEA DE DEPURACIÓN AÑADIDA AQUÍ ---
        System.out.println("Ruta absoluta que se intenta abrir para usuarios: " + new File(rutaArchivo).getAbsolutePath());
        // ----------------------------------------
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] datos = linea.split(","); // Divide la línea por comas
                    // Un usuario debe tener al menos nombre, contraseña y email (3 partes)
                    if (datos.length >= 3) {
                        String nombreUsuario = datos[0].trim();
                        String contrasena = datos[1].trim();
                        String email = datos[2].trim();
                        Usuario usuario = new Usuario(nombreUsuario, contrasena, email);

                        // Si hay más partes en la línea, son los cómics reservados por este usuario
                        for (int i = 3; i < datos.length; i++) {
                            usuario.addComicReservado(datos[i].trim());
                        }
                        // Añadimos el usuario al HashMap, usando su nombre como clave
                        usuarios.put(nombreUsuario, usuario);
                    } else {
                        System.err.println("Advertencia: Línea de usuario con formato incorrecto, saltando: " + linea);
                    }
                } catch (Exception e) { // Captura cualquier tipo de error al procesar una línea de usuario
                    System.err.println("Error al procesar línea de usuario: " + linea + " -> " + e.getMessage());
                }
            }
            System.out.println("Carga de usuarios completada. Total cargados: " + usuarios.size());
        } catch (FileNotFoundException e) {
            // Si el archivo de usuarios no existe, es una advertencia, no un error crítico.
            // Significa que es la primera vez que se ejecuta o el archivo se borró.
            System.err.println("Advertencia: Archivo de usuarios no encontrado en " + rutaArchivo + ". Se creará uno nuevo al guardar.");
            // --- LÍNEA DE DEPURACIÓN AÑADIDA AQUÍ ---
            System.err.println("Ruta buscada para usuarios: " + new File(rutaArchivo).getAbsolutePath());
            // ----------------------------------------
        } catch (IOException e) {
            System.err.println("Error de lectura del archivo de usuarios: " + e.getMessage());
        }
    }


    // --- MÉTODOS PARA INTERACTUAR CON LAS COLECCIONES (Lógica de Negocio) ---

    /**
     * Agrega un nuevo usuario al sistema.
     * @param usuario El objeto Usuario a agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        try {
            // Regla de negocio: No se permite agregar usuarios con nombres de usuario duplicados.
            if (usuarios.containsKey(usuario.getNombreUsuario())) {
                // Si el usuario ya existe, lanzamos una excepción para indicar el problema.
                throw new IllegalArgumentException("El nombre de usuario '" + usuario.getNombreUsuario() + "' ya existe. Por favor, elige otro.");
            }
            usuarios.put(usuario.getNombreUsuario(), usuario); // Añade el usuario al HashMap
            System.out.println("Usuario " + usuario.getNombreUsuario() + " agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            // Capturamos la excepción y mostramos un mensaje de error amigable al usuario.
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    
    public void reservarComic(String nombreUsuario, String tituloComic) {
        try {
            // Validación: Verificar si el usuario existe en el sistema.
            if (!usuarios.containsKey(nombreUsuario)) {
                throw new IllegalArgumentException("Usuario '" + nombreUsuario + "' no encontrado. Por favor, regístrate primero.");
            }
            // Validación: Verificar si el cómic existe en nuestra lista de cómics disponibles.
            // Usamos 'stream().anyMatch()' para buscar el título sin importar mayúsculas/minúsculas.
            boolean comicExiste = comics.stream().anyMatch(c -> c.getTitulo().equalsIgnoreCase(tituloComic));
            if (!comicExiste) {
                throw new IllegalArgumentException("El cómic '" + tituloComic + "' no está disponible o no existe en nuestra base de datos.");
            }

            Usuario usuario = usuarios.get(nombreUsuario); // Obtenemos el objeto Usuario
            // Validación: Verificar si el cómic ya está reservado por este usuario.
            if (usuario.getComicsReservados().contains(tituloComic)) {
                System.out.println("El cómic '" + tituloComic + "' ya está reservado por " + nombreUsuario + ".");
            } else {
                usuario.addComicReservado(tituloComic); // Añade el cómic a la lista de reservas del usuario
                System.out.println("Cómic '" + tituloComic + "' reservado para " + nombreUsuario + ".");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error al reservar cómic: " + e.getMessage());
        }
    }

    /**
     * Muestra todos los cómics que han sido cargados en el sistema.
     */
    public void listarComics() {
        if (comics.isEmpty()) { // Si la lista de cómics está vacía
            System.out.println("No hay cómics cargados en el sistema.");
            return; // Salir del método
        }
        System.out.println("\n--- Lista de Cómics Disponibles ---");
        for (Comic comic : comics) { // Itera sobre cada Comic en la lista
            System.out.println(comic); // Imprime el Comic (usa el método toString() de la clase Comic)
        }
        System.out.println("------------------------------------");
    }

    /**
     * Muestra todos los usuarios registrados en el sistema.
     */
    public void listarUsuarios() {
        if (usuarios.isEmpty()) { // Si el mapa de usuarios está vacío
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }
        System.out.println("\n--- Lista de Usuarios Registrados ---");
        for (Usuario usuario : usuarios.values()) { // Itera sobre los valores (objetos Usuario) del HashMap
            System.out.println(usuario); // Imprime el Usuario (usa el método toString() de la clase Usuario)
        }
        System.out.println("------------------------------------");
    }

    
    public void mostrarEditorialesUnicas() {
        if (editorialesUnicas.isEmpty()) {
            System.out.println("No hay editoriales únicas cargadas.");
            return;
        }
        System.out.println("\n--- Editoriales Únicas (HashSet) ---");
        for (String editorial : editorialesUnicas) {
            System.out.println(editorial);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Muestra los títulos de los cómics cargados, ordenados alfabéticamente, utilizando TreeSet.
     * TreeSet mantiene los elementos ordenados automáticamente.
     */
    public void mostrarTitulosOrdenados() {
        if (titulosOrdenados.isEmpty()) {
            System.out.println("No hay títulos de cómics ordenados cargados.");
            return;
        }
        System.out.println("\n--- Títulos de Cómics Ordenados (TreeSet) ---");
        for (String titulo : titulosOrdenados) {
            System.out.println(titulo);
        }
        System.out.println("------------------------------------");
    }


    // --- MÉTODO PRINCIPAL (MAIN) ---
    
    
    public static void main(String[] args) {
        ComicCollectorSystem system = new ComicCollectorSystem(); // Creamos una instancia de nuestro sistema
        Scanner scanner = new Scanner(System.in); // Creamos un objeto Scanner para leer la entrada del usuario desde la consola

        // Definimos los nombres de los archivos que vamos a usar.
        String comicsCSV = "libros.csv";    // Nombre del archivo de cómics
        String usuariosTXT = "usuarios.txt"; // Nombre del archivo donde se guardarán/cargarán los usuarios

        // --- Carga inicial de datos al inicio del programa ---
        // Intentamos cargar los cómics y usuarios existentes.
        system.cargarComicsDesdeCSV(comicsCSV);
        system.cargarUsuariosDesdeArchivo(usuariosTXT);

        int opcion; // Variable para guardar la opción que elija el usuario en el menú
        do { // Este es un bucle 'do-while' que se ejecutará al menos una vez y continuará
             // mientras la opción elegida no sea la de salir (opción 7).
            System.out.println("\n--- Menú ComicCollectorSystem ---");
            System.out.println("1. Listar Cómics Disponibles");
            System.out.println("2. Listar Usuarios Registrados");
            System.out.println("3. Agregar Nuevo Usuario");
            System.out.println("4. Reservar Cómic para Usuario");
            System.out.println("5. Mostrar Editoriales Únicas");
            System.out.println("6. Mostrar Títulos de Cómics Ordenados");
            System.out.println("7. Guardar Usuarios y Salir");
            System.out.print("Ingrese una opción: ");

            try { // Bloque try/catch para manejar la entrada del usuario en el menú.
                  // Esto previene que el programa se caiga si el usuario ingresa algo que no es un número.
                opcion = scanner.nextInt(); // Lee el número que el usuario ingresa
                scanner.nextLine(); 
                                    

                switch (opcion) { 
                    case 1:
                        system.listarComics(); // Llama al método para listar cómics
                        break; // Sale del switch
                    case 2:
                        system.listarUsuarios(); // Llama al método para listar usuarios
                        break;
                    case 3:
                        System.out.print("Ingrese nombre de usuario (ej. 'juan123'): ");
                        String nomUser = scanner.nextLine().trim(); 
                        System.out.print("Ingrese contraseña: ");
                        String passUser = scanner.nextLine().trim(); 
                        System.out.print("Ingrese email (ej. 'juan@correo.com'): ");
                        String emailUser = scanner.nextLine().trim(); 
                        system.agregarUsuario(new Usuario(nomUser, passUser, emailUser)); // Crea y agrega un nuevo Usuario
                        break;
                    case 4:
                        System.out.print("Ingrese nombre de usuario para reservar: ");
                        String userReserva = scanner.nextLine().trim(); 
                        System.out.print("Ingrese título del cómic a reservar: ");
                        String comicReserva = scanner.nextLine().trim(); 
                        system.reservarComic(userReserva, comicReserva); // Llama al método para reservar un cómic
                        break;
                    case 5:
                        system.mostrarEditorialesUnicas(); // Muestra las editoriales únicas
                        break;
                    case 6:
                        system.mostrarTitulosOrdenados(); // Muestra los títulos ordenados
                        break;
                    case 7:
                        system.guardarUsuariosEnArchivo(usuariosTXT); // Guarda los datos de los usuarios antes de salir
                        System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                // Captura el error si el usuario ingresa texto en lugar de un número para la opción del menú.
                System.err.println("Error: Entrada no válida. Por favor, ingrese un número del menú.");
                scanner.nextLine(); // Limpia el buffer del scanner para evitar que el programa se quede en un bucle infinito
                opcion = 0; // Asigna 0 a 'opcion' para que el bucle 'do-while' continúe y muestre el menú de nuevo
            } catch (Exception e) {
                // Captura cualquier otra excepción inesperada que pueda ocurrir en el bloque try.
                System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
                opcion = 0; // Para que el bucle continúe
            }
        } while (opcion != 7); // El bucle continúa mientras la opción no sea 7 (Salir)

        scanner.close(); // Cierra el objeto Scanner para liberar recursos del sistema.
    }
}