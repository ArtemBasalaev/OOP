package ru.academits.basalaev.lymbda_main;

import ru.academits.basalaev.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 25),
                new Person("Ivan", 13),
                new Person("Denis", 30),
                new Person("Sergey", 22),
                new Person("Pyotr", 12),
                new Person("Anton", 43)
        );

        //А
        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        //Б
        String uniqueNames = uniqueNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("В списке содержатся следующие уникальные имена:");
        System.out.println(uniqueNames);

        //В
        int limitAge = 18;

        List<Person> personsYoungerThanLimitAge = persons.stream()
                .filter(person -> person.getAge() < limitAge)
                .collect(Collectors.toList());

        personsYoungerThanLimitAge.stream()
                .mapToInt(Person::getAge)
                .average()
                .ifPresent(averageAge -> System.out.println("Средний возраст: " + averageAge));

        //Г
        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        //Д
        int minAge = 20;
        int maxAge = 45;

        List<Person> personsInRangeAges = persons.stream()
                .filter(person -> person.getAge() >= minAge && person.getAge() <= maxAge)
                .collect(Collectors.toList());

        String personsNamesDescendingByAge = personsInRangeAges.stream()
                .sorted((person1, person2) -> person2.getAge() - person1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.printf("Список людей возраст, которых находится в диапазоне от %d до %d, в порядке убывания их возраста: ", minAge, maxAge);
        System.out.println(personsNamesDescendingByAge);
    }
}