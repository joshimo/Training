package inner_nested_local_classes;

public class OuterC extends OuterA {
}

class OuterC1 extends OuterA.NestedA {
}

class OuterC2 extends OuterA.InnerA {
    public OuterC2(OuterA outerA) {
        outerA.super();
    }
}