package kg.it.fileservice.service.impl;

import kg.it.fileservice.models.FileModel;
import kg.it.fileservice.response.FileException;
import kg.it.fileservice.service.FileService;
import kg.it.fileservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public boolean save(FileModel file) throws FileException, IOException {
        validateFile(file);

        String fullPath = getPathByFile(file);
        if (new File(fullPath + file.getNameWithExtension()).exists()) {
            throw new FileException(String.format("FILE ALREADY EXISTS WITH NAME: %s", file.getName()));
        }

        checkMkdirs(fullPath);
        return save(file, fullPath);
    }

    @Override
    public boolean update(FileModel file) throws FileException, IOException {
        validateFile(file);
        return save(file, getPathByFile(file));
    }

    @Override
    public boolean delete(FileModel file) throws FileException, IOException {
        validateFile(file);
        Files.delete(Paths.get(getPathByFile(file) + file.getNameWithExtension()));
        return true;
    }

    @Override
    public byte[] get(FileModel file) throws FileException, IOException {
        validateFile(file);
        return Files.readAllBytes(Paths.get(getPathByFile(file) + file.getNameWithExtension()));
    }

    private void validateFile(FileModel file) throws FileException {
        if (file.getSystem() == null) throw new FileException("Заполните имя системы");
        if (file.getFileDate() == null) throw new FileException("Заполните дату");
        if (file.getFileExtension() == null) throw new FileException("Заполните формат файла");
        if (file.getName() == null) throw new FileException("Заполните имя файла");
        if (file.getData() == null) throw new FileException("Приложите файл");
    }

    private String getPathByFile(FileModel fileModel) {
        StringJoiner pathJoiner = new StringJoiner("/");

        return pathJoiner
                .add(fileModel.getSystem())
                .add(fileModel.getSecondPath())
                .add(DateUtil.year(fileModel.getFileDate()))
                .add(DateUtil.month(fileModel.getFileDate()))
                .add(DateUtil.dayOfMonth(fileModel.getFileDate()))
                .toString();
    }

    private void checkMkdirs(String fullPath) {
        File directory = new File(fullPath);
        if (!directory.exists()) directory.mkdirs();
    }

    private boolean save(FileModel file, String path) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(file.getData());
            fileOutputStream.flush();
        }
        return true;
    }
}
