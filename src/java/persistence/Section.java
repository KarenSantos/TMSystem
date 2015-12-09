/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

/**
 *
 * @author karensantos
 */
public enum Section{

    FALL("F"), WINTER("W"), SUMMER("S");

    private String section;

    private Section(String s) {
        section = s;
    }

    public String getSection() {
        return section;
    }

}
