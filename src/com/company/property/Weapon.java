package com.company.property;

public class Weapon extends Item{
    private int damage;

    public Weapon(int damage) {
        this.damage = damage;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean disposable() {
        System.out.println("武器还在");
        return false;
    }
}
