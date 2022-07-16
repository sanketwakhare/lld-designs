package com.sanket.netflixschema.models;

import com.sanket.netflixschema.enums.VideoStatusType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "video_profile")
public class VideoProfile extends BaseModel {

    private VideoStatusType videoStatus;
    private Date lastWatchTimeStamp;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private Video video;

    public VideoStatusType getVideoStatus() {
        return videoStatus;
    }

    public Date getLastWatchTimeStamp() {
        return lastWatchTimeStamp;
    }
}
