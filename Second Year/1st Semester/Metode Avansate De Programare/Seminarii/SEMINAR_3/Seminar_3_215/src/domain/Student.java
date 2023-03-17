package domain;

import java.util.Objects;

public class Student {
    private String nume;
    private float media;

    public Student(String nume, float media) {
        this.nume = nume;
        this.media = media;
    }

    public String toString() {
        return nume+" "+media;
    }

    public int hashCode() {
        return Objects.hash(nume, media);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public int getMediaRotunjita() {
        return Math.round(media);
    }

    @Override
    public boolean equals(Object o) {
        // verifica daca este acelasi obiect, prin adresa
        if (this == o) return true;
        // daca obiectul este null sau clasa lui nu este Student,
        // nu sunt egale
        if (o == null || getClass() != o.getClass()) return false;
        // cast la student
        Student student = (Student) o;
        // compara proprietatile ca Studenti
        return Float.compare(student.media, media) == 0 // abs(x-y)<epsilon
                && Objects.equals(nume, student.nume);
    }
}
