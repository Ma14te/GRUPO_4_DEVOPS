# GRUPO_4_DEVOPS

Servicio de registro de usuarios construido con Java 25, Spring Boot y Gradle.

## Ejecutar localmente

```bash
./gradlew build
./gradlew bootRun
```

## Persistencia

La aplicacion persiste usuarios en PostgreSQL usando Spring Data JPA. En EC2 se conecta a
Amazon RDS con las variables de entorno enviadas desde `.github/workflows/ci.yml`.

La tabla se crea automaticamente con Hibernate (`ddl-auto=update`) en la base `userdb`.

## CI/CD

El flujo de GitHub Actions se encuentra en `.github/workflows/ci.yml` y ejecuta:

- `build`: construye el proyecto con `./gradlew build --no-daemon`.
- `test`: ejecuta las pruebas con `./gradlew test --no-daemon`.
- `deploy`: copia el `.jar` generado al EC2 y reinicia la aplicacion.

## Secretos requeridos para deploy

Configurar estos secretos en GitHub: `Settings > Secrets and variables > Actions`.

- `EC2_SSH_KEY`: llave privada SSH con acceso al EC2.
- `DB_PASS`: password de la base de datos PostgreSQL.

El deploy usa estos valores fijos en `.github/workflows/ci.yml`:

- EC2 host: `18.218.230.144`
- EC2 user: `ec2-user`
- EC2 app dir: `/home/ec2-user/user-service`
- PostgreSQL host: `grupo-4-rds.ct8iogk44c9s.us-east-2.rds.amazonaws.com`
- PostgreSQL puerto: `5432`
- PostgreSQL base de datos: `userdb`
- PostgreSQL usuario: `postgres`
- Puerto publico de la aplicacion: `8080`

El EC2 debe tener Java 25 instalado y el puerto `8080` habilitado en el security group.
