package no.hvl.dat152.repositories;

import java.util.ArrayList;
import java.util.List;

import no.hvl.dat152.model.Item;

/**
 * Implementation of a Item DAO.
 *
 * @author Lars-Petter Helland
 * @author Atle Geitung
 */
public final class ItemDAOMemorySingleton implements ItemDAO {

    private final List<Item> items = new ArrayList<>();
    private static final Integer FIRST_INDEX = 10000;
    private Integer nextId = FIRST_INDEX;

    // Singleton-things
    private static ItemDAOMemorySingleton instance;

    /**
     * Constructs a new ItemDAOMemorySingleton.
     */
    private ItemDAOMemorySingleton() {
    }

    /**
     * Gets the only instance of this class.
     *
     * @return instance
     */
    public static synchronized ItemDAOMemorySingleton getInstance() {
        if (instance == null) {
            instance = new ItemDAOMemorySingleton();
            instance.init();
        }
        return instance;
    }

    @Override
    public List<Item> findAllItems() {
        return items;
    }

    @Override
    public Item findItem(final String id) {
        final int index = items.indexOf(new Item(id));
        return index >= 0 ? items.get(index) : null;
    }

    @Override
    public synchronized void createItem(final Item item) {
        final int index = items.indexOf(item);
        if (index == -1) {
            items.add(item);
        }
    }

    @Override
    public synchronized void updateItem(final String id, final Item itemdata) {
        final int index = items.indexOf(new Item(id));
        if (index >= 0) {
            items.get(index).setName(itemdata.getName());
            items.get(index).setPrice(itemdata.getPrice());
            items.get(index).setDescription(itemdata.getDescription());
        }
    }

    @Override
    public synchronized String getNextId() {
        nextId++;
        return nextId.toString();
    }
}
