package abd.p1.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import abd.p1.bd.DAO;
import abd.p1.model.Preferencia;
import abd.p1.model.Sexo;
import abd.p1.model.Usuario;
import abd.p1.view.Login;
import abd.p1.view.MainWindow;

public class EditUserProfileController
{
	private Usuario usuario;
	private DAO dao;
	
	/**
	 * Main constructor
	 * 
	 * @param usuario
	 * @param dao
	 */
	public EditUserProfileController(Usuario usuario, DAO dao)
	{
		this.usuario = usuario;
		this.dao = dao;
	}
	
	/**
	 * Set new pass
	 * 
	 * @param pass
	 */
	public void updatePass(String pass)
	{
		usuario.setPass(pass);
	}
	
	/**
	 * If user is inside database, will return to MainWindow otherwise will open Login
	 */
	public void checkCancel()
	{
		Usuario user = dao.findUserByEmail(usuario.getEmail());
		
		if(user == null)
		{
			LoginController loginController = new LoginController(dao);
			Login login = new Login(null, true, loginController);
			login.setVisible(true);
		}
		
	}

	/**
	 * Commit user inside database if already exists, otherwise will create new user
	 */
	public void commitUser()
	{
		if(dao.findUserByEmail(usuario.getEmail()) != null)
		{
			dao.updateUser(usuario);
		}
		else
		{
			dao.insertUser(usuario);
			MainWindowController mainWindowController = new MainWindowController(usuario, dao);
			MainWindow mainWindow = new MainWindow(null, true, mainWindowController);
			mainWindow.setVisible(true);
		}
	}
	
	/**
	 * Called from "CAMBIAR NOMBRE" button
	 * 
	 * @param name
	 */
	public void updateName(String name)
	{
		usuario.setNombre(name);
	}
	
	/**
	 * Update description
	 * 
	 * @param description
	 */
	public void updateDescription(String description)
	{
		usuario.setDescripcion(description);
	}
	
	/**
	 * Called from "CAMBIAR FECHA NACIMIENTO" button
	 * 
	 * @param birth
	 */
	public void updateBirth(Date birth)
	{
		usuario.setFecha_nacimimento(birth);
	}
	
	/**
	 * Called from "CAMBIAR SEXO" button
	 * 
	 * @param gender
	 */
	public void updateGender(Sexo gender)
	{
		usuario.setSexo(gender);
	}
	
	/**
	 * Called from "CAMBIAR PREFERENCIA" button
	 * 
	 * @param preference
	 */
	public void updatePreference(Preferencia preference)
	{
		usuario.setPreferencia(preference);
	}
	
	/**
	 * Called from when we need to UPDATE hobbies from buttons:
	 * 
	 *  "AÑADIR SELECCION"
	 *  "EDITAR SELECCION"
	 *  "ELIMINAR SELECCION"
	 * 
	 * @param aficiones
	 */
	public void updateHobbies(List<String> aficiones)
	{
		usuario.setAficiones(aficiones);
	}
	
	
	public void updateAvatar(String path) throws IOException
	{
		String extension = path.substring(path.lastIndexOf('.')+1);

		BufferedImage imagenAvatar = ImageIO.read(new File(path));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(imagenAvatar, extension, baos);
		baos.flush();
		byte[] avatar = baos.toByteArray();
		baos.close();
		
		usuario.setImagen(avatar);
	}
	
	/**
	 * Return the list of hobbies of user to initialize at UserProfilePanel
	 * 
	 * @return
	 */
	public List<String> getAficiones()
	{
		return dao.getAficiones(usuario);
	}

}
