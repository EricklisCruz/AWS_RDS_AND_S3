package com.awsstudies.estudos.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.awsstudies.estudos.model.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class S3Services {

    @Value("${bucketName}")
    private String nomeDobucket;
    private static final String URL = "https://s3.amazonaws.com/";
    private final AmazonS3 amazonS3;

    private final UserService userService;

    public S3Services(AmazonS3 amazonS3, UserService userService) {
        this.amazonS3 = amazonS3;
        this.userService = userService;
    }

    public String saveFile(MultipartFile arquivo) {
        String nomeOriginalDoArquivo = arquivo.getOriginalFilename();
        try {
            File arquivoConvertido = convertMultipartInFile(arquivo);
            amazonS3.putObject(nomeDobucket, nomeOriginalDoArquivo, arquivoConvertido);
            String urlDoArquivoS3 = URL + nomeDobucket + "/" + nomeOriginalDoArquivo;
            User user = new User();
            user.setUrlImage(urlDoArquivoS3);
            userService.update(userService.getCurrentUser().getId(), user);
            return urlDoArquivoS3;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> listAllFiles() {
        ListObjectsV2Result objectsList = amazonS3.listObjectsV2(nomeDobucket);
        List<String> files = objectsList.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
        return files;
    }

    private File convertMultipartInFile(MultipartFile file) throws IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();

        return convertFile;
    }
}
