package com.company.app;

interface UnaryFunction<T>{
    T apply(T arg);
}
// Generic singleton facotry method
public class App
{
    // Generic singleton factory pattern
    private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {
        public Object apply(Object arg){
            return arg;
        }
    };

    // IDENTIFY_FUNCTION is stateless and its type parameter is
    // unbounded so it's safe to share one instance across all types
    public static <T> UnaryFunction<T> identityFunction(){
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }

    public static void main( String[] args )
    {
        String[] strings = {"jute", "hemp", "nylon"};
        UnaryFunction<String> sameString = identityFunction();
        for(String s : strings){
            System.out.println(sameString.apply(s));
        }

        // The abstract class Number is the superclass of classes BigDecimal, BigInteger, Byte, Double, Float, Integer,
        // Long and Short.
        Number[] numbers = {1, 2.0, 3L};
        UnaryFunction<Number> sameNumber = identityFunction();
        for(Number n : numbers){
            System.out.println(sameNumber.apply(n));
        }
    }
}
