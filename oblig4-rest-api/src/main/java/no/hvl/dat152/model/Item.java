package no.hvl.dat152.model;

import java.io.Serializable;

/**
 * Implements a Item. This is the domain model.
 *
 * @author Lars-Petter Helland
 * @author Atle Geitung
 *
 */
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private Double price;
	private String description;

	/**
	 * Constructs a new Item.
	 */
	public Item() {
	}

	/**
	 * Constructs a new Item.
	 *
	 * @param id the id
	 */
	public Item(final String id) {
		this.id = id;
	}

	/**
	 * Constructs a new Item.
	 *
	 * @param id          the id
	 * @param name        the name
	 * @param price       the price
	 * @param description the description
	 */
	public Item(final String id, final String name, final Double price, final String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public final void setId(final String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public final Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the price to set
	 */
	public final void setPrice(final Double price) {
		this.price = price;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public final void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}

	@Override
	public final int hashCode() {
		return getId().hashCode();
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Item other = (Item) obj;
		return this.id.equals(other.id);
	}

}
