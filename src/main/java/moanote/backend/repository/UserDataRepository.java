package moanote.backend.repository;

import moanote.backend.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserDataRepository extends JpaRepository<UserData, UUID> {
    UserData findByUsername(String username);
}