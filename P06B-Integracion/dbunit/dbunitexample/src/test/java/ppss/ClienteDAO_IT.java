 package ppss;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

 /* IMPORTANTE:
     Dado que prácticamente todos los métodos de dBUnit lanzan una excepción,
     vamos a usar "throws Esception" en los métodos, para que el código quede más
     legible sin necesidad de usar un try..catch o envolver cada sentencia dbUnit
     con un assertDoesNotThrow()
     Es decir, que vamos a primar la legibilidad de los tests.
     Si la SUT puede lanza una excepción, SIEMPRE usaremos assertDoesNotThrow para
     invocar a la sut cuando no esperemos que se lance dicha excepción (independientemente de que hayamos propagado las excepciones provocadas por dbunit).
 */
public class ClienteDAO_IT {
  
  private ClienteDAO clienteDAO; //SUT
  private IDatabaseTester databaseTester;
  private IDatabaseConnection connection;

  @BeforeEach
  public void setUp() throws Exception {

    String cadena_conexionDB = "jdbc:mysql://localhost:3306/DBUNIT?useSSL=false";
    databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
            "jdbc:mysql://localhost:3306/DBUNIT?useSSL=false", "root", "ppss");
    connection = databaseTester.getConnection();

    clienteDAO = new ClienteDAO();
  }

  @Test
  public void D1_insert_should_add_John_to_cliente_when_John_does_not_exist() throws Exception {
    Cliente cliente = new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
    
     //invocamos a la sut
    Assertions.assertDoesNotThrow(()->clienteDAO.insert(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente"); 

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);

   }

  @Test
  public void D2_delete_should_remove_John_from_cliente_when_John_is_in_table() throws Exception {
    Cliente cliente =  new Cliente(1,"John", "Smith");
    cliente.setDireccion("1 Main Street");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a la SUT
    Assertions.assertDoesNotThrow(()->clienteDAO.delete(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");
    
    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

  @Test
  public void D3_insert_should_throw_exception_when_insert_cliente_that_already_exists() throws Exception {
    Cliente cliente = new Cliente(2, "Pepe", "Garcia");
    cliente.setDireccion("1 Develop Street");
    cliente.setCiudad("Anycity");

    //Inicializar BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init-D3-D4.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //Invocar la SUT
    SQLException exception = Assertions.assertThrows(SQLException.class,
            () -> clienteDAO.insert(cliente)
    );

    //Comprobar mensaje de error
    Assertions.assertTrue(exception.getMessage().contains("Duplicate entry"));


    //Recuperar BD
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    //Dataset con datos esperados
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-expected-D3-D4.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(actualTable, expectedTable);

  }

  @Test
  public void D4_delete_should_throw_exception_when_delete_cliente_that_do_not_exists() throws Exception {
    Cliente cliente = new Cliente(3, "Alex", "Lopez");
    cliente.setDireccion("5 Java Street");
    cliente.setCiudad("TechCity");

    //Inicializar BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init-D3-D4.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //Invocar la SUT
    SQLException exception = Assertions.assertThrows(SQLException.class,
            () -> clienteDAO.delete(cliente)
    );

    //Comprobar mensaje de error
    Assertions.assertTrue(exception.getMessage().contains("Delete failed"));

    //Recuperar BD
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");

    //Dataset con datos esperados
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-expected-D3-D4.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(actualTable, expectedTable);
  }

  @Test
  public void D5_update_should_change_Johns_city_to_NewCity_when_exists_in_table() throws Exception {
    Cliente cliente = new Cliente(1, "John", "Smith");
    cliente.setDireccion("Other Street");
    cliente.setCiudad("NewCity");


    //Inicializar BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init-D5-D6.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //Llamar a la SUT
    Assertions.assertDoesNotThrow(() -> clienteDAO.update(cliente));


    //Recuperar la BD
    IDataSet databaseDataSet = connection.createDataSet();
    ITable actualTable = databaseDataSet.getTable("cliente");


    //Cargar datos esperados
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-expected-D5-D6.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);
  }

  @Test
  public void D6_retrieve_should_retrn_all_data_from_clientes() throws Exception {
    Cliente clienteEsperado = new Cliente(1, "John", "Smith");
    clienteEsperado.setDireccion("1 Main Street");
    clienteEsperado.setCiudad("Anycity");

    int clienteId = 1;

    //Inicializar BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init-D5-D6.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //Invocar a la SUT
    Cliente clienteActual = Assertions.assertDoesNotThrow(() -> clienteDAO.retrieve(clienteId));

    //Comprobar resultado
    Assertions.assertTrue(clienteEsperado.equals(clienteActual));
  }

}
