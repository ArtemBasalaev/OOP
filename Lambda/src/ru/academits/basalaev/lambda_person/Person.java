package ru.academits.basalaev.lambda_person;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (age <= 0) {
            throw new IllegalArgumentException(("Значение возраста должно быть положительным числом, переданное значение age = " + age));
        }

        if (name == null || name.length() <= 1) {
            throw new IllegalArgumentException("Необходимо заполнить поле имя");
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