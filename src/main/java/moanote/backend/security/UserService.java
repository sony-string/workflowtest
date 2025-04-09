package moanote.backend.service;

import com.github.f4b6a3.uuid.UuidCreator;
import moanote.backend.entity.UserData;
import moanote.backend.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserDataRepository userDataRepository;

  public UserData createUser(String username, String password) {
    UserData userData = new UserData();
    userData.setUsername(username);
    userData.setPassword(password);
    userData.setId(UuidCreator.getTimeOrderedEpoch());
    return userDataRepository.save(userData);
  }

  public UserData findByUsername(String username) {
    return userDataRepository.findByUsername(username);
  }
}