/*
 * To change this template(); public void  choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.data;

import java.awt.event.ActionEvent;

/**
 *
 * @author Nes
 */
public interface iResultSet {

    public void close(ActionEvent e);

    public void deleteRow(ActionEvent e);

    public boolean first(ActionEvent e);

    public void insertRow(ActionEvent e);

    public boolean isAfterLast(ActionEvent e);

    public boolean isBeforeFirst(ActionEvent e);

    public boolean isClosed(ActionEvent e);

    public boolean isFirst(ActionEvent e);

    public boolean isLast(ActionEvent e);

    public boolean last(ActionEvent e);

    public boolean next(ActionEvent e);

    public boolean previous(ActionEvent e);

    public boolean rowDeleted(ActionEvent e);

    public boolean rowInserted(ActionEvent e);

    public boolean rowUpdated(ActionEvent e);

    public boolean wasNull(ActionEvent e);
}
