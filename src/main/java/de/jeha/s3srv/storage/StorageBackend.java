package de.jeha.s3srv.storage;

import de.jeha.s3srv.errors.BadDigestException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public interface StorageBackend {

    List<S3Bucket> listBuckets();

    void createBucket(String bucket);

    boolean existsBucket(String bucket);

    void deleteBucket(String bucket);

    S3Object createObject(String bucket, String key, InputStream contentStream, int contentLength, String expectedMD5,
                          String contentType)
            throws IOException, BadDigestException;

    boolean existsObject(String bucket, String key);

    List<S3Object> listObjects(String bucket);

    S3Object getObject(String bucket, String key);

}
