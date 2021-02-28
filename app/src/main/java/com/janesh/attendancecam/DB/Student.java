package com.janesh.attendancecam.DB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by eisaadil on 04/05/18.
 */
@Entity(indices={@Index(value="regNo", unique=true)})
public class Student {
    @PrimaryKey
    @NonNull
    public String studentId; //personID

    public String courseId; //personGroupID

    public String studentName;

    @NonNull
    public String regNo;

    //also includes faces
    public String faceArrayJson;


    public Student(String studentId, String courseId, String studentName, String regNo, String faceArrayJson) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.regNo = regNo;

        this.faceArrayJson = faceArrayJson;
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        return this.studentId.equals(other.studentId);
    }
}
