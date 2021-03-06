/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package support;

import cucumber.runtime.java.guice.ScenarioScoped;

import nicebank.Account;

@ScenarioScoped
public class TestAccount extends Account {

    public TestAccount() {
        super(1234);
        saveIt();
    }
}
