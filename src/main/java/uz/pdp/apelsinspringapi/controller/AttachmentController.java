package uz.pdp.apelsinspringapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.entity.Attachment;
import uz.pdp.apelsinspringapi.repository.AttachmentRepository;
import uz.pdp.apelsinspringapi.service.AttachmentService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentRepository attachmentRepository;

    private final AttachmentService attachmentService;

    private final Path root = Paths.get("D:\\backend G7 Lessons\\TeacherProjecs\\apelsin-spring-api\\src\\main\\resources\\upload");

    @PostMapping("/upload")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.uploadFileSystem(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) {

        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new RuntimeException("File Topilmadi"));

//        File file = new File(attachment.getUrl());

        Path file = root.resolve(attachment.getFileName());
        Resource resource = new UrlResource(file.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(resource);
    }

    @PostMapping("/uploadDb")
    public ResponseEntity<?> uploadDB(MultipartHttpServletRequest request){
        ApiResponse response = attachmentService.saveIntoDb(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable UUID id){
        return attachmentService.downloadDB(id);
    }
}
