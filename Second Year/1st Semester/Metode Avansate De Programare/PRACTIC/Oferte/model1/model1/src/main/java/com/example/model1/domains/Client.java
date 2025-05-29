package com.example.model1.domains;

public class Client extends Entity<Long> {

    private Long clientId;
    private String name;
    private Integer fidelityGrade;
    private Integer age;
    private Hobbies hobbies;

    public Client(Long clientId, String name, Integer fidelityGrade, Integer age, Hobbies hobbies) {
        super(clientId);
        this.clientId = clientId;
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.age = age;
        this.hobbies = hobbies;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public Integer getFidelityGrade() {
        return fidelityGrade;
    }

    public Integer getAge() {
        return age;
    }

    public Hobbies getHobbies() {
        return hobbies;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", fidelityGrade=" + fidelityGrade +
                ", age=" + age +
                ", hobbies=" + hobbies +
                '}';
    }
}
