import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomSorting {
    public static void main(String[] args) {
        List<Person> li = new ArrayList<Person>();
        li.add(new Person("Ali", 35));
        li.add(new Person("Veli", 25));
        li.add(new Person("Hasan", 27));

        Collections.sort(li);

        for (Person p : li) {
            System.out.println(p.toString());
        }
    }

    public static class Person implements Comparable<Person> {
        private Integer age;
        private String name;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return "Benim Adım: " + this.name + " Benim yaşım: " + this.age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Person otherPerson) {
            return this.age.compareTo(otherPerson.getAge());
        }
    }

}
