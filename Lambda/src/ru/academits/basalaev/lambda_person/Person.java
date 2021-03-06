package ru.academits.basalaev.lambda_person;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Значение возраста должно быть положительным числом, переданное значение age = " + age);
        }

        if (name == null) {
            throw new IllegalArgumentException("Необходимо заполнить поле имя");
        }

        if (name.length() <= 1) {
            throw new IllegalArgumentException("Имя дожно состоять из более чем одного символа, введено символов: " + name.length());
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}