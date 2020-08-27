B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=8.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim webservice2 As HttpJob
	'Private RbPlantas As RadioButton
	'Private RbOtros As RadioButton
	Private btnVolver As Button
	Private ButtonGuardar As Button
	Private etNombre As EditText
	Private etModelo As EditText
	Private etTipo As EditText
	Private etPrecio As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("PantallaEdicion")
	webservice2.Initialize("webservice2", Me)
End Sub

Sub Activity_Resume
	etNombre.Text = Main.nombreProducto
	etModelo.Text = Main.modeloProducto
	etTipo.Text = Main.tipoProducto
	etPrecio.Text = Main.precioProducto
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub btnVolver_Click
	StartActivity("Main")
	Activity.Finish
End Sub

Sub btnGuardar_Click
	Dim nombreSinEspacio As String
	Dim tipoSinEspacio As String
	Dim precioSinEspacio As String
	Dim modeloSinEspacio As String
	'guardar datos
	If etNombre.Text <> "" Then
		ProgressDialogShow("Guardando datos...")
		nombreSinEspacio = etNombre.Text.Replace(" ","%20")
		modeloSinEspacio = etModelo.Text.Replace(" ","%20")
		tipoSinEspacio = etTipo.Text.Replace(" ","%20")
		precioSinEspacio = etPrecio.Text.Replace(" ","%20")
		'webservice2.Download("https://submersible-defeat.000webhostapp.com/modificar.php?id=" & Main.idProducto & "&nombre=" & nombreSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio)
		webservice2.Download("https://elmundodelasplantas.000webhostapp.com/modificar.php?id=" & Main.idProducto & "&nombre=" & nombreSinEspacio & "&modelo=" & modeloSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio)
		'webservice2.Download("http://192.168.0.18/EMDLP/modificar.php?id=" & Main.idProducto & "&nombre=" & nombreSinEspacio & "&modelo=" & modeloSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio)
		Main.SQL1.ExecNonQuery("update productos set nombre='" & etNombre.Text & "' , modelo='" & etModelo.Text & "' , tipo='" & etTipo.Text & "', precio='" & etPrecio.Text & "' where id=" & Main.idProducto)
		'SQL1.ExecQuery("select * from productos where nombre like '%" & txtBuscar.Text & "%' and categoria = 'plantas'")
		
	End If
End Sub

Sub JobDone(Job As HttpJob)
	Dim result As String
	result = Job.GetString
	ProgressDialogHide
	If result = "ok" Then
		Msgbox ("El registro se guardo correctamente","")
		'Main.recargarPagina = True
		StartActivity("Main")
		Activity.Finish
	End If
	
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean
	Dim mensaje As Int
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		mensaje = Msgbox2 ("¿Cerrar el programa?","","Si","","No",Null)
		If mensaje = -1 Then
			ExitApplication
		Else
			Return True
		End If
	End If
End Sub