package com.example.ch5.di;

import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

interface Meat {
    String getName();
}

class Beef implements Meat {
    public String getName() {
        return "소고기";
    }
}

class Pork implements Meat {
    public String getName() {
        return "돼지고기";
    }
}

class Chicken implements Meat {
    public String getName() {
        return "닭고기";
    }
}

class Chef {
    private Meat meat;

    public Chef(Meat meat) {
        this.meat = meat;
    }

    public void cook() {
        System.out.println(meat.getName() + " 요리를 시작합니다.");
    }
}

public class WithoutDependencyInversion {
    public static void main(String[] args) {
        Meat beef = new Beef();
        Meat pork = new Pork();
        Meat chicken = new Chicken();
        Chef chef = new Chef(chicken);
        chef.cook();
    }
}
