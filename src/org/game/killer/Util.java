package org.game.killer;

import java.util.Random;
import java.util.Set;

public class Util {

	public static int getRandom(Set<Integer> set, int bound) {
		while (true) {
			int result = new Random().nextInt(bound);
			if (!set.contains(result)) {
				set.add(result);
				return result;
			}
		}
	}

}
