package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Attachment;
import uz.pdp.apelsinspringapi.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findByAttachment(Attachment attachment);

    AttachmentContent findByAttachment_Uuid(UUID id);
}
