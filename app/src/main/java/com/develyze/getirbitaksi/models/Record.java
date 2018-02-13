package com.develyze.getirbitaksi.models;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("_id")
    @Expose
    private RecordInfo recordInfo;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;

    public RecordInfo getRecordInfo() {
        return recordInfo;
    }

    public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}