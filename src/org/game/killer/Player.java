package org.game.killer;

public class Player implements Comparable<Player> {

	public enum Role {
		POLICE, KILLER, FOLK, SPEAKER;
	}

	public enum Status {
		ALIVE, DEAD;
	}

	private int deathCount;
	private Status status;
	private Role role;
	private String name;
	private String ip;

	public Player() {
	}

	public Player(String name, String ip) {
		this.name = name;
		this.ip = ip;
		this.status = Status.ALIVE;
	}

	public void voteToDeath(Player player) {
		if (this.status == Status.ALIVE) {
			player.addDeathCount();
		}
	}

	public void clear() {
		this.role = null;
		this.status = Status.ALIVE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}

	public void addDeathCount() {
		this.deathCount++;
	}

	@Override
	public int compareTo(Player other) {
		return this.deathCount - other.deathCount;
	}

}
