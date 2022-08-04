package com.sanket.designparkinglot.exceptions;

import java.util.Objects;

public class EntityAlreadyExistsException extends Throwable {

    public EntityAlreadyExistsException(String entityName) {
        super("entity already exist " + entityName);
    }
}
