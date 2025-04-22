package moanote.backend.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class NoteUserDataId implements Serializable {

  @EqualsAndHashCode.Include
  private Note note;

  @EqualsAndHashCode.Include
  private UserData user;
}
