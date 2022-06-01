package com.blood.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bloodloc")
public class Bloodloc {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int center_id;
	private String cityname;
	private String center;
	public Bloodloc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCenter_id() {
		return center_id;
	}
	public void setCenter_id(int center_id) {
		this.center_id = center_id;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public Bloodloc(int center_id, String cityname, String center) {
		super();
		this.center_id = center_id;
		this.cityname = cityname;
		this.center = center;
	}
	@Override
	public String toString() {
		return "Bloodloc [center_id=" + center_id + ", cityname=" + cityname + ", center=" + center + "]";
	}
	

}
