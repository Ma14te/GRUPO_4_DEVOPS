# GRUPO_4_DEVOPS

Servicio de registro de usuarios construido con Java 25, Spring Boot y Gradle.

## Ejecutar localmente

```bash
./gradlew build
./gradlew bootRun
```

## CI/CD

El flujo de GitHub Actions se encuentra en `.github/workflows/ci.yml` y ejecuta:

- `build`: construye el proyecto con `./gradlew build --no-daemon`.
- `test`: ejecuta las pruebas con `./gradlew test --no-daemon`.
- `deploy`: copia el `.jar` generado al EC2 y reinicia la aplicacion.

## Secretos requeridos para deploy

Configurar estos secretos en GitHub: `Settings > Secrets and variables > Actions`.

- `EC2_HOST`: IP publica o DNS del EC2.
- `EC2_USER`: usuario SSH del EC2, por ejemplo `ubuntu` o `ec2-user`.
- `EC2_SSH_KEY`: llave privada SSH con acceso al EC2.
- `EC2_APP_DIR`: ruta de despliegue, por ejemplo `/home/ubuntu/user-service`.

El EC2 debe tener Java 25 instalado y el puerto `8080` habilitado en el security group.
