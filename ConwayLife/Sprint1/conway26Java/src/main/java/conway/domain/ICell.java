package main.java.conway.domain;

public interface ICell {
	public boolean isAlive();
	public void setStatus(boolean status);
	public void switchStatus();
}
