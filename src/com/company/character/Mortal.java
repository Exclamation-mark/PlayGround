package com.company.character;
/**
*adj致命的;不共戴天的;终有一死的;极度的
*n.凡人，人类
* */
public interface Mortal {
    void die();
    default  void revive() {
        System.out.println("本英雄复活了");
    }
}
