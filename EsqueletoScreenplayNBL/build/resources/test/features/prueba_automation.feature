Feature: PRUEBA AUTOMATION - NICOLAS BOLIVAR LEON

  @CompletarFormulario
  Scenario Outline: Prueba automatizacion
    Given el usuario ingresa en la pagina de prueba de automatizacion con sus credenciales <username>,<password>
    When  el usuario ingresa en el apartado test y completa el formulario 10 veces
    Then el envio del formulario ha sido completado con exito
    Examples:
      | username | password                                         |
      | 640950   | 10df2f32286b7120Mi00LTA1OTA0Ng==30e0c83e6c29f1c3 |
