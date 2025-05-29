package grades;

public class NotaDTO {
    private String studentName;
    private double nota;
    private String temaid;
    private String profesor;

    public NotaDTO(String studentName, double nota, String temaid, String profesor) {
        this.studentName = studentName;
        this.nota = nota;
        this.temaid = temaid;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "NotaDTO{" +
                "studentName='" + studentName + '\'' +
                ", nota=" + nota +
                ", temaid='" + temaid + '\'' +
                ", profesor='" + profesor + '\'' +
                '}';
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getTemaid() {
        return temaid;
    }

    public void setTemaid(String temaid) {
        this.temaid = temaid;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}


