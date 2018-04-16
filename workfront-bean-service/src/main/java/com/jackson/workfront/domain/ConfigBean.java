package com.jackson.workfront.domain;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by sarwar on 4/9/18.
 */
@Entity
public class ConfigBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "BEAN_NAME")
    private String beanName;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "REFRESH_RATE")
    private Double refreshRate;
    @Column(name = "QUERY")
    private String query;

    protected  ConfigBean() {
    }

    public ConfigBean(String beanName, LocalDate startDate, Double refreshRate, String query) {
        this.beanName = beanName;
        this.startDate = startDate;
        this.refreshRate = refreshRate;
        this.query = query;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Double getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigBean that = (ConfigBean) o;

        if (!id.equals(that.id)) return false;
        if (!beanName.equals(that.beanName)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!refreshRate.equals(that.refreshRate)) return false;
        return query.equals(that.query);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + beanName.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + refreshRate.hashCode();
        result = 31 * result + query.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "id=" + id +
                ", beanName='" + beanName + '\'' +
                ", startDate=" + startDate +
                ", refreshRate=" + refreshRate +
                ", query='" + query + '\'' +
                '}';
    }
}


