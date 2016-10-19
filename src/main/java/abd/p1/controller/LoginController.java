package abd.p1.controller;

import javax.swing.JOptionPane;

import abd.p1.bd.DAO;
import abd.p1.model.Usuario;
import abd.p1.view.EditUserProfile;
import abd.p1.view.MainWindow;

public class LoginController
{
	
	private DAO dao;
	
	/**
	 * Main constructor
	 * 
	 * @param sf
	 */
	public LoginController(DAO dao)
	{
		this.dao = dao;
	}
	
	/**
	 * Check email and password to enter the program  
	 * 
	 * @param email - user email
	 * @param pass - user password
	 * @return true if the user is valid
	 */
	public boolean checkUser(String email, String pass)
	{
				
		// Check empty text field
		if (((email == null) || email.equals("")) || ((pass == null) || (pass.equals(""))))
		{
			JOptionPane.showMessageDialog(null, "Introduzca un email y contraseña válidos");
		}
		// Checks if user is inside database
		else
		{
			Usuario user = dao.findUserByEmail(email);
			
			// User doesn't exist
			if (user == null)
			{
				JOptionPane.showMessageDialog(null, "El usuario no existe");
			}
			else
			{
				String bdPass = user.getPass();
				
				// User exists and password is correct
				if(bdPass.equals(pass))
				{
					JOptionPane.showMessageDialog(null, "Bienvenido " + user.getNombre());
					MainWindowController mainWindowController = new MainWindowController(user, dao);
					MainWindow mainWindow = new MainWindow(null, true, mainWindowController);
					mainWindow.setVisible(true);
					
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				}
			}
		}
		
		return false;
	}
	
	public boolean newUser(String email, String pass)
	{
		
		// Check empty text field
		if (((email == null) || email.equals("")) || ((pass == null) || (pass.equals(""))))
		{
			JOptionPane.showMessageDialog(null, "Introduzca un email y contraseña válidos");
		}
		// Checks if user is inside database
		else
		{
			Usuario user = dao.findUserByEmail(email);
			
			// User doesn't exist
			if (user == null)
			{
				JOptionPane.showMessageDialog(null, "Bienvenido, rellena tu perfil para empezar a ligar");
				// Uses a constructor that gives random latitude and longitude
				Usuario usuario = new Usuario(email, pass);
				EditUserProfileController editUserProfileController = new EditUserProfileController(usuario, dao);
				EditUserProfile editUserProfile = new EditUserProfile(null, true, editUserProfileController, usuario);
				editUserProfile.setVisible(true);
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Ya existe un usuario con este email");
			}
		}
		
		return false;
	}
	
}
