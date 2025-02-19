package imageserver.image.Service;

import imageserver.image.Configuration.FileUploadProperties;
import imageserver.image.Exception.FileDownloadException;
import imageserver.image.Exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadDownloadService {
    private Path fileLocation;

    @Autowired
    private RandMaker randMaker;

    @Autowired
    public FileUploadDownloadService(FileUploadProperties prop) {
        this.fileLocation = Paths.get(prop.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    public void setFileLocation(String date) {
        this.fileLocation = Paths.get(date)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    public String storeFile(MultipartFile file, boolean isit) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = null;
        String rand = randMaker.getKey(false, 20);
        try {
            // 파일명에 부적합 문자가 있는지 확인한다.
            if (fileName.contains(".."))
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);
            if (isit) {
                String str = fileName.substring(fileName.lastIndexOf('.'));
                rand = rand + str;
                targetLocation = this.fileLocation.resolve(rand);
            } else {
                targetLocation = this.fileLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            }

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return rand;

        } catch (Exception e) {
            throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", e);
        }
    }

    public void deleteFiles() throws IOException {
        File file = new File(String.valueOf(this.fileLocation));
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].delete()) {
                        System.out.println(files[i].getName() + " 삭제성공");
                    } else {
                        System.out.println(files[i].getName() + " 삭제실패");
                    }
                }
            }
        }
        else{
            System.out.println("해당 파일이 존재하지 않습니다.");
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
            }
        } catch (MalformedURLException e) {
            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
        }
    }

}