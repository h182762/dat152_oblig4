package no.hvl.dat152.repositories;

import java.util.List;

import no.hvl.dat152.model.Item;

/**
 * Interface for the data access object for Item.
 *
 * @author Lars-Petter Helland
 * @author Atle Geitung
 * @author Lasse Jenssen
 */
public interface ItemDAO {

	/**
	 * Load some test data
	 */
	default void init() {
		createItem(new Item("9991", "Item01", 1D, "Item01 Description"));
		createItem(new Item("9992", "Item02", 2D, "Item02 Description"));
		createItem(new Item("9993", "Item03", 3D, "Item03 Description"));
	}

	/**
	 * Find all Items.
	 *
	 * @return all items
	 */
	List<Item> findAllItems();

	/**
	 * Find one Item.
	 *
	 * @param id the id
	 * @return the Item
	 */
	Item findItem(String id);

	/**
	 * Insert a new Item.
	 *
	 * @param item the new Item
	 */
	void createItem(Item item);

	/**
	 * Update an existing Item.
	 *
	 * @param id       the id
	 * @param itemdata The Item
	 */
	void updateItem(String id, Item itemdata);

	/**
	 * Delete an existing Item.
	 *
	 * @param id the id
	 */
	boolean deleteItem(String id);

	/**
	 * Return the next available id.
	 *
	 * @return id
	 */
	String getNextId();
}
