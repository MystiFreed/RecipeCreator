package model;
// Mysti Freed, mrfreed@dmacc.edu
// 9/19/2018

// This program is the annotated entity for the RecipeCreator program. 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="create_recipe")
public class RecipeImport {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id; //auto-incremented identifier for the line item
	@Column(name="QUANTITY")
	private double quantity; // quantity of the component to be added
	@Column(name="MEASUREMENT")
	private String measurement; // volume measure (T, tsp, cups, etc)
	@Column(name="COMPONENT")
	private String component; // recipe item
	
	public RecipeImport() { // default constructor
	}
	
	public RecipeImport(String c, String m, double q) { // non-default constructor
		this.component = c;
		this.measurement = m;
		this.quantity = q;
	}
	
	public RecipeImport(String c) { // constructor for remove method
		this.component = c;
	}

	public int getId() { // get id, auto-increments
		return id;
	}

	public void setId(int id) { //set id
		this.id = id;
	}

	public double getQuantity() { // get quantity, double
		return quantity;
	}

	public void setQuantity(double quantity) { // set quantity
		this.quantity = quantity;
	}

	public String getMeasurement() { // get measurement type
		return measurement;
	}

	public void setMeasurement(String measurement) { // set measurement
		this.measurement = measurement;
	}

	public String getComponent() { // get recipe component via String
		return component;
	}

	public void setComponent(String component) { // set component
		this.component = component;
	}

	// this method prints each recipe quantity, measurement and component line by line until no other
	// components remain
	public void printRecipe() { 
		System.out.println(quantity + " " + measurement + " " + component);
		}
	
	// to string for all variables
	@Override
	public String toString() {
		return "RecipeImport [id=" + id + ", quantity=" + quantity + ", measurement=" + measurement + ", component="
				+ component + "]";
	}
	
	

}
