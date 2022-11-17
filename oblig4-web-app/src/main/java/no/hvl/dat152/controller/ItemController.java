package no.hvl.dat152.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import no.hvl.dat152.model.Item;

@Controller
public class ItemController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewShoppingDefault() {
		return "index";
	}

	@RequestMapping(value = "/viewitems", method = RequestMethod.GET)
	public String viewShoppingList(Model model) {

		String fooResourceUrl = "http://localhost:8299/items/";

		ResponseEntity<Item[]> response = restTemplate.getForEntity(fooResourceUrl, Item[].class);
		Item[] items = response.getBody();

		model.addAttribute("items", items);

		return "shoppinglist";
	}

	@RequestMapping(value = "/viewitem/{id}", method = RequestMethod.GET)
	protected String viewItem(@PathVariable String id, Model model) {
		String fooResourceUrl = "http://localhost:8299/items/";
		ResponseEntity<Item> response = restTemplate.getForEntity(fooResourceUrl + id, Item.class);
		final Item item = response.getBody();
		model.addAttribute("item", item);

		return "viewitem";
	}

	@RequestMapping(value = "/createitem", method = RequestMethod.GET)
	protected String createItem(Model model) {

		// final String id = ItemDAOMemorySingleton.getInstance().getNextId();
		// model.addAttribute("id",id);

		return "createitem";
	}

	@RequestMapping(value = "/createitem", method = RequestMethod.POST)
	protected String createItem(@RequestParam String name, @RequestParam Double price, @RequestParam String description,
			Model model) {
		System.out.println(name + ", " + price + ", " + description);
		if (name != null && price != null && description != null) {
			String fooResourceUrl = "http://localhost:8299/items/";
			Item newItem = new Item();
			ResponseEntity<Item> response = restTemplate.postForEntity(
					"http://localhost:8299/items?name=" + name + "&price=" + price + "&description=" + description,
					newItem, Item.class);

			return "redirect:viewitems";
		} else {
			return "viewitem";
		}

	}

}
