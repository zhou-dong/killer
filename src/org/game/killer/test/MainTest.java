package org.game.killer.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.game.killer.Player;
import org.game.killer.SimpleController;
import org.game.killer.Util;

public class MainTest {

	SimpleController controller = new SimpleController();
	List<String> names = new ArrayList<>();

	public MainTest() {
		addNames();
	}

	public void addNames() {
		names.add("Dong");
		names.add("Kai");
		names.add("Xu");
		names.add("Kun");
		names.add("Snow");
		names.add("Xiong");
		names.add("2");
	}

	public void addUser() {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < names.size(); i++) {
			int index = Util.getRandom(set, names.size());
			Player player = new Player(names.get(index), "10.123.11");
			controller.addPlayer(player);
		}
	}

	public void display() {
		for (Player player : controller.getPlayers()) {
			System.out.println(player.getName() + " " + player.getRole() + " " + player.getStatus() + " "
					+ player.getDeathCount());
		}
	}

	public void testVote(MainTest test, int index) {
		List<Player> players = test.controller.getPlayers();
		for (Player player : players) {
			player.voteToDeath(players.get(index));
		}
		test.controller.killPerson();
	}

	public static void main(String[] args) {
		MainTest test = new MainTest();
		test.addUser();
		test.display();
		System.out.println();
		test.controller.assign();
		test.display();
		System.out.println();
		test.testVote(test, 0);
		test.display();
		System.out.println();
		test.testVote(test, 1);
		test.display();
		System.out.println();
		test.testVote(test, 2);
		test.display();
		System.out.println();
	}

}
