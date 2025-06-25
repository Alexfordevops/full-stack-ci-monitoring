package Api.Core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class DockerSecretsConfig {

    @Value("${SPRING_DATASOURCE_PASSWORD_FILE:}")
    private String dbPasswordPath;

    @Value("${JWT_SECRET_FILE:}")
    private String jwtSecretPath;

    public static String DB_PASSWORD;
    public static String JWT_SECRET;

    @PostConstruct
    public void init() throws IOException {
        if (!dbPasswordPath.isEmpty()) {
            DB_PASSWORD = Files.readString(Paths.get(dbPasswordPath)).trim();
            System.setProperty("spring.datasource.password", DB_PASSWORD);
        }
        if (!jwtSecretPath.isEmpty()) {
            JWT_SECRET = Files.readString(Paths.get(jwtSecretPath)).trim();
            System.setProperty("jwt.secret", JWT_SECRET);
        }
    }
}
