package inner_nested_local_classes;

public class Runner {

    public static void main(String... args) {

        /* Создание экземпляра вложенного класса NestedA класса OuterA */
        OuterA.NestedA nestedA = new OuterA.NestedA();

        /* Создание экземпляра внутреннего класса InnerA класса OuterA */
        OuterA.InnerA innerA = new OuterA().new InnerA();

        /* Создание экземпляра класса OuterC1, который наследует вложенный класс NestedA класса OuterA */
        OuterC1 outerC1 = new OuterC1();

        /* Создание экземпляра класса OuterC2, который наследует внутренний класс InnerA класса OuterA */
        OuterC2 outerC2 = new OuterC2(new OuterA());

        /* Вызов метода внутреннего класса InnerB1 класса OuterB */
        OuterB outerB = new OuterB();
        outerB.new InnerB1().printOtherString();

        /* Вызов метода вложенного класса NestedB1 класса OuterB */
        OuterB.NestedB1.printOwnStaticString();
    }

}
