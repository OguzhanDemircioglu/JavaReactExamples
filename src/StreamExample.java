import java.util.*;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {

        List<String> arr = new ArrayList<>();
        arr.add("asasd as");
        arr.add("awd ");
        arr.add("123eaaaa");
        arr.add("awd");
        arr.add("123");

        System.out.println(mapperToUpperCase(arr));

        System.out.println();
        getTotalNujmberOfArraysBiggerThanFive(arr);

        List<List<Person>> roughArray = new ArrayList<>();
        List<Person> persons = getPeople();
        roughArray.add(persons);

        System.out.println();
        getBiggestAgeOfCompany(persons);

        List<Integer> intArr = new ArrayList<>();
        System.out.println("\n" + sumTheArray(intArr));

        System.out.println("\n" +getPeoplesAgeBiggerThan18byTheirNames(persons));

        System.out.println("\n" +getPeopleWhichIsOlderThan18ByHashMap(persons));

        System.out.println("\n" + getNamesWithCommaFinishWithDot(persons));

    }

    private static List<Person> getPeople() {
        List<Person> persons = new ArrayList<>();

        Person osman = new Person();
        osman.setName("Osman");
        osman.setSurName("Yaylaci");
        osman.setAge(13);
        osman.setBirthPlace("Trabzon");

        Person bilal = new Person();
        bilal.setSurName("Kara");
        bilal.setName("Bilal");
        bilal.setAge(61);
        bilal.setBirthPlace("Trabzon");

        Person oguz = new Person();
        oguz.setName("Oguz");
        oguz.setSurName("Gel");
        oguz.setAge(31);
        oguz.setBirthPlace("Trabzon");

        Person oguz1 = new Person();
        oguz1.setName("Oguz");
        oguz1.setSurName("Gel1");
        oguz1.setAge(31);
        oguz1.setBirthPlace("Trabzon1");

        persons.add(osman);
        persons.add(bilal);
        persons.add(oguz);
        persons.add(oguz1);
        return persons;
    }

    public static List<String> mapperToUpperCase(List<String> arr) {
        return arr.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static void getTotalNujmberOfArraysBiggerThanFive(List<String> arr){
        System.out.println(
                arr.stream().filter(s -> s.length()>5).mapToInt(String::length).count()
        );
    }

    public static void getBiggestAgeOfCompany(List<Person> arr){
        System.out.println(
                arr.stream().map(Person::getAge)
                        .max(Comparator.comparing(Integer::intValue))
                        .orElse(null));

        System.out.println(
                arr.stream().max(Comparator.comparingInt(Person::getAge))
                        .stream().map(Person::getAge).findAny()
                        .orElse(null)
        );

        System.out.println(
                arr.stream().max(Comparator.comparingInt(Person::getAge))
                        .get().birthPlace);

        System.out.println(
                arr.stream().max(Comparator.comparingInt(Person::getAge))
                        .map(person -> person.surName)
                        .orElse("null")
        );
    }

    public static int sumTheArray(List<Integer> arr){
        for (int i = 0; i <10 ; i++) {
            arr.add(i);
        }
        return arr.stream().reduce(Integer::sum).orElseThrow(NullPointerException::new);
    }

    public static Set<String> getPeoplesAgeBiggerThan18byTheirNames(List<Person> arr){
        return arr.stream()
                        .filter(person -> person.getAge()>18).map(Person::getName)
                            .collect(Collectors.toSet());
    }

    public static Map<Boolean, List<Person>> getPeopleWhichIsOlderThan18ByHashMap(List<Person> arr){

        System.out.println("\n"+
                arr.stream()
                        .collect(
                                Collectors.groupingBy(Person::getAge, Collectors.toList()
                                ))
        );

        return arr.stream()
                .collect(
                        Collectors.groupingBy(person -> person.getAge() > 18, Collectors.toList()
                        ));
    }

    public static String getNamesWithCommaFinishWithDot(List<Person> arr){

        System.out.println("\nNames : " +
                arr.stream().map(Person::getName).collect(Collectors.joining(","))+ "."
        );

        return "Names : "+arr.stream().map(Person::getName).reduce((i,sum) -> i + ", " +sum).orElse("")+ ".";
    }

}
