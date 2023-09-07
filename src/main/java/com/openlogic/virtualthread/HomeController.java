package com.openlogic.virtualthread;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	private static final int INSERTS = 1;
	
	@Autowired
	InventoryRepository inventoryRepository;

	@GetMapping("/list")
	public List<Inventory> checkThread() throws InterruptedException {
		Thread.sleep(1000);
		return inventoryRepository.findAll();
	}

	@PostMapping("/save")
	@GetMapping("/save")
	@ResponseStatus(HttpStatus.OK)
	public String saveItem() throws InterruptedException {
		try {
			for (int i = 0; i < INSERTS; i++) {
				Inventory item = new Inventory();
				item.setName(RandomStringUtils.randomAlphanumeric(5));
				item.setPrice(RandomUtils.nextLong(10, 1000));
				item.setPrice(1L);
				inventoryRepository.save(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
}
