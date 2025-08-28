package api.loginjson.repository;

import api.loginjson.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class JsonUserRepository {

    private static final String JSON_FILE = "users.json";
    private List<User> users;

    public JsonUserRepository() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource(JSON_FILE);
            this.users = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error cargando usuarios desde JSON", e);
        }
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

}
