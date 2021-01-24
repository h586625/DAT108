package no.hvl.dat108;

public class Bean
{
	//@EJB
	//private BeanDAO beanDAO;

	// Fields
	private String something;

	public Bean(String something) {
		this.something = something;
	}

	// =====GETTERS=====
	public String getSomething() {
		return something;
	}

	@Override
	public String toString() {
		return "Bean [something=" + something + "]";
	}
}
