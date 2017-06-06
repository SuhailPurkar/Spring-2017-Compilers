//Mark Vo
//HW3
//CS451
//This is used for testing parsing interface and classes with interfaces

public interface InterfaceTest extends AClass{
}
public interface InterfaceTest2 extends AClass, BClass {
}

public class RandomClass extends AnotherClass implements InterfaceTest {
}

public class Random2Class extends AnotherClass implements Interface1, Interface2, Interface3 {
}