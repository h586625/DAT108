package no.hvl.dat108.task1;

public class Message {
	private String content;

	public Message(String content) {
		this.content = content;
	}

	public void print() {
		System.out.println(content);
	}

	public String getContent() {
		return content;
	}

	public synchronized void setContent(String content) {
		this.content = content;
	}
}
