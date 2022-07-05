package uz.pdp.apelsinspringapi.entity;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AttachmentContent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @OneToOne
    private Attachment attachment;
    private byte[] bytes;
}
