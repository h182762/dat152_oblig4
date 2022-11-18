package no.hvl.dat152.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import no.hvl.dat152.model.Item;
import no.hvl.dat152.repositories.ItemDAOMemorySingleton;

@RestController
public class ItemController {

	ItemDAOMemorySingleton instance = ItemDAOMemorySingleton.getInstance();

	/**
	 * Returns all items.
	 * 
	 * @return JSON representation of all items
	 */
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String getItems() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<Item> allItems = instance.findAllItems();
		String json = gson.toJson(allItems);
		return json;
	}

	/**
	 * Creates a new item
	 * 
	 * @param id          - the id of the item
	 * @param name        - the name of the item
	 * @param price       - the price of the item
	 * @param description - the description of the item
	 */
	@RequestMapping(value = "/items", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	protected String createItem(@RequestParam String name, @RequestParam Double price,
			@RequestParam String description) {
		try {
			// TODO ADD ERROR WHEN ID IS THE SAME
			Item newItem = new Item(instance.getNextId(), name, price, description);
			instance.createItem(newItem);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(newItem);
			return json;
		} catch (Exception e) {
			return "Failure: " + e;
		}

	}

	/**
	 * Returns a item that is equal to a given id
	 * 
	 * @param id - the id of the item
	 */
	@RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
	public String getItem(@PathVariable String id) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Item item = instance.findItem(id);
		if (item == null) {
			return "NOT FOUND";
		}

		String json = gson.toJson(item);

		return json;
	}

	/**
	 * Delete a item given the id
	 * 
	 * @param id - the id of the item
	 */
	@RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
	protected HttpStatus deleteItem(@PathVariable String id) {
		boolean deleted = instance.deleteItem(id);
		if (deleted == true) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

	/**
	 * Update a item given the id
	 * 
	 * @param id - the id of the item
	 */
	@RequestMapping(value = "/items/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	protected HttpStatus modifyItem(@PathVariable String id, @RequestBody Item body) {
		try {
			instance.updateItem(id, body);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.NOT_FOUND;
		}

	}

}
