package com.palprotech.eduappparentsstudents.bean.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 17-05-2017.
 */

public class Exams implements Serializable{

    @SerializedName("exam_id")
    @Expose
    private String exam_id;

    @SerializedName("exam_name")
    @Expose
    private String exam_name;

    /**
     * @return The hw_id
     */
    public String getExamId() {
        return exam_id;
    }

    /**
     * @param exam_id The exam_id
     */
    public void setExamId(String exam_id) {
        this.exam_id = exam_id;
    }

    /**
     * @return The exam_name
     */
    public String getExamName() {
        return exam_name;
    }

    /**
     * @param exam_name The exam_name
     */
    public void setExamName(String exam_name) {
        this.exam_name = exam_name;
    }


}
