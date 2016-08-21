
package com.koton.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by MacbookPro on 20/08/16.
 */
@Document
public class ProductEntity extends BaseEntity{

	private String name;

	private String size;

	private String color;

	private Double price;

    private CategoryEntity category;

    public ProductEntity() {
        super();
    }

    public ProductEntity(Long id, String name, String size, String color, Double price, CategoryEntity category) {
        super(id);
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.category = category;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
