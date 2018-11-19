package com.company.property;

public abstract class Item {
    String name;
    int price;
    public abstract boolean disposable();
    public void buy(){
        System.out.println("购买");
    }
    public void effect() {
        System.out.println("物品使用后，可以有效果");
    }

}
