package com.icode.data.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

import com.icode.data.iDataOutput;
import com.icode.validation.ValidationEngine;
import com.icode.validation.iMessages;
import com.icode.validation.iValidator;
import com.icode.validation.validators.annotation.Valid.ValidateModel;

/**
 *
 * @author Nes
 * @param 
 */
@ValidateModel
@SuppressWarnings("unchecked")
public abstract class BaseModel implements Serializable, iValidator, iDataOutput {
	private static final long serialVersionUID = 1L;
	
    public BaseModel() {}

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getChangeSupport().addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getChangeSupport().removePropertyChangeListener(listener);
    }

    /**
     *
     * @return
     */
    public String getMyName() {
        return BaseModel.this.getClass().getName();
    }

    public abstract Integer getId();
        
    public void validate() {
        ValidationEngine.validate(this);
    }

    /**
     *
     * @param propertyName
     * @param propertyValue
     * @param messages
     */
    public void validateProperty(String propertyName, Object propertyValue, iMessages messages) {
        ValidationEngine.validateProperty(BaseModel.this.getClass(), propertyName, propertyValue, messages);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaseModel)) {
            return false;
        }
        BaseModel other = (BaseModel) object;
        if ((this.hashCode() == 0 && other.hashCode() != 0) || (this.hashCode() != 0 && this.hashCode() != (other.hashCode()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[id=" + this.getId() + "]";
    }

    public int compareTo(BaseModel other) {
        return (this.hashCode() < other.hashCode() ? -1 : (this.hashCode() == other.hashCode() ? 0 : 1));
    }

    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private List<String> counterFL;

    public String toJSON() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String toXLS() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String toCSV() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the changeSupport
     */
    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }
}