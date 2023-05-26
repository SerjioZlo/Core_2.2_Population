import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        int countNotEighteen = Math.toIntExact(persons.stream()
                .filter(person -> person.getAge() < 18)
                .count());
        System.out.println("Лица не достигшие 18 лет: " + countNotEighteen);

        List<String> conscripts = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getName)
                .collect(Collectors.toList());

        for (String name : conscripts){
            System.out.print(name + " ");
        }

        List<String> workPeople =persons.stream()
                .filter(person -> person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() <= 60 || person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                //.filter(person -> person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Работающие люди:");
        for (String surname : workPeople){
            System.out.print(surname + " ");
        }
    }
}