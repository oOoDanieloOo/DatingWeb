package abd.p1.bd;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import abd.p1.model.Mensaje;
import abd.p1.model.MensajeAmistad;
import abd.p1.model.MensajePregunta;
import abd.p1.model.MensajeTexto;
import abd.p1.model.Opcion;
import abd.p1.model.Preferencia;
import abd.p1.model.Pregunta;
import abd.p1.model.Sexo;
import abd.p1.model.Usuario;
import abd.p1.model.Valoracion;

public class DAO
{
	private SessionFactory sf;

	/**
	 * Main constructor
	 * 
	 * @param sf
	 */
	public DAO(SessionFactory sf)
	{
		this.sf = sf;
	}

	/**
	 * Inserts a user inside data base
	 * 
	 * @param usuario
	 * @return
	 */
	public void insertUser(Usuario usuario)
	{
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.save(usuario);
		tr.commit();
		session.close();
	}

	/**
	 * Updates a user of the database
	 * 
	 * @param usuario
	 * @return
	 */
	public void updateUser(Usuario usuario)
	{
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.update(usuario);
		tr.commit();
		session.close();
	}

	/**
	 * Finds a user from email
	 * 
	 * @param email
	 * @return
	 */
	public Usuario findUserByEmail(String email)
	{
		Session session = sf.openSession();
		String preQuery = "FROM Usuario AS u WHERE u.email = :text";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setString("text", email);
		Usuario user = (Usuario) finalQuery.uniqueResult();
		session.close();
		return user;
	}

	/**
	 * Finds users filtering by name, preference and distance
	 * 
	 * @param constraint
	 * @param preference
	 * @param sexo
	 * @param id
	 * @param latitud
	 * @param longitud
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findUserContaining(String constraint, Preferencia preference, Sexo sexo, Integer id, double latitud, double longitud)
	{
		Integer ordinalPreference = Preferencia.getPreference(preference);
		Integer ordinalGender = Sexo.getGender(sexo);
		
		List<Integer> coupleComp = getCouple(ordinalGender, ordinalPreference);
		coupleComp.add(ordinalGender);
		coupleComp.add(ordinalPreference);
		
		List<Usuario> users = null;
		Session session = sf.openSession();
		
		if (ordinalPreference != 2)
		{
			String preQuery = "FROM Usuario AS u WHERE u.nombre LIKE :constraint AND (u.preferencia = :pref OR u.preferencia = 2) AND u.sexo = :gender AND u.id != :id "
					+ "ORDER BY ((:latitud-u.latitud)*(:latitud-u.latitud)+(:longitud-u.longitud)*(:longitud-u.longitud))";
			Query finalQuery = session.createQuery(preQuery);
			finalQuery.setInteger("pref", coupleComp.get(1));
			finalQuery.setInteger("id", id);
			finalQuery.setParameter("constraint", "%" + constraint + "%");
			finalQuery.setInteger("gender", coupleComp.get(0));
			finalQuery.setDouble("latitud", latitud);
			finalQuery.setDouble("longitud", longitud);
			finalQuery.setMaxResults(20);
			users = (List<Usuario>) finalQuery.list();
		}
		else
		{
			String preQuery = "FROM Usuario AS u WHERE u.nombre LIKE :constraint AND u.id != :id AND (u.preferencia = :pref OR u.preferencia = 2) "
					+ "ORDER BY ((:latitud-u.latitud)*(:latitud-u.latitud)+(:longitud-u.longitud)*(:longitud-u.longitud))";
			Query finalQuery = session.createQuery(preQuery);
			finalQuery.setInteger("id", id);
			finalQuery.setParameter("constraint", "%" + constraint + "%");
			finalQuery.setInteger("pref", coupleComp.get(0));
			finalQuery.setDouble("latitud", latitud);
			finalQuery.setDouble("longitud", longitud);
			finalQuery.setMaxResults(20);
			users = (List<Usuario>) finalQuery.list();
		}
		session.close();
		return users;
	}
	
	/**
	 * Get all user hobbies from id
	 * 
	 * @param usuario
	 * @return
	 */
	public List<String> getAficiones(Usuario usuario)
	{
		Session session = sf.openSession();
		Usuario user = (Usuario) session.merge(usuario);
		List<String> aficiones = user.getAficiones();
		aficiones.size();
		session.close();
		return aficiones;
	}
	
	/**
	 * Finds users filtering by preference and distance
	 * 
	 * @param preference
	 * @param sexo
	 * @param id
	 * @param latitud
	 * @param longitud
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> filterByDistance(Preferencia preference, Sexo sexo, Integer id, double latitud, double longitud)
	{
		Integer ordinalPreference = Preferencia.getPreference(preference);
		Integer ordinalGender = Sexo.getGender(sexo);
		
		List<Integer> coupleComp = getCouple(ordinalGender, ordinalPreference);
		coupleComp.add(ordinalGender);
		coupleComp.add(ordinalPreference);
		
		List<Usuario> users = null;
		Session session = sf.openSession();
		
		if (ordinalPreference != 2)
		{
			String preQuery = "FROM Usuario AS u WHERE (u.preferencia = :pref OR u.preferencia = 2) AND u.sexo = :gender AND u.id != :id "
					+ "ORDER BY ((:latitud-u.latitud)*(:latitud-u.latitud)+(:longitud-u.longitud)*(:longitud-u.longitud))";
			Query finalQuery = session.createQuery(preQuery);
			finalQuery.setInteger("pref", coupleComp.get(1));
			finalQuery.setInteger("id", id);
			finalQuery.setInteger("gender", coupleComp.get(0));
			finalQuery.setDouble("latitud", latitud);
			finalQuery.setDouble("longitud", longitud);
			finalQuery.setMaxResults(20);
			users = (List<Usuario>) finalQuery.list();
		}
		else
		{
			String preQuery = "FROM Usuario AS u WHERE u.id != :id AND (u.preferencia = :pref OR u.preferencia = 2) "
					+ "ORDER BY ((:latitud-u.latitud)*(:latitud-u.latitud)+(:longitud-u.longitud)*(:longitud-u.longitud))";
			Query finalQuery = session.createQuery(preQuery);
			finalQuery.setInteger("id", id);
			finalQuery.setInteger("pref", coupleComp.get(0));
			finalQuery.setDouble("latitud", latitud);
			finalQuery.setDouble("longitud", longitud);
			finalQuery.setMaxResults(20);
			users = (List<Usuario>) finalQuery.list();
		}
		session.close();
		return users;
	}
	
	/**
	 * Show the twenty best rated questions
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pregunta> getBestRatedQuestions()
	{
		Session session = sf.openSession();
		String preQuery = "SELECT p FROM Pregunta p JOIN p.opciones o LEFT JOIN Valoracion v ON v.id.opcion.id = o.id" 
				+ " GROUP BY p.id ORDER BY AVG(v.valoracionNumero) DESC";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setMaxResults(20);
		List<Pregunta> preguntas = (List<Pregunta>) finalQuery.list();
		session.close();
		return preguntas;
	}

	/**
	 * Show a random question
	 * 
	 * @return
	 */
	public Pregunta getRandomQuestion()
	{
		Session session = sf.openSession();
		String preQuery = "FROM Pregunta ORDER BY RAND()";
		Query finalQuery = session.createQuery(preQuery).setMaxResults(1);
		Pregunta pregunta = (Pregunta) finalQuery.uniqueResult();
		session.close();
		return pregunta;
	}

	/**
	 * Retrieves all answer from a question
	 * 
	 * @param pregunta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> getAnswers(Integer pregunta)
	{
		Session session = sf.openSession();
		String preQuery = "FROM Opcion AS o WHERE o.preguntaMadre = :id";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setInteger("id", pregunta);
		List<Opcion> opciones = (List<Opcion>) finalQuery.list();
		session.close();
		return opciones;
	}

	/**
	 * Update an answer from database
	 * 
	 * @param valoracion
	 * @param idUsuario
	 */
	public void commitAnswer(Valoracion valoracion, Integer idUsuario)
	{
		this.deleteAnswer(valoracion.getId().getOpcion().getId(), idUsuario);
		
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.save(valoracion);
		tr.commit();
		session.close();
	}
	
	/**
	 * Delete user rating for that question, after that insert new answer
	 * 
	 * @param idOpcion
	 * @param idUsuario
	 */
	@SuppressWarnings("unchecked")
	private void deleteAnswer(Integer idOpcion, Integer idUsuario)
	{
		List<Valoracion> listaValoraciones = null;
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		String preQuery = "SELECT v FROM Valoracion v LEFT JOIN Opcion o ON o.id = v.id.opcion.id LEFT JOIN Pregunta p ON p.id = o.preguntaMadre.id"
				+ " WHERE v.id.usuario.id = :idUser AND p.id = (SELECT preguntaMadre.id FROM Opcion o WHERE o.id = :idOpt)";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setInteger("idUser", idUsuario);
		finalQuery.setInteger("idOpt", idOpcion);
		listaValoraciones = finalQuery.list();
		if(!listaValoraciones.isEmpty())
		{
			Valoracion v = session.get(Valoracion.class, listaValoraciones.get(0).getId());
			session.delete(v);
			tr.commit();
		}
		session.close();
	}

	/**
	 * Get compatible hobbies from main user and selected user
	 * 
	 * @param usuario
	 * @param selectedUser
	 * @return
	 */
	public List<String> getCompatibleHobbies(Usuario usuario, Usuario selectedUser)
	{
		List<String> userHobbies = this.getAficiones(usuario);
		List<String> selectedUserHobbies = this.getAficiones(selectedUser);
		List<String> compatibleHobbies = new LinkedList<String>();
		
		if (userHobbies != null)
		{
			for (String aficion : userHobbies)
			{
	            if(selectedUserHobbies.contains(aficion))
	            {
	            	compatibleHobbies.add(aficion);
	            }
	        }
		}
		return compatibleHobbies;
	}

	/**
	 * Calculates the compatibility percent of both users
	 * 
	 * @param idUser
	 * @param idSelectedUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public double getPercent(Integer idUser, Integer idSelectedUser)
	{
		Session session = sf.openSession();
		String preQuery = "SELECT v.valoracionNumero, o.preguntaMadre.id, v.id.opcion.id"
				+ " FROM Valoracion v LEFT JOIN Opcion o ON o.id = v.id.opcion.id"
				+ " WHERE v.id.usuario.id = :user";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setParameter("user", idUser);
		List<Object[]> lista1 = (List<Object[]>) finalQuery.list();
		session.close();
		
		Session session2 = sf.openSession();
		preQuery = "SELECT v.valoracionNumero, o.preguntaMadre.id, v.id.opcion.id"
				+ " FROM Valoracion v LEFT JOIN Opcion o ON o.id = v.id.opcion.id"
				+ " WHERE v.id.usuario.id = :selectedUser";
		finalQuery = session2.createQuery(preQuery);
		finalQuery.setParameter("selectedUser", idSelectedUser);
		List<Object[]> lista2 = (List<Object[]>) finalQuery.list();
		session2.close();

		int aux = 0;
		int mTotal = 0;
		int mAcierto = 0;
		double porcentaje;
		
		for (Object[]  l1 : lista1)
		{
			aux = (int) l1[1];
		    for (Object[] l2 : lista2)
		    {
		    	if (aux == (int)l2[1])
		    	{
		    		mTotal += (int)l1[0] + (int)l2[0];
		    	}
		    }
		}	
		aux = 0;
		
		for (Object[]  l1 : lista1)
		{
			aux = (int) l1[2];
		    for (Object[] l2 : lista2)
		    {
		    	if (aux == (int)l2[2])
		    	{
		    		mAcierto += (int)l1[0] + (int)l2[0];
		    	}
		    }
		}
		porcentaje = (mAcierto/(double)mTotal) * 100;
		
		return porcentaje;
	}
	
	/**
	 * Insert new friend relationship
	 * 
	 * @param usuario
	 * @param selectedUser
	 * @return TRUE if users were friends
	 */
	public boolean makeFriend(Usuario usuario, Usuario selectedUser)
	{
		Session session = sf.openSession();
		boolean igual = false;
		Transaction tr = session.beginTransaction();
		Usuario user = (Usuario) session.merge(usuario);
		List<Usuario> listaUsuario = new LinkedList<Usuario>();
		listaUsuario = user.getContactos();
		
		for (Usuario u : listaUsuario)
		{
        	if (u.getEmail().equals(selectedUser.getEmail()))
        		igual = true;
        }
		
		if (!igual)
		{
			listaUsuario.add(selectedUser);
			listaUsuario.size();
			session.update(user);
			tr.commit();
			session.close();
			this.makeFriendship(selectedUser, usuario);
			return false;
		}
		else
		{
			session.close();
			return true;
		}
	}
	
	/**
	 * Make friends with the user that sent the friendship request
	 * 
	 * @param usuario
	 * @param selectedUser
	 */
	public void makeFriendship(Usuario usuario, Usuario selectedUser)
	{
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Usuario user = (Usuario) session.merge(usuario);
		List<Usuario> listaUsuario = user.getContactos();
		listaUsuario.add(selectedUser);
		listaUsuario.size();
		session.update(user);
		tr.commit();
		session.close();
	}

	/**
	 * Send friend message
	 * 
	 * @param user
	 * @param selectedUser
	 */
	public void sendFriendMessage(Usuario user, Usuario selectedUser)
	{
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		MensajeAmistad mensajeAmistad = new MensajeAmistad(user, selectedUser, Calendar.getInstance(), false);
		session.save(mensajeAmistad);
		tr.commit();
		session.close();
	}

	/**
	 * Get user friends
	 * 
	 * @param id
	 * @param latitud
	 * @param longitud
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> filterByFriendsOnly(Integer id, double latitud, double longitud)
	{
		List<Usuario> users = null;
		Session session = sf.openSession();

		String preQuery = "SELECT u FROM Usuario AS u JOIN u.contactos AS c WHERE u.id != :id AND c.id = :id "
				+ "ORDER BY ((:latitud-u.latitud)*(:latitud-u.latitud)+(:longitud-u.longitud)*(:longitud-u.longitud)) DESC";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setInteger("id", id);
		finalQuery.setDouble("latitud", latitud);
		finalQuery.setDouble("longitud", longitud);
		users = (List<Usuario>) finalQuery.list();
		
		session.close();
		return users;
	}

	/**
	 * Get user friends that contains a key word
	 * 
	 * @param constraint
	 * @param id
	 * @param latitud
	 * @param longitud
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> filterByNameDistanceAndFriends(String constraint, Integer id, double latitud, double longitud)
	{
		List<Usuario> users = null;
		Session session = sf.openSession();
	
		String preQuery = "SELECT u FROM Usuario AS u JOIN u.contactos WHERE u.id != :id AND u.nombre LIKE :constraint "
				+ "ORDER BY ((:latitud-u.latitud)*(:latitud-u.latitud)+(:longitud-u.longitud)*(:longitud-u.longitud)) DESC";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setParameter("constraint", "%" + constraint + "%");
		finalQuery.setInteger("id", id);
		finalQuery.setDouble("latitud", latitud);
		finalQuery.setDouble("longitud", longitud);
		users = (List<Usuario>) finalQuery.list();
		
		session.close();
		return users;
	}

	/**
	 * Send a question to user to answer it
	 * 
	 * @param usuario
	 * @param selectedUser
	 * @param pregunta
	 */
	public void sendQuestionMessage(Usuario usuario, Usuario selectedUser, Pregunta pregunta)
	{
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		MensajePregunta mensajePregunta = new MensajePregunta(usuario, selectedUser, Calendar.getInstance(), false, pregunta);
		session.save(mensajePregunta);
		tr.commit();
		session.close();
	}

	/**
	 * Get all text messages from user and selected user
	 * 
	 * @param usuario
	 * @param selectedUser
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllTextMessages(Integer usuario, Integer selectedUser)
	{
		Session session = sf.openSession();
		String preQuery = "FROM Mensaje AS m WHERE (m.emisor = :idUser AND m.receptor = :idSelectedUser) OR (m.emisor = :idSelectedUser AND m.receptor = :idUser)"
				+ " ORDER BY m.hora";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setInteger("idUser", selectedUser);
		finalQuery.setInteger("idSelectedUser", usuario);
		List<Mensaje> listaMensajes = finalQuery.list();
		session.close();
		
		String list = "";
		for (Mensaje m : listaMensajes)
	    {
			if (m instanceof MensajeTexto)
				list += m.toString();
	    }
		
		return list;
	}

	/**
	 * Send message to selected user
	 * 
	 * @param usuario
	 * @param selectedUser
	 * @param text
	 */
	public void sendSingleMessage(Usuario usuario, Usuario selectedUser, String text)
	{
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		MensajeTexto mensajeTexto = new MensajeTexto(usuario, selectedUser, Calendar.getInstance(), false, text);
		session.save(mensajeTexto);
		tr.commit();
		session.close();
	}

	/**
	 * Mark all user received messages as read
	 * 
	 * @param usuarioId
	 * @param u
	 */
	public void markAsReadAllMessagesReceived(Integer usuarioId, Usuario u)
	{
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		String preQuery = "UPDATE Mensaje AS m SET m.leido = TRUE WHERE m.receptor = :id";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setInteger("id", usuarioId);
		finalQuery.executeUpdate();
		tr.commit();
		session.close();
	}

	/**
	 * Get all user messages not read
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllMessagesNotRead(Integer userId)
	{
		Session session = sf.openSession();
		String preQuery = "FROM Mensaje AS m WHERE m.receptor = :id AND m.leido = FALSE ORDER BY m.hora";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setInteger("id", userId);
		List<Mensaje> listaMensajes = finalQuery.list();
		session.close();
		
		String list = "";
		for (Mensaje m : listaMensajes)
	    {
	    	list += m.toString();
	    }
		
		return list;
	}

	/**
	 * Get user couple
	 * 
	 * @param sexo
	 * @param preference
	 * @return
	 */
	private List<Integer> getCouple(Integer sexo, Integer preference)
	{
		List<Integer> res = new LinkedList<Integer>();
		
		if (sexo == 0 && preference == 1) 
		{
			res.add(1);
			res.add(0);
			return res;
		}
		if (sexo == 1 && preference == 0)
		{
			res.add(0);
			res.add(1);
			return res;
		}
		if (sexo == 0 && preference == 0)
		{
			res.add(0);
			res.add(0);
			return res;
		}
		if (sexo == 1 && preference == 1)
		{
			res.add(1);
			res.add(1);
			return res;
		}
		if (sexo == 1 && preference == 2)
		{
			res.add(1);
			return res;
		}
		if (sexo == 0 && preference == 2)
		{
			res.add(0);
			return res;
		}
		return res;
	}

	/**
	 * Search question from id
	 * 
	 * @param question
	 * @return
	 */
	public Pregunta searchQuestionById(String question)
	{
		Session session = sf.openSession();
		String preQuery = "FROM Pregunta AS p WHERE p.id = :text";
		Query finalQuery = session.createQuery(preQuery);
		finalQuery.setString("text", question);
		Pregunta pregunta = (Pregunta) finalQuery.uniqueResult();
		session.close();
		return pregunta;
	}
}
