package com.xiaolan.common.bean;

import com.xiaolan.common.dao.query.BaseQuery;

/**
 * Beacon对象
 *
 * @author Administrator
 */
public class BeaconORM extends BaseORM {
    /**
     *
     */
    private static final long serialVersionUID = 6124242540205616091L;
    private String addr;                                        // 地址
    private Double major;                                        // 设备信息，包括UUID、major、minor，以及距离
    private Double measure_power;
    private Double minor;                                        //
    private Long uuid;                                        // 设备信息，包括UUID、major、minor，以及距离
    private Double latitude;                                    // 纬度
    private Double longitude;                                    // 经度

    public class BeaconQuery extends BaseQuery {
        public void defineQuery() {
            addQuery("addr like '%" + addr + "%'");
        }
    }

    public String getAddr() {
        return addr;
    }

    public Double getMajor() {
        return major;
    }

    public Double getMeasure_power() {
        return measure_power;
    }

    public Double getMinor() {
        return minor;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setMajor(Double major) {
        this.major = major;
    }

    public void setMeasure_power(Double measure_power) {
        this.measure_power = measure_power;
    }

    public void setMinor(Double minor) {
        this.minor = minor;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


}
