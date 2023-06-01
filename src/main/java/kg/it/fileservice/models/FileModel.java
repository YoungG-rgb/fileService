package kg.it.fileservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileModel {

    String name;
    String system;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    LocalDate fileDate;

    String fileExtension;
    String mime;

    String base64binaryData;
    byte [] data;

    public String getFileExtension() {
        return switch (fileExtension) {
            case "application/pdf" -> "pdf";
            case "image/jpeg", "image/jpg" -> "jpg";
            case "video/mp4" -> "mp4";
            default -> fileExtension;
        };
    }

    public String getSecondPath() {
        return switch (mime) {
            case "application/octet-stream", "application/pdf", "application/zip", "application/xml" -> "attachment";
            case "image/jpeg", "image/jpg", "image/png", "image/svg" -> "images";
            case "audio/mpeg", "audio/vorbis" -> "audio";
            case "video/mp4" -> "video";
            default -> "default";
        };
    }


    public byte[] getData() {
        if (base64binaryData != null) return Base64.getDecoder().decode(base64binaryData);
        return data;
    }

    public String getNameWithExtension(){
        return name + "." + fileExtension;
    }
}
