package moanote.backend.dto;

import java.util.UUID;

public record CreateCollaborationSessionDTO(
    UUID sessionId,
    UUID noteId,
    String sessionCreateUserId
) {

}