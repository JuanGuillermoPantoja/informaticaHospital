package informaticaHospital;

import javax.swing.JOptionPane;

public class EmpleadoEventual extends Empleado {
	private double honorarioPorHora;
	private String fechaTerminoContrato;
	
	@Override
	public void registrarDatos() {
		super.registrarDatos();
		
		honorarioPorHora = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario Mensual"));
		fechaTerminoContrato = JOptionPane.showInputDialog("Ingrese fecha de nacimiento (dd/mm/aaaa)");
	}
	@Override
	public void imprimirDatosPersona(String datos) {
		super.imprimirDatosPersona(datos);
		
		datos+= "Honorarios por Hora: "+ honorarioPorHora+"\n";
		datos += "Fecha Termino del Contrato: "+fechaTerminoContrato + "\n";
		
		System.out.println(datos);
	}

	public double getHonorarioPorHora() {
		return honorarioPorHora;
	}

	public void setHonorarioPorHora(double honorarioPorHora) {
		this.honorarioPorHora = honorarioPorHora;
	}

	public String getFechaTerminoContrato() {
		return fechaTerminoContrato;
	}

	public void setFechaTerminoContrato(String fechaTerminoContrato) {
		this.fechaTerminoContrato = fechaTerminoContrato;
	}
	
}
