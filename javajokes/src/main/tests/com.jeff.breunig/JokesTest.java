package com.jeff.breunig;

public class JokerTest {
    @Test
    public void test() {
        Joker joker = new Joker();
        assert joker.getJoke().length() != 0;
    }
}