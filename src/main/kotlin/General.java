
import events.Button;
import events.View;
import singleton.Person;
import strings.JMVJoinName;

import static events.ButtonKt.showOff;
import static strings.JMVJoinName.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class General {

    static Set<String> set = Stream.of("1", "7", "53")
                            .collect(Collectors.toCollection(HashSet::new));

    static List<Integer> list = Stream.of(1, 7, 53)
                                .collect(Collectors.toCollection(ArrayList::new));

    static Map<Integer, String> map = Stream.of(new Object[][]{
            {1, "one" }, {7, "seven"}, {53, "fifty-three"}
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

    public static void main(String[] args) throws InterruptedException {

        System.out.println(joinToString(set,"k","z","#"));
        System.out.println(joinToString(list));

        System.out.println(joinToStrings(set, "&", "&", "+"));
        System.out.println(joinToStrings(list, "&", "&", "+"));

        performOperation();
        performOperation();
        performOperation();
        reportOperation();

        System.out.println(lastChar("kotlin"));
        System.out.println(lastChar1("java"));
        System.out.println(lastChar2("javascript"));

        System.out.println(getLastChar("kotlin"));
        System.out.println(getLastChar("java"));
        System.out.println(getLastChar("javascript"));

        StringBuilder sb =  new StringBuilder("Kotlin?");
        setLastChar(sb, '!');
        System.out.println(getLastChar(sb));

        View view = new View();
        view.clicked();

        View button = new Button();
        button.clicked();

        Button b = new Button();
        b.showableOff();
        showOff(b);     //Extension method, should be call as an import method of ButtonKt class
        b.setFocus(true);
        b.clicked();

        Person per1 = new Person("Bob");
        Person per2 = new Person("Alice");
        Person per3 = new Person("George");
        Person per4 = new Person("Mihahl");
        List<Person> persons = Stream.of(per1, per2, per3, per4).collect(Collectors.toList());
        persons.sort(Person.NameComparator.INSTANCE);
        System.out.println(persons);

        companion.Person cmpP= new companion.Person("Alice",34);
        companion.Person person1 = companion.Person.Loader.fromJSON("Soula");
        companion.Person person2 = companion.Person.Loader.fromJSON("Voula");
        System.out.println(person1); System.out.println(person2);

        General general = new General();
        general.postponeComputation(1000, () -> System.out.print("42"));

        IGeneralImplementer igi = new IGeneralImplementer();
        igi.feed();
        System.out.println(igi.noise());
    }

    public void postponeComputation(int delay, Runnable runnable) throws InterruptedException{
        Thread.sleep(delay);
        runnable.run();
    }
}

interface IGeneral {
    void feed();
    default String noise() {
        return "niaou";
    }
}

class IGeneralImplementer implements IGeneral {
    @Override
    public void feed() {
        System.out.println("Feed!");
    }
}
