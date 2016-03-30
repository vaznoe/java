package com.company.app;

import java.util.stream.Stream;

import static com.company.app.TailCalls.call;
import static com.company.app.TailCalls.done;

interface TailCall<T>{
    TailCall<T> apply();
    default boolean isComplete(){return false;}
    default T result() {throw new Error("not implemented");}
    default T invoke(){
//        System.out.println("TailCall.invoke() this="+this.getClass().getSimpleName());
        return Stream.iterate(this, TailCall::apply)
                .filter(TailCall::isComplete)
                .findFirst()
                .get()
                .result();
    }
}
class TailCalls{
    public static <T> TailCall<T> call(final TailCall<T> nextCall){
        return nextCall;
    }
    public static <T> TailCall<T> done(final T value){
        return new TailCall<T>() {
            @Override public boolean isComplete(){return true;}
            @Override public T result() {return value;}
            @Override public TailCall<T> apply() {
                throw new Error("not implemented");
            }
        };
    }
}
public class App
{
    static int factorialRec(final int number){
        if(number == 1){
            return number;
        }
        else{
            return number * factorialRec(number - 1);
        }
    }
    static TailCall<Integer> factorialTailRec(final int factorial, final int number){
        if(number == 1){
            return done(factorial);
        }
        else{
            return call(() -> factorialTailRec(factorial * number, number - 1));
        }
    }
    static int factorial(final int number){
        return factorialTailRec(1, number).invoke();
    }
    public static void main( String[] args )
    {
        System.out.println("#factorialRecTest");
        factorialRecTest();
        System.out.println("#factorialTailRecTest");
        factorialTailRecTest();

        System.out.println("#streamTest");
        streamTest();
    }

    private static void factorialTailRecTest() {
        System.out.println(factorial(5));
        System.out.println(factorial(20000));
    }

    private static void factorialRecTest() {
        System.out.println(factorialRec(5));
        try{
            System.out.println(factorialRec(20000));
        }
        catch (StackOverflowError e){
            System.out.println(e);
        }
    }
    private static void streamTest() {
        System.out.println("##Stream.iterate TailCall");
        TailCall<Integer> tailCall = () -> {
            System.out.println("TailCall.apply() returns a new TailCall");
            return new TailCall<Integer>() {
                @Override
                public TailCall<Integer> apply() {
                    return null;
                }
            };
        };
        Stream<TailCall<Integer>> tailCalls = Stream.iterate(tailCall, TailCall::apply)
                .limit(3);
        tailCalls.forEach(t -> {
            if(t != null){
                t.apply();
            }
            else{
                System.out.println("t is null");
            }
        });
        /*
        iterate() takes two arguments: a seed and a function
        A seed is the first element of the stream. The second element is generated by applying the function to the first
        element. The third element is generated by applying the function on the second element, and so on.
         */
        Stream<Integer> numbers = Stream.iterate(1, n -> n * 2)
                .limit(3);
        numbers.forEach(n -> System.out.printf("%s ", n));
        System.out.println("");
        Stream<Integer> negativeNumbers = Stream.iterate(-1, n -> n * 2)
                .limit(3);
        negativeNumbers.forEach(n -> System.out.printf("%s ", n));
        System.out.println("");
    }
}
/*
output:
#factorialRecTest
120
java.lang.StackOverflowError
#factorialTailRecTest
120
0
#streamTest
##Stream.iterate TailCall
TailCall.apply() returns a new TailCall
TailCall.apply() returns a new TailCall
t is null
1 2 4
-1 -2 -4
 */
