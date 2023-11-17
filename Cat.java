package MPP1.src.ppp.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Cat {
    private final String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void meow() {
        System.out.println("Meow meow");
    }

    private void privateMethod() {
        System.out.println("can you call this private method?");
    }

    public static void publicStaticMethod() {
        System.out.println("this is static and public");
    }

    private static void privateStaticMethod() {
        System.out.println("how to call a private static method");
    }
}

class MainDemo {
    public static void main(String[] args) throws Exception {

       Cat myCat=new Cat("Sandra",5);
       myCat.meow();
       Field[]catFields=myCat.getClass().getDeclaredFields();
       for(Field field:catFields){
           field.setAccessible(true);
          // System.out.println(field.getName());
           
           if(field.getName().equals("name")){
               field.set(myCat,"Jimmy");
               //changing a private and final field
           }
       }

       Method[]catMethods=Cat.class.getDeclaredMethods();

       for(Method method:catMethods){
          // System.out.println(method.getName());

           if(method.getName().equals("privateMethod")){
               method.setAccessible(true);
               method.invoke(myCat);
           }

           if(method.getName().equals("privateStaticMethod")){
               method.setAccessible(true);
               method.invoke(myCat);
           }
       }

       Constructor[] constructors=myCat.getClass().getDeclaredConstructors();
       for(Constructor constructor:constructors){
           System.out.println(constructor.getName());
       }
        System.out.println(myCat.getName());
    }
}
