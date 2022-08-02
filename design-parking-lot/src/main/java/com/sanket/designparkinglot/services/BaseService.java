package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.models.BaseModel;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BaseService {

    public void setCreatedAt(BaseModel model, Date currentTimeStamp) {
        model.setCreatedAt(currentTimeStamp);
    }

    public void setModifiedAt(BaseModel model, Date currentTimeStamp) {
        model.setLastModifiedAt(currentTimeStamp);
    }

    public void setCreatedBy(BaseModel model) {
        String currentUser = "admin";
        model.setCreatedBy(currentUser);
    }

    public void setModifiedBy(BaseModel model) {
        String currentUser = "admin";
        model.setLastModifiedBy(currentUser);
    }

    public void setCreateModelDefaults(BaseModel model) {
        Date currentTimeStamp = Calendar.getInstance().getTime();
        setCreatedAt(model, currentTimeStamp);
        setModifiedAt(model, currentTimeStamp);
        setCreatedBy(model);
        setModifiedBy(model);
    }

    public void setUpdateModelDefaults(BaseModel model) {
        Date currentTimeStamp = Calendar.getInstance().getTime();
        setModifiedAt(model, currentTimeStamp);
        setCreatedBy(model);
    }
}
