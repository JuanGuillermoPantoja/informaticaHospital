package informaticaHospital;

import javax.swing.JOptionPane;

public class Procesos {
	ModeloDatos miModeloDatos;
	public Procesos(){
		miModeloDatos= new ModeloDatos();
		presentarMenuOpciones();
	}

	private void presentarMenuOpciones() {
		String menu = "MENU HOSPITAL \n\n";
		menu+="1. Registrar paciente\n";
		menu+="2. Registrar empleado\n";
		menu+="3. Registrar cita medica\n";
		menu+="4. Imprimir informacion\n";
		menu+="5. Salir\n\n";
		menu+="Ingrese una Opcion\n";
		
		int opcion = 0;
		
		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch(opcion) {
			case 1:registrarPaciente(); break;
			case 2:registrarEmpleado(); break;
			case 3:registrarCitaMedica(); break;
			case 4:imprimirInformacion();	break;			
			case 5:System.out.println("El sistema ha terminado!"); break;
			default: System.out.println("No existe el codigo, verifique nuevamente"); break;
			}
		}while(opcion != 5);
		
		
	}

	private void imprimirInformacion() {
		
		String menuImprimir = "MENU IMPRESIONES\n";
		menuImprimir += "1. Listar Pacientes\n";
		menuImprimir += "2. Listar Empleados Eventuales\n";
		menuImprimir += "3. Listar Empleados por Planilla\n";
		menuImprimir += "4. Listar Medicos\n";
		menuImprimir += "5. Listar Citas Programadas\n";
		menuImprimir += "Ingrese una opcion\n";
		
		System.out.println("******************************************************************************************************");
		
		int opcion=Integer.parseInt(JOptionPane.showInputDialog(menuImprimir));
		
		switch (opcion) {
		case 1: miModeloDatos.imprimirPacientes();	break;
		case 2: miModeloDatos.imprimirEmpleadosEventuales();	break;
		case 3: miModeloDatos.imprimirEmpleadosPlanilla();	break;
		case 4: miModeloDatos.imprimirMedicos();	break;
		case 5: miModeloDatos.imprimirCitasMedicasProgramadas(); break;
		default: System.out.println("No existe la opcion");
			break;
		}
	}

	private void registrarEmpleado() {
		
		String nuevoEmpleado = JOptionPane.showInputDialog("Ingrese documento del empleado para validarlo");
		boolean empleadoValidacion  = miModeloDatos.validacionNuevoMedico(nuevoEmpleado);
		
		if (empleadoValidacion == true) {
			JOptionPane.showMessageDialog(null, "El empleado ya esta registrado");
		}else {		
		String menuTipoEmpleado="Registro de Empleado\n";
		menuTipoEmpleado+="1. Empleado Eventual\n";
		menuTipoEmpleado+="2. Empleado con Planilla\n";
		menuTipoEmpleado+="Seleccione el tipo de empleado a registrar";
		
		int tipoEmpleado=Integer.parseInt(JOptionPane.showInputDialog(menuTipoEmpleado));
		
		switch (tipoEmpleado) {
		case 1:
				EmpleadoEventual miEmpleadoEventual=new EmpleadoEventual();
				miEmpleadoEventual.registrarDatos();
				miModeloDatos.registrarPersona(miEmpleadoEventual);
			break;
		case 2:
			String resp=JOptionPane.showInputDialog("Ingrese si, si es un medico, de lo contrario es otro tipo de empleado");
			if (resp.equalsIgnoreCase("si")) {
				Medico miMedico = new Medico();
				miMedico.registrarDatos();
				miModeloDatos.registrarPersona(miMedico);
			}else {
				EmpleadoPlanilla miEmpleadoPlanilla = new EmpleadoPlanilla();
				miEmpleadoPlanilla.registrarDatos();
				miModeloDatos.registrarPersona(miEmpleadoPlanilla);
			}
			
			break;
			
			default:System.out.println("El tipo de empleado no es valido, intentalo nuevamente"); break;
		}
	  }
	}
	

	private void registrarPaciente() {
		String nuevoPaciente = JOptionPane.showInputDialog("Ingrese el documento del paciente para validarlo");
		
		boolean pacienteValidacion  = miModeloDatos.validacionNuevoPacienteDocumento(nuevoPaciente);
		if (pacienteValidacion == true) {
			JOptionPane.showMessageDialog(null, "Ya existe");
		}else {
			Paciente miPaciente = new Paciente();
			miPaciente.registrarDatos();
			miModeloDatos.registrarPersona(miPaciente);
		}
		
	}
	
	private void registrarCitaMedica() {
		String documentoPaciente = JOptionPane.showInputDialog("Ingrese el documento del paciente");
		
		Paciente pacienteEncontrado = miModeloDatos.consultaPorPacientePorDocumento(documentoPaciente);
		
		if (pacienteEncontrado != null) {
			String nombreMedico = JOptionPane.showInputDialog("Ingrese el nombre del Medico");
			Medico medicoEncontrado = miModeloDatos.consultaMedicoPorNombre(nombreMedico);
			
			if (medicoEncontrado != null) {
				String servicio = JOptionPane.showInputDialog("Ingrese servicio o motivo de la consulta");
				String fechaConsulta =  JOptionPane.showInputDialog("Ingrese la fecha de la consulta");
				String horaConsulta = JOptionPane.showInputDialog("Ingrese la hora de la consulta");
				
				String lugar = "La cita sera en el consultorio"+ medicoEncontrado.getNumeroDeConsultorio();
				CitaMedica miCita = new CitaMedica(pacienteEncontrado, medicoEncontrado, servicio, fechaConsulta, horaConsulta, lugar);
				miModeloDatos.registrarCitaMedica(miCita);
			}else {
				System.out.println("El medico no se encuentra registrado en el sistema");
			}
		}else {
			System.out.println("El paciente no se encuentra registrado en el sistema");
		}
	}
	
}
