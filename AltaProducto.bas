B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=8.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	'Dim SQL1 As SQL
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	'Dim registro As Cursor
	Private btnVolver As Button
	Private ButtonGuardar As Button
	Private etNombre As EditText
	Private etTipo As EditText
	Private etPrecio As EditText
	'Private RbPlantas As RadioButton
	'Private RbOtros As RadioButton
	Dim webservice3 As HttpJob
	Private etModelo As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("PantallaAlta")
	webservice3.Initialize("webservice2", Me)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub btnVolver_Click
	StartActivity("Main")
	Activity.Finish
End Sub

Sub btnGuardar_Click
	Dim cur As Cursor
	Dim maxId As Int
	Dim nombreSinEspacio As String
	Dim modeloSinEspacio As String
	Dim tipoSinEspacio As String
	Dim precioSinEspacio As String
	Dim valOtros As String
	ProgressDialogShow("Guardando datos...")
	cur = Main.SQL1.ExecQuery("SELECT MAX(id) as mayor FROM productos")
	cur.Position = 0
	maxId = cur.GetInt("mayor") + 1
	nombreSinEspacio = etNombre.Text.Replace(" ","%20")
	modeloSinEspacio = etModelo.Text.Replace(" ","%20")
	tipoSinEspacio = etTipo.Text.Replace(" ","%20")
	precioSinEspacio = etPrecio.Text.Replace(" ","%20")
	valOtros = "otros"
	
	If etNombre.Text = "" Then
		ProgressDialogHide
		ToastMessageShow("El nombre no puede estar vacio", False)
	Else 
		Main.SQL1.BeginTransaction
		'Log(maxId)
		'Log(nombreSinEspacio)
		'Log(modeloSinEspacio)
		'Log(tipoSinEspacio)
		'Log(precioSinEspacio)
		'Log(valOtros)
		Main.SQL1.ExecNonQuery("INSERT INTO productos (id, nombre, modelo, tipo, precio, categoria) VALUES (" & maxId & ", '" & etNombre.Text & "','" & etModelo.Text & "','" & etTipo.Text & "','" & etPrecio.Text & "','otros')")
		'webservice3.Download("http://192.168.0.18/EMDLP/insertar.php?id=" & maxId & "&nombre=" & nombreSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio)
		'webservice3.Download("https://submersible-defeat.000webhostapp.com/insertar.php?id=" & maxId & "&nombre=" & nombreSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio)
		Log("https://elmundodelasplantas.000webhostapp.com/insertar.php?id=" & maxId & "&nombre=" & nombreSinEspacio & "&modelo=" & modeloSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio & "&categoria=" & valOtros )
		'no es necesario el id en el insert ya que lo pone autoincremental
		'webservice3.Download("http://submersible-defeat.000webhostapp.com/insertar.php?id=" & maxId & "&nombre=" & nombreSinEspacio & "&modelo=" & modeloSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio & "&categoria=" & valOtros )
		webservice3.Download("https://elmundodelasplantas.000webhostapp.com/insertar.php?id=" & maxId & "&nombre=" & nombreSinEspacio & "&modelo=" & modeloSinEspacio & "&tipo=" & tipoSinEspacio & "&precio=" & precioSinEspacio & "&categoria=" & valOtros )
	End If
End Sub

Sub JobDone(Job As HttpJob)
	Dim result As String
	result = Job.GetString
	ProgressDialogHide
	If result = "ok" Then
		Main.SQL1.TransactionSuccessful
		Main.SQL1.EndTransaction
		Msgbox ("El registro se guardo correctamente","")
		etNombre.Text = ""
		etModelo.Text = ""
		etTipo.Text = ""
		etPrecio.Text = ""
		'Main.recargarPagina = True
	Else
		Msgbox ("Se encontro un error al guardar los datos: " & result,". Recuerde Sincronizar los datos")
	End If
	
End Sub

