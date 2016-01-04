package com.company.app;

import java.util.HashMap;
import java.util.Map;

/*
Unformat toString() method
After reading the comment, programmers who produce code or persistent data that depends on the details of the format
will have no one but themselves to blame when the format is changed.
 */
final class PhoneNumber{
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    private static void rangeCheck(int arg, int max, String name){
        if(arg < 0 || arg > max){
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof PhoneNumber)) return false;
        PhoneNumber pn = (PhoneNumber) o;
        return pn.lineNumber == lineNumber && pn.prefix == prefix && pn.areaCode == areaCode;
    }
    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + areaCode;
        result = 31 * result + prefix;
        result = 31 * result + lineNumber;
        return result;
    }
    /*
    Returns a brief description of this portion. The exact details of the representation are unspecified and subject to
    change but the following may be regarded as typical:
     */
    @Override
    public String toString(){
        return String.format("(%d %d %d", areaCode, prefix, lineNumber);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny");
        System.out.println(m);
    }
}
/*
output:
{(707 867 5309=Jenny}
 */
