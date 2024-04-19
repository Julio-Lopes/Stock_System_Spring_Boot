package br.com.springboot.model;

import org.hibernate.validator.constraints.br.CNPJ;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o nome fantasia do fornecedor")
    @Size(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Informe a razão social do fornecedor")
    @Size(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String companyName;

    @Column(length = 18)
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;

    @Column(length = 14)
    private String telephone;

    @Column(length = 15)
    private String cellphone;

    @Column(length = 50)
    @Email
    private String email;
    
    private boolean enabled;

    public Supplier(){
        this.enabled = true;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}