/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.data;

/**
 *
 * @author Nes
 */
public final class SearchWizard extends Object {

    private Object searchItem = null;
    private Object field = null;
    private boolean wholeWords = true;
    private boolean matchCase = false;

    public Object getField() {
        return field;
    }

    public void setField(Object field) {
        this.field = field;
    }

    public boolean isMatchCase() {
        return matchCase;
    }

    public void setMatchCase(boolean matchCase) {
        this.matchCase = matchCase;
    }

    public Object getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(Object searchItem) {
        this.searchItem = searchItem;
    }

    public boolean isWholeWords() {
        return wholeWords;
    }

    public void setWholeWords(boolean wholeWords) {
        this.wholeWords = wholeWords;
    }
}
