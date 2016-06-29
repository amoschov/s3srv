package de.jeha.s3srv.api;

import de.jeha.s3srv.jaxb.InstantXmlAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
@XmlRootElement(name = "ListBucketResult")
public class ListBucketResult {

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "KeyCount")
    private Integer keyCount;

    @XmlElement(name = "MaxKeys")
    private Integer maxKeys;

    @XmlElement(name = "Contents")
    private List<ContentsEntry> objects = new ArrayList<>();

    public ListBucketResult() {
    }

    public ListBucketResult(String name, Integer keyCount, Integer maxKeys, List<ContentsEntry> objects) {
        this.name = name;
        this.keyCount = keyCount;
        this.maxKeys = maxKeys;
        this.objects = objects;
    }

    public static class ContentsEntry {

        private static final String STORAGE_CLASS_STANDARD = "STANDARD";

        @XmlElement(name = "Key")
        private String key;

        @XmlJavaTypeAdapter(InstantXmlAdapter.class)
        @XmlElement(name = "LastModified")
        private Instant lastModified;

        @XmlElement(name = "ETag")
        private String eTag;

        @XmlElement(name = "Size")
        private Integer size;

        @XmlElement(name = "StorageClass")
        private String storageClass = STORAGE_CLASS_STANDARD;

        public ContentsEntry(String key, Instant lastModified, String eTag, Integer size) {
            this.key = key;
            this.lastModified = lastModified;
            this.eTag = eTag;
            this.size = size;
        }
    }

}