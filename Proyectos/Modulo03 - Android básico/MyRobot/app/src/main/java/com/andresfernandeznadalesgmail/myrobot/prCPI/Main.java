package prCPI;

public class Main {
	public static void main(String [] args) {
		// 5 + (6 - 2) * 3;
		// NPI 5 6 2 - 3 * +
		CPI cpi = new CPI();
		cpi.entra(5);
		cpi.entra(6);
		cpi.entra(2);
		cpi.resta();
		cpi.entra(3);
		cpi.multiplica();
		cpi.suma();
		System.out.println("Resultado " + cpi.getResultado());
 	}
}
