
import java.sql.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public abstract class PruebaGestion extends GestionFacultad {

    public static void main(String[] args) throws IOException {

        //Declaracion de Variables
        String pedir, nombre = "", apellido = "", id = "", anio = "", numDes = "", opcion = "", tipo = "", estadobeca = "";
        float nuevoSueldo = 0;

        try {
            //Conexion a Base de Datos
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost/universidad", "root", "")) {
                Statement stm = co.createStatement();
                ResultSet rs = stm.executeQuery("select * from empleado");

                //Crear reporte
                String path0 = "C:\\Users\\luiyi\\Documents\\NetBeansProjects\\Bimestre23CProgra\\src\\Reporte\\Docente.jrxml";
                String path1 = "C:\\Users\\luiyi\\Documents\\NetBeansProjects\\Bimestre23CProgra\\src\\Reporte\\Estudiante.jrxml";
                String path2 = "C:\\Users\\luiyi\\Documents\\NetBeansProjects\\Bimestre23CProgra\\src\\Reporte\\Empleado.jrxml";
                JasperPrint reporte = null;
                JasperPrint reporte1 = null;
                JasperPrint reporte2 = null;

                Empleado emp = new Empleado() {
                    @Override
                    public void generaSueldo() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                Estudiante est = new Estudiante() {
                    @Override
                    public void generaSueldo() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                Profesor prf = new Profesor() {
                    @Override
                    public void generaSueldo() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                JOptionPane.showMessageDialog(null, "Bienvenido al programa");
                do {
                    pedir = JOptionPane.showInputDialog(null, "Seleccione su Usuario\n1. Empleado\n2.Estudiante\n3.Profesor\n4.Obtener Listas Actuales\n5.Salir");
                    switch (Integer.parseInt(pedir)) {
                        case 1: {
                            do {
                                opcion = JOptionPane.showInputDialog(null, "Empleado\n1. Registro Empleado\n2. Cambio Despacho\n3. Calculo Salario \n4. Guardar Registro\n5. Salir");
                                switch (Integer.parseInt(opcion)) {
                                    case 1: {
                                        id = JOptionPane.showInputDialog(null, "Su Cédula: ");
                                        emp.setId(id);
                                        nombre = JOptionPane.showInputDialog(null, "Su Nombre: ");
                                        emp.setNombre(nombre);
                                        apellido = JOptionPane.showInputDialog(null, "Su Apellido: ");
                                        emp.setApellido(apellido);
                                        anio = JOptionPane.showInputDialog(null, "Año en el que empezó a laborar en la empresa: ");
                                        emp.setAnioincorporacion(Integer.parseInt(anio));
                                        numDes = JOptionPane.showInputDialog(null, "Ingrese el número de su cubículo: ");
                                        emp.setAnioincorporacion(Integer.parseInt(numDes));
                                        break;
                                    }
                                    case 2: {
                                        numDes = JOptionPane.showInputDialog(null, "Ingrese su nuevo número de Despacho:");
                                        emp.cambioDespacho(Integer.parseInt(numDes));
                                        JOptionPane.showMessageDialog(null, "Despacho cambiado con exito");
                                        break;
                                    }
                                    case 3: {
                                        JOptionPane.showMessageDialog(null, "Sueldo generado con éxito");
                                        emp.generaSueldo(Integer.parseInt(anio));
                                        break;
                                    }
                                    case 4: {
                                        stm.executeUpdate("INSERT INTO empleado (CEDULA,NOMBRES,APELLIDOS,DESPACHO,EXPERIENCIA,SALARIO) "
                                                + "VALUES ('" + emp.getId() + "','" + emp.getNombre() + "','" + emp.getApellido() + "','" + numDes
                                                + "','" + anio + "','" + emp.getSueldo() + "' )");
                                        JOptionPane.showMessageDialog(null, "Datos Almacenados con éxito");
                                        break;
                                    }
                                }
                            } while (Integer.parseInt(opcion) != 5);
                            break;
                        }
                        case 2: {
                            do {
                                opcion = JOptionPane.showInputDialog(null, "Estudiante\n1. Registro Estudiante\n2. Matrícula nuevo ciclo\n3. Salario por Beca \n4. Guardar Registro\n5. Salir");
                                switch (Integer.parseInt(opcion)) {
                                    case 1: {
                                        id = JOptionPane.showInputDialog(null, "Su Cédula: ");
                                        est.setId(id);
                                        nombre = JOptionPane.showInputDialog(null, "Su Nombre: ");
                                        est.setNombre(nombre);
                                        apellido = JOptionPane.showInputDialog(null, "Su Apellido: ");
                                        est.setApellido(apellido);
                                        anio = JOptionPane.showInputDialog(null, "Ciclo académico: ");
                                        est.setCiclo(Integer.parseInt(anio));
                                        break;
                                    }
                                    case 2: {
                                        numDes = JOptionPane.showInputDialog(null, "Ingrese el ciclo a matricularse:");
                                        est.matricular(Integer.parseInt(numDes));
                                        JOptionPane.showMessageDialog(null, "Matriculado con exito");
                                        break;
                                    }
                                    case 3: {
                                        numDes = JOptionPane.showInputDialog(null, "¿Usted posee Beca\n1. Si\n2. No?");
                                        if (Integer.parseInt(numDes) == 1) {
                                            estadobeca = "SI";
                                            nuevoSueldo = est.generaSueldo(1);
                                            JOptionPane.showMessageDialog(null, "Proceso Generado con éxito");
                                        } else {
                                            estadobeca = "NO";
                                            nuevoSueldo = est.generaSueldo(2);
                                            JOptionPane.showMessageDialog(null, "Proceso Generado con éxito");
                                        }
                                        break;
                                    }
                                    case 4: {
                                        stm.executeUpdate("INSERT INTO estudiante (CEDULA,NOMBRES,APELLIDOS,CICLOS_TOTALES,CICLO_aCTUAL,BECADO,BENEFICIO) "
                                                + "VALUES ('" + est.getId() + "','" + est.getNombre() + "','" + est.getApellido() + "','" + 10
                                                + "','" + est.getCiclo() + "','" + estadobeca + "','" + nuevoSueldo + "' )");
                                        JOptionPane.showMessageDialog(null, "Datos Almacenados con éxito");
                                        break;
                                    }
                                }
                            } while (Integer.parseInt(opcion) != 5);
                            break;
                        }
                        case 3: {
                            do {
                                opcion = JOptionPane.showInputDialog(null, "Profesor\n1. Registro Profesor\n2. Registro Area Trabajo\n3. Cálculo Salario \n4. Guardar \n5. Salir");
                                switch (Integer.parseInt(opcion)) {
                                    case 1: {
                                        id = JOptionPane.showInputDialog(null, "Su Cédula: ");
                                        prf.setId(id);
                                        nombre = JOptionPane.showInputDialog(null, "Su Nombre: ");
                                        prf.setNombre(nombre);
                                        apellido = JOptionPane.showInputDialog(null, "Su Apellido: ");
                                        prf.setApellido(apellido);
                                        break;
                                    }
                                    case 2: {
                                        numDes = JOptionPane.showInputDialog(null, "Seleccione Area\n1.Area Técnica\n2.Area SocioHumanística\n3. Area Biológica\n4. Area Idiomas");
                                        prf.asignArea(Integer.parseInt(numDes));
                                        opcion = JOptionPane.showInputDialog(null, "Seleccione Tipo Trabajador\n1.Tiempo Completo\n2.Tiempo Parcial");
                                        if (Integer.parseInt(opcion) == 1) {
                                            estadobeca = "Tiempo Parcial";
                                            nuevoSueldo = prf.generaSueldo(1);
                                        } else {
                                            estadobeca = "Tiempo Completo";
                                            nuevoSueldo = prf.generaSueldo(2);
                                        }
                                        JOptionPane.showMessageDialog(null, "Registrado con exito");
                                        break;
                                    }
                                    case 3: {
                                        JOptionPane.showMessageDialog(null, "Salario Generado con éxito");
                                        break;
                                    }
                                    case 4: {
                                        stm.executeUpdate("INSERT INTO docente (CEDULA,NOMBRES,APELLIDOS,AREA,TIPO_DOC,SALARIO) "
                                                + "VALUES ('" + prf.getId() + "','" + prf.getNombre() + "','" + prf.getApellido() + "','" + prf.getDepartamento()
                                                + "','" + estadobeca + "','" + nuevoSueldo + "' )");
                                        JOptionPane.showMessageDialog(null, "Datos Almacenados con éxito");
                                        break;
                                    }
                                }
                            } while (Integer.parseInt(opcion) != 5);
                            break;
                        }
                        case 4: {
                            try {
                                JOptionPane.showMessageDialog(null, "Generando Reportes");
                                JasperReport jasperReport;
                                jasperReport = JasperCompileManager.compileReport(path0);
                                JasperReport jasperReport1;
                                jasperReport1 = JasperCompileManager.compileReport(path1);
                                JasperReport jasperReport2;
                                jasperReport2 = JasperCompileManager.compileReport(path2);
                                reporte = JasperFillManager.fillReport(jasperReport, null, co);
                                reporte1 = JasperFillManager.fillReport(jasperReport1, null, co);
                                reporte2 = JasperFillManager.fillReport(jasperReport2, null, co);
                                JasperViewer jv = new JasperViewer(reporte);
                                JasperViewer jv1 = new JasperViewer(reporte1);
                                JasperViewer jv2 = new JasperViewer(reporte2);
                                jv.setVisible(true);
                                jv1.setVisible(true);
                                jv2.setVisible(true);
                                break;
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        case 5: {
                            JOptionPane.showMessageDialog(null, "Gracias por usar el sistema...");
                            System.exit(0);
                            break;
                        }
                    }
                } while (Integer.parseInt(pedir) != 5);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha detectado un error durante la ejecucion del programma");
        }
    }
}
