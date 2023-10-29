package generated;

import java.io.FileWriter;
import java.io.IOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class JAXBRepaso {

	/*
	 * Crear un método llamado contratarEmpleados que genere un fichero
	 * “empleados.xml”, que se guardará en la carpeta “files_salidas”, con los
	 * siguientes datos: i. Nombre=”Pedro”; Ciudad=”Madrid”; Sueldo=”20.000”;
	 * Empresa=”LW”; Puesto=”Gerente”. ii. Nombre=”Luis”; Ciudad=”Barcelona”;
	 * Sueldo=”19.500”; Empresa=”QH”; Puesto=”Becario”. iii. Nombre=”Isabel”;
	 * Ciudad=”Madrid”; Sueldo=”21.000”; Empresa=”LW”; Puesto=”Manager”.
	 */
	public static void main(String[] args) {
		JAXBRepaso rs = new JAXBRepaso();
		rs.generarJAXB();

	}

	
	public void generarJAXB() {
		
		Empleados empleados = new Empleados();
		
		Empleado empleado1 = new Empleado();
		empleado1.setNombre("Pedro");
		empleado1.setCiudad("Madrid");
		empleado1.setSueldo("20000");
		empleado1.setPuesto("LW");
		empleado1.setEmpresa("Gerente");
		
		empleados.getEmpleado().add(empleado1);
		
		Empleado empleado2 = new Empleado();
		
		empleado2.setNombre("Luis");
		empleado2.setCiudad("Barcelona");
		empleado2.setSueldo("19000");
		empleado2.setPuesto("QH");
		empleado2.setEmpresa("Becario");
		
		empleados.getEmpleado().add(empleado2);
		
		Empleado empleado3 = new Empleado();
		
		empleado3.setNombre("Isabel");
		empleado3.setCiudad("Madrid");
		empleado3.setSueldo("21000");
		empleado3.setPuesto("LW");
		empleado3.setEmpresa("Manager");
		
		empleados.getEmpleado().add(empleado3);
		
		JAXBContext context;
	
		try {
			
			
			context = JAXBContext.newInstance(Empleados.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(empleados, new FileWriter("files_salidas/empleados.xml"));
		
		
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

}
