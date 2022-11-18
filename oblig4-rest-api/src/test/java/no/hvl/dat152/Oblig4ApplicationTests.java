package no.hvl.dat152;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;

import no.hvl.dat152.controller.ItemController;
import no.hvl.dat152.model.Item;

@SpringBootTest
class Oblig4ApplicationTests {

	@Autowired
	private ItemController controller;

	@Test
	void get3items() {
		String item = controller.getItems();
		Gson g = new Gson();
		Item[] s = g.fromJson(item, Item[].class);
		assertTrue(s.length == 3);
	}

	@Test
	void itemNotFound() {
		String status = controller.getItem("10100");
		System.out.println(status);
		assertTrue(status.toString() == "NOT FOUND");
	}

}
