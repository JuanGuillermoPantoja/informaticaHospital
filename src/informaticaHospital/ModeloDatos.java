package informaticaHospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JOptionPane;


public class ModeloDatos {
	HashMap<String, Paciente> pacientesMap;
	HashMap<String, EmpleadoPlanilla> empleadosPlanillaMap;
	HashMap<String, EmpleadoEventual> empleadosEventualMap;
	HashMap<String, Medico> medicosMap;	
	ArrayList<CitaMedica> citaList;
	
	public ModeloDatos() {
		pacientesMap=new HashMap<String, Paciente>();
		empleadosPlanillaMap=new HashMap<String, EmpleadoPlanilla>();
		medicosMap=new HashMap<String, Medico>();
		empleadosEventualMap=new HashMap<String, EmpleadoEventual>();
		citaList = new ArrayList<CitaMedica>();
	}
	
	public void registrarPersona(Paciente miPaciente) {
			pacientesMap.put(miPaciente.getNumeroDNI(),miPaciente);
			System.out.println("Se ha registrado el paciente "+miPaciente.getNombre()+" satisfactoriamente");
	}
		
	
	public void registrarPersona(EmpleadoPlanilla miEmpPlanilla) {
		empleadosPlanillaMap.put(miEmpPlanilla.getNumeroDNI(), miEmpPlanilla);
		System.out.println("Se ha registrado el paciente "+miEmpPlanilla.getNombre()+" satisfactoriamente");
	}
	
	public void registrarPersona(EmpleadoEventual miEmpEventual) {
		empleadosEventualMap.put(miEmpEventual.getNumeroDNI(), miEmpEventual);
		System.out.println("Se ha registrado el paciente "+miEmpEventual.getNombre()+" satisfactoriamente");
		
	}
	
	public void registrarPersona(Medico miMedico) {
		medicosMap.put(miMedico.getNumeroDNI(), miMedico);
		System.out.println("Se ha registrado el paciente "+miMedico.getNombre()+" satisfactoriamente");
		
		
	}
	
	public void imprimirPacientes() {
		if (pacientesMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay pacientes registrados");
		}else {
			String msj = "PACIENTES  REGISTRADOS\n";
			Iterator<String> iterador = pacientesMap.keySet().iterator();
			
			while(iterador.hasNext()) {
				
				String clave = iterador.next();
				pacientesMap.get(clave).imprimirDatosPersona(msj);
			}
		}
		
	}
	
	
	public void imprimirEmpleadosEventuales() {
		if (empleadosEventualMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay empleados eventuales registrados");
		}else {
			String msj = "EMPLEADOS EVENTUALES REGISTRADOS\n";
			
			for(String clave : empleadosEventualMap.keySet()) {
				empleadosEventualMap.get(clave).imprimirDatosPersona(msj);
			}
		}
			
 		
	}

	public void imprimirEmpleadosPlanilla() {
		if (empleadosPlanillaMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay empleados por planilla registrados");
		}else {
			String msj = "EMPLEADOS POR PLANILLA REGISTRADOS\n";
			
			for (String clave : empleadosPlanillaMap.keySet()) {
				empleadosPlanillaMap.get(clave).imprimirDatosPersona(msj);
			}
			
		}
		
	}
	
	public void imprimirMedicos() {
		if (medicosMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay medicos registrados");
		}else {
			String msj = "MEDICOS REGISTRADOS\n";
			
			for (Entry<String, Medico> elemento : medicosMap.entrySet()) {
				elemento.getValue().imprimirDatosPersona(msj);			
			}
		}
	}
	
	public boolean validacionNuevoPacienteDocumento(String nuevoPaciente) {
		boolean validacion = false;
		
		if(pacientesMap.containsKey(nuevoPaciente)) {
			validacion = true;
			return validacion;
		}else {
			validacion = false;
			return validacion;
		}
			
	};
	
	public boolean validacionNuevoMedico(String nuevoMedico) {
		boolean validacion = false;
		if(empleadosEventualMap.containsKey(nuevoMedico) || empleadosPlanillaMap.containsKey(nuevoMedico)){
			validacion = true;
			return validacion;
		}else {
			validacion = false;
			return validacion;
		}
	}
	
	public Paciente consultaPorPacientePorDocumento(String documentoPaciente) {
		Paciente miPaciente=null;
		
		if (pacientesMap.containsKey(documentoPaciente)) {
			miPaciente = pacientesMap.get(documentoPaciente);
		}
		return miPaciente;
	}
	
	public Medico consultaMedicoPorNombre (String nombreMedico) {
		for (Medico medico : medicosMap.values()) {
			if (medico.getNombre().equalsIgnoreCase(nombreMedico)) {
				return medico;
			}
		}
		return null;
	}
	
	public void registrarCitaMedica(CitaMedica miCita) {
		citaList.add(miCita);
		System.out.println("Se ha registrado la cita exitosamente\n");
		System.out.println(miCita.informacionCitaMedica());
	}

	public void imprimirCitasMedicasProgramadas() {
		if (citaList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay citas registradas en este momento");
		}else {
			String msj = "CITAS MEDICAS PROGRAMADAS\n";
			CitaMedica miCita = null;
			
			System.out.println(msj + "\n");
			
			if(citaList.size()>0) {
				for (int i = 0; i < citaList.size(); i++) {
					miCita = citaList.get(i);
					System.out.println(miCita.informacionCitaMedica());
				}
			}else {
				System.out.println("No existen citas programadas");
			}
		}
		
		
	}
		
}
