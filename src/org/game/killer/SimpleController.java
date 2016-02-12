package org.game.killer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.game.killer.Player.Role;
import org.game.killer.Player.Status;

public class SimpleController implements Controller {

	private List<Player> players;

	private Role winner;

	public SimpleController() {
		this.players = new ArrayList<>();
	}

	public void initRound() {
		clearDeathCount();
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	public void removePlayer(int index) {
		this.players.remove(index);
	}

	public List<Player> getPlayers() {
		return new ArrayList<>(players);
	}

	public Role getWinner() {
		return winner;
	}

	public void setWinner(Role winner) {
		this.winner = winner;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public void assign() {
		clear();
		Set<Integer> set = new HashSet<>();
		assignKiller(set);
		assignPolice(set);
		assignSpeak(set);
		assignFolks();
	}

	private void clear() {
		for (Player player : players) {
			player.clear();
		}
	}

	private void assignPolice(Set<Integer> set) {
		assign(set, Role.POLICE);
	}

	private void assignKiller(Set<Integer> set) {
		assign(set, Role.KILLER);
	}

	private void assignSpeak(Set<Integer> set) {
		assign(set, Role.SPEAKER);
	}

	private void assignFolks() {
		for (Player player : players) {
			if (player.getRole() == null) {
				player.setRole(Role.FOLK);
			}
		}
	}

	private void assign(Set<Integer> set, Role role) {
		players.get(Util.getRandom(set, players.size())).setRole(role);
	}

	@Override
	public Player killPerson() {
		Collections.sort(players);
		for (int i = players.size() - 1; i > 0; i--) {
			Player current = players.get(i);
			if (current.getStatus() == null || current.getStatus() == Status.ALIVE) {
				current.setStatus(Status.DEAD);
				return current;
			}
		}
		return null;
	}

	private void clearDeathCount() {
		for (Player player : players) {
			if (player.getStatus() != Status.DEAD) {
				player.setDeathCount(0);
			}
		}
	}

	private int countAlive() {
		int result = 0;
		for (Player player : players) {
			if (player.getStatus() == Status.ALIVE) {
				result++;
			}
		}
		return result;
	}

	@Override
	public boolean isGameOver() {
		switch (killPerson().getRole()) {
		case POLICE:
			winner = Role.POLICE;
			return true;
		case KILLER:
			winner = Role.KILLER;
			return true;
		default:
			break;
		}
		if (countAlive() < 3) {
			return true;
		}
		return false;
	}

}
