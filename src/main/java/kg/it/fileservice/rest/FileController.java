package kg.it.fileservice.rest;

import kg.it.fileservice.models.FileModel;
import kg.it.fileservice.response.FileException;
import kg.it.fileservice.response.ResponseModel;
import kg.it.fileservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController extends BaseController {
    private final FileService fileService;

    @PostMapping
    public ResponseModel<Boolean> save(@RequestBody FileModel fileModel) throws FileException, IOException {
        return successResponse(fileService.save(fileModel));
    }

    @GetMapping
    public ResponseModel<byte[]> get(@RequestBody FileModel fileModel) throws FileException, IOException {
        return successResponse(fileService.get(fileModel));
    }

    @PutMapping
    public ResponseModel<Boolean> update(@RequestBody FileModel fileModel) throws FileException, IOException {
        return successResponse(fileService.update(fileModel));
    }

    @DeleteMapping
    public ResponseModel<Boolean> delete(@RequestBody FileModel fileModel) throws FileException, IOException {
        return successResponse(fileService.delete(fileModel));
    }
}
