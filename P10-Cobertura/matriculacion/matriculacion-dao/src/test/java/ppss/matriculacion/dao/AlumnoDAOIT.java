package ppss.matriculacion.dao;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.time.LocalDate;

import ppss.matriculacion.to.*;

public class AlumnoDAOIT {
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;

    @BeforeEach
    public void setUp() throws Exception {

        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/matriculacion?useSSL=false", "root", "ppss");
        connection = databaseTester.getConnection();

    }

    @Test
    public void testA1() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        alumno.setFechaNacimiento(LocalDate.of(1985, 02, 22));

        //Inicializar la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Invocar al SUT
        Assertions.assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno));

        //Recuperar BD
        IDataSet actualDataSet = connection.createDataSet();
        ITable actualTable = actualDataSet.getTable("alumnos");

        //Cargar resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testA2() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        alumno.setFechaNacimiento(LocalDate.of(1982, 02, 22));

        //Inicializar la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Invocar al SUT
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );

        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    public void testA3() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setFechaNacimiento(LocalDate.of(1982, 02, 22));

        //Inicializar la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Invocar al SUT
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );

        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    public void testA4() throws Exception {
        AlumnoTO alumno = new AlumnoTO();

        //Inicializar la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Invocar al SUT
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );

        Assertions.assertEquals("Alumno nulo", exception.getMessage());
    }

    @Test
    public void testA5() throws Exception {
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNombre("Pedro Garcia Lopez");
        alumno.setFechaNacimiento(LocalDate.of(1982, 02, 22));

        //Inicializar la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Invocar al SUT
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().addAlumno(alumno)
        );

        Assertions.assertEquals("Error al conectar con BD", exception.getMessage());
    }

    @Test
    public void testB1() throws Exception {
        String nif = "11111111A";

        //Inicializar la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Invocar al SUT
        Assertions.assertDoesNotThrow(() -> new FactoriaDAO().getAlumnoDAO().delAlumno(nif));

        //Recuperar BD
        IDataSet actualDataSet = connection.createDataSet();
        ITable actualTable = actualDataSet.getTable("alumnos");

        //Cargar resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testB2() throws Exception {
        String nif = "33333333C";

        //Inicializar la BD
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        //Invocar al SUT
        DAOException exception = Assertions.assertThrows(DAOException.class,
                () -> new FactoriaDAO().getAlumnoDAO().delAlumno(nif)
        );

        Assertions.assertEquals("No se ha borrado ningun alumno", exception.getMessage());
    }
}