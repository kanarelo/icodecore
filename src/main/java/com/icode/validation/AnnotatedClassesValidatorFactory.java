package com.icode.validation;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;

import com.icode.util.Commons;
import com.icode.validation.ValidationEngine.iValidatorSupplier;
import com.icode.validation.validators.annotation.Valid.ValidateGroup;
import com.icode.validation.validators.annotation.Valid.ValidateModel;
import com.icode.validation.validators.annotation.Valid.Validator;

/**
 * <p>
 * The <tt>AnnotatedClassesValidatorFactory</tt> class is responsible for supplying
 * ModelValidators for classes that are annotated with validations. Therefore it can
 * create ModelValidators based on the standard set of validation validators.
 * </p>
 * <p>
 * A not standard validation Annotation can also integrated by registering a AnnotationValidatorCreator,
 * which creates <tt>iPropertyValidation</tt> for that annotation.
 * 
 * @see AnnotationValidatorCreator
 * @author roho
 *
 */
public class AnnotatedClassesValidatorFactory implements iValidatorSupplier {

    private static String validatorsPackage = Commons.packageNameConc(AnnotatedClassesValidatorFactory.class, "validators");
    private static final AnnotatedClassesValidatorFactory instance = new AnnotatedClassesValidatorFactory();
    private static final Logger logger = Logger.getLogger(AnnotatedClassesValidatorFactory.class.getName());

    static {
        ValidationEngine.setValidatorSupplier(instance);
        try {
            registerValidatorClasses();
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param clazz
     * @return
     * @see nl.knowlogy.validation.ValidationEngine$ValidatorSupplier#getValidator(java.lang.Class)
     */
    public iModelValidator getValidator(Class clazz) {
        iModelValidator ModelValidator = null;
        if (clazz.isAnnotationPresent(ValidateModel.class)) {
            ModelValidator = createModelValidator(clazz);
        }
        return ModelValidator;
    }

    private AnnotatedClassesValidatorFactory() {
    }

    /**
     *
     * @return
     */
    public static AnnotatedClassesValidatorFactory getInstance() {
        return instance;
    }
    private static List<Class<iPropertyValidation>> validatorsRawClassList;

    private static void registerValidatorClasses() throws ClassNotFoundException, IOException {
        logger.info("Registering Validators...");
        validatorsRawClassList = Commons.<iPropertyValidation>getClasses(validatorsPackage);
    }

    private synchronized iModelValidator createModelValidator(Class clazz) {
        iModelValidator modelValidator = new iModelValidatorImpl(clazz);
        Field[] fields = clazz.getDeclaredFields();
        //TODO: I just Want the beans's fields, not from ancestors, But what if the Entity is inherited??

        //Tsk Tsk, we only deal with Column field, no other; Ubaguzi!!!
        String propertyName;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                propertyName = field.getName();
                ValidateGroup groupAnnotation = field.getAnnotation(ValidateGroup.class);

                if (groupAnnotation != null) {
                    modelValidator.addPropertyToGroups(groupAnnotation.name(), propertyName);
                }

                Iterator<Class<iPropertyValidation>> validatorsItr = validatorsRawClassList.iterator();
                while (validatorsItr.hasNext()) {
                    Class<iPropertyValidation> validatorClass = validatorsItr.next();
                    if (validatorClass.isAnnotationPresent(Validator.class)) {
                        try {
                            Annotation annotation = field.getAnnotation((Class<Annotation>) validatorClass.getAnnotation(Validator.class).fieldAnnotation());
                            if (annotation != null) {
                                modelValidator.add(createValidator(validatorClass, propertyName, annotation));
                            }
                        } catch (NoSuchMethodException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return modelValidator;
    }

    private iPropertyValidation createValidator(Class<iPropertyValidation> validatorClass, String propertyName, Annotation annotation) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Constructor<iPropertyValidation> constructor = validatorClass.getConstructor(new Class[]{String.class, Annotation.class});
        return constructor.newInstance(propertyName, annotation);
    }
}
