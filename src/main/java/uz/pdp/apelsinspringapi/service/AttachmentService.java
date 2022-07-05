package uz.pdp.apelsinspringapi.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.entity.Attachment;
import uz.pdp.apelsinspringapi.entity.AttachmentContent;
import uz.pdp.apelsinspringapi.repository.AttachmentContentRepository;
import uz.pdp.apelsinspringapi.repository.AttachmentRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final AttachmentContentRepository attachmentContentRepository;

    private final Path root = Paths.get("D:\\backend G7 Lessons\\TeacherProjecs\\apelsin-spring-api\\src\\main\\resources\\upload");

    @SneakyThrows
    public ApiResponse uploadFileSystem(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        Attachment save = null;
        while (fileNames.hasNext()) {
            Attachment attachment = new Attachment();

            MultipartFile file = request.getFile(fileNames.next());
            attachment.setFileName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());

            Files.copy(file.getInputStream(),this.root.resolve(file.getOriginalFilename()));

            attachment.setUrl(this.root + file.getOriginalFilename());

            save = attachmentRepository.save(attachment);
        }
        return ApiResponse.builder().success(true).message(save.getFileName() + " nomli fayl saqlandi").build();
    }

    @SneakyThrows
    public ApiResponse saveIntoDb(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());
            if (!file.isEmpty() || file != null ) {
                Attachment attachment = new Attachment();
                attachment.setContentType(file.getContentType());
                attachment.setSize(file.getSize());
                attachment.setFileName(file.getOriginalFilename());

                Attachment save = attachmentRepository.save(attachment);

                AttachmentContent content = new AttachmentContent();
                content.setAttachment(save);
                content.setBytes(file.getBytes());

                attachmentContentRepository.save(content);

            }
        }
        return ApiResponse.builder().success(true).message("Chotki").build();
    }

    public ResponseEntity<?> downloadDB(UUID id) {
        //info
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Bunday id lisi topilmadi"));

        AttachmentContent byAttachment = attachmentContentRepository.findByAttachment(attachment);
        AttachmentContent byAttachment_id = attachmentContentRepository.findByAttachment_Uuid(id);


        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(byAttachment.getBytes());
    }
}
