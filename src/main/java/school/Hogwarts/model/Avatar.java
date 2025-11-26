package school.Hogwarts.model;

import jakarta.persistence.*;


import java.util.Objects;

@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private Long fileSize;
    private String mediaType;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Avatar() {}

    public Avatar(String name, String url, Long fileSize, String mediaType, Student student) {
        this.name = name;
        this.url = url;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.student = student;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return Objects.equals(id, avatar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
