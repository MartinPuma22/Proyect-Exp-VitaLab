- Corregir nombre del proyecto: proyect-exp-vitalab
- Remover mvnw, mvnw.cmd, .gitattributes
- Corregir nombre del artifact:
```xml
	<groupId>com.org.vitaLab</groupId>
	<artifactId>clinica-ocupacional </artifactId>
	<version>0.0.1-SNAPSHOT</version>
```

- Remover secciones del pom.xml sin usar
```xml
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
```

> **Nota**: Spring Dev Tools se utiliza solo para desarrollo.

- Corregir la integración con Lombok
> Ejemplo [commons-spring-boot-parent](https://github.com/miguel-armas-abt/commons-spring-boot-parent/blob/main/pom.xml)

- Organizar versionado de las dependencias en la sección properties
```xml
	<properties>
		<java.version>17</java.version>
		<jasper.version>6.21.0</jasper.version>
	</properties>
```

- Corregir nombre del paquete:
```
project-exp-vitalab
├───src.main.java
│   └───com.org.vitalab
│       ├───controller
│       ├───model
│       ├───repository
│       └───service
└─── ...
```
- Se puede implementar [Screaming architecture](https://media.licdn.com/dms/image/v2/D4E22AQFPA0vfMK4dSQ/feedshare-shrink_800/B4EZSyELXAHoAg-/0/1738154227939?e=2147483647&v=beta&t=t6xhlGNFb_aivQkF1Zesrx99B1lRIuWdH554hZDXnJE).

```
  project-exp-vitalab
  ├───src.main.java
  │   └───com.org.vitalab
  │       ├───atencion
  │       │   ├───controller
  │       │   ├───service
  │       │   ├───repository
  │       │   └───model
  │       ├───cita
  │       ├───doctor
  │       ├───reporte
  │       └───paciente
  └─── ...
```

- No mezclar inglés y español
  - `PacienteController`
  - `ReportController`

- Limpiar imports

- Crear una clase de constantes para tus cadenas repetidas.

```java
model.addAttribute("listaCitas", citaService.listar());
model.addAttribute("pacientes", pacienteService.listar());
model.addAttribute("doctores", doctorService.listar());
model.addAttribute("cita", new Cita());
model.addAttribute("modoEdicion", false);
```

- Utilizar correctamente los métodos REST (GET, POST, PUT, DELETE).

- [Teoría Spring Boot](https://github.com/miguel-armas-abt/roadmap-java/blob/main/path/27-spring-boot/README.md)







