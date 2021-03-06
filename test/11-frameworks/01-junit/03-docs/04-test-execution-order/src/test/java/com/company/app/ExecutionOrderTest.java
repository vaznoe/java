package com.company.app;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExecutionOrderTest {

  @Test
  public void testA() {
    System.out.println("first");
  }

  @Test
  public void testB() {
    System.out.println("second");
  }

  @Test
  public void testC() {
    System.out.println("third");
  }
}
/*
output:
first
second
third
 */
