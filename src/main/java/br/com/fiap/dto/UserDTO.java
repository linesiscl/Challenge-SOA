package br.com.fiap.dto;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String riskProfile;

    // Construtores
    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String riskProfile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.riskProfile = riskProfile;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRiskProfile() {
        return riskProfile;
    }

    public void setRiskProfile(String riskProfile) {
        this.riskProfile = riskProfile;
    }

    // toString()
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", riskProfile='" + riskProfile + '\'' +
                '}';
    }
}
