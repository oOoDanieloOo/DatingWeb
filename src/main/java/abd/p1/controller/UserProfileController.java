package abd.p1.controller;

import java.text.DecimalFormat;
import java.util.List;

import abd.p1.bd.DAO;
import abd.p1.model.Usuario;

public class UserProfileController
{
	private Usuario usuario;
	private Usuario selectedUser;
	private DAO dao;
	
	/**
	 * Main constructor
	 * 
	 * @param usuario
	 * @param dao
	 */
	public UserProfileController(Usuario selectedUser, Usuario usuario, DAO dao)
	{
		this.usuario = usuario;
		this.selectedUser = selectedUser;
		this.dao = dao;
	}

	/**
	 * Distance from user to selected profile user
	 * 
	 * @return distance in meters
	 */
	public String getDistance()
	{		
		
		final int R = 6371000;// Earth radius in meters
		double c;
		double a;

		double latitudUser = Math.toRadians(this.usuario.getLatitud());
		double latitudSelectedUser = Math.toRadians(this.selectedUser.getLatitud());
		
		double longitudUser = Math.toRadians(this.usuario.getLongitud());
		double longitudSelectedUser = Math.toRadians(this.selectedUser.getLongitud());
		
		a = Math.pow(Math.sin((latitudUser - latitudSelectedUser)/2),2) + 
				Math.cos(latitudUser) * Math.cos(latitudSelectedUser) * Math.pow(Math.sin((longitudUser - longitudSelectedUser)/2),2);
		c = Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return convertDistance(R, c);
	}
	
	/**
	 * Converts to kilometers if the distance is more than 999 meters
	 * otherwise will remain unchanged
	 * 
	 * @param R
	 * @param c
	 * @return
	 */
	private String convertDistance(double R, double c)
	{
		Double distance = R * c;
		DecimalFormat decimals = new DecimalFormat("0");
		
		if((distance) > 999)
		{
			return decimals.format(distance / 1000) + " km";
		}
		else
		{
			return decimals.format(R*c) + " metros";
		}

	}
	
	/**
	 * Return the list of hobbies of user to initialize at UserProfilePanel
	 * 
	 * @return
	 */
	public List<String> getCompatibleHobbies()
	{
		return dao.getCompatibleHobbies(usuario, selectedUser);
	}

	/**
	 * Insert new friend relationship
	 */
	public boolean makeFriend()
	{
		return dao.makeFriend(usuario, selectedUser);
	}

	/**
	 * Send friend message
	 */
	public void sendFriendMessage()
	{
		dao.sendFriendMessage(usuario, selectedUser);
	}

	/**
	 * Get compatibility percent with selected user
	 * 
	 * @return
	 */
	public double getCompatibilityPercent()
	{
		return dao.getPercent(usuario.getId(), selectedUser.getId());
	}

	/**
	 * Get all text messages from user and selected user
	 * 
	 * @return
	 */
	public String getAllTextMessages()
	{
		return dao.getAllTextMessages(usuario.getId(), selectedUser.getId());
	}

	/**
	 * Send message to selected user
	 * 
	 * @param text
	 */
	public void sendSingleMessage(String text)
	{
		dao.sendSingleMessage(this.usuario, this.selectedUser, text);
	}
}
