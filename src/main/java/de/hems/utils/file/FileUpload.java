package de.hems.utils.file;

import java.util.function.Function;

public class FileUpload {
    TrippleFunction<byte[], String, String, UploadedFile> upload;

    public FileUpload(TrippleFunction<byte[], String, String, UploadedFile> uploadFile) {
        upload = uploadFile;
    }

    public UploadedFile upload(byte[] file, String type, String name) {
        return upload.apply(file, type, name);
    }
}
