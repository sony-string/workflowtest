package moanote.backend.repository;

import com.github.f4b6a3.uuid.util.UuidComparator;
import jakarta.transaction.Transactional;
import moanote.backend.BackendApplication;
import moanote.backend.entity.Note;
import moanote.backend.entity.UserData;
import moanote.backend.service.NoteService;
import moanote.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application.properties")
@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BackendApplication.class)
class NoteRepositoryTest {

  @Autowired
  private NoteRepository noteRepository;

  @Autowired
  private NoteService noteService;

  @Autowired
  private UserService userService;

  @Test
  void findNotesByOwnerTest() {
    UserData user = userService.createUser("testUser", "testPassword");
    ArrayList<Note> notes = new ArrayList<>();
    notes.add(noteService.createNote(user.getId()));
    notes.add(noteService.createNote(user.getId()));
    notes.add(noteService.createNote(user.getId()));

    List<Note> foundNotes = noteRepository.findNotesByOwner(user.getId());

    assertEquals(3, foundNotes.size());
    notes.sort((o1, o2) -> UuidComparator.defaultCompare(o1.getId(), o2.getId()));
    foundNotes.sort((o1, o2) -> UuidComparator.defaultCompare(o1.getId(), o2.getId()));
    assertEquals(notes.get(0).getId(), foundNotes.get(0).getId());
    assertEquals(notes.get(1).getId(), foundNotes.get(1).getId());
    assertEquals(notes.get(2).getId(), foundNotes.get(2).getId());
  }

}