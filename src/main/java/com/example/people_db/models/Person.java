package com.example.people_db.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity // This tells Hibernate to make a table out of this class
//@Table(name = "mytable")
    public class Person {
        @Id
        @Column(name = "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        @Length(message = "имя дожно быть от 2 до 30 символов", min = 2, max = 30)
        @Column(name = "name")
        @NotEmpty(message="имя не должно быть пустным")
        private String name;

        @NotNull(message = "возраст не должен быть пустым")
        @Max(message = "серьезно?", value = 100)
        @Min(message = "маленький возраст", value = 18)
        @Column(name = "age")
        private Integer age;


        @NotBlank(message = "email не должен быть пустым")
        @Email(message = "неправильно введен email")
        @Column(name = "email")
        private String email;

        public Person() {
        }

        public Person(String name, Integer age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public Integer getId() {
            return id;
        }
        public void setId(Integer id){
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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
}
