package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import java.lang.reflect.Constructor;

class Dependency{
}
class Sealed {
    private final Dependency dep;

    public Sealed(Dependency dep) {
        this.dep = dep;
        System.out.println("#Sealed constructor");
    }
}
class SealedModule extends AbstractModule{
    @Override
    protected void configure() {
        //# Bind directly to a constructor of Sealed
        bind(Sealed.class).toConstructor(sealedConstructor());
    }
    private Constructor<Sealed> sealedConstructor(){
        System.out.println("#SealedModule.sealedConstructor()");
        try{
            //# Resolve the constructor we want to inject
            return Sealed.class.getConstructor(Dependency.class);
        }catch (NoSuchMethodException e){
            System.out.println(e);
            return null;
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new SealedModule()).getInstance(Sealed.class);
    }
}
/*
output: external metadata allows you to inject classes in sealed code easily.
#SealedModule.sealedConstructor()
#Sealed constructor
 */
