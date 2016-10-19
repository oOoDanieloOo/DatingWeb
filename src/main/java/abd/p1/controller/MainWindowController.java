package abd.p1.controller;

import java.util.List;

import abd.p1.bd.DAO;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;
import abd.p1.model.Valoracion;
import abd.p1.model.ValoracionUsuario;
import abd.p1.view.AnswerDialog;
import abd.p1.view.EditUserProfile;
import abd.p1.view.InviteAnswerQuestion;
import abd.p1.view.UserProfile;

public class MainWindowController
{
	private DAO dao;
	private Usuario usuario;
	private EditUserProfileController editUserProfileController;
	
	/**
	 * Main constructor
	 * 
	 * @param mainWindow
	 * @param sf
	 */
	public MainWindowController(Usuario usuario, DAO dao)
	{
		this.usuario = usuario;
		this.dao = dao;
		this.editUserProfileController = new EditUserProfileController(usuario, dao);
	}
	
	/**
	 * Modifies user profile
	 */
	public void modificarPerfil()
	{
		new EditUserProfile(null, true, editUserProfileController, usuario).setVisible(true);
	}

	/**
	 * Filter by name and preference
	 * 
	 * @param name
	 * @return
	 */
	public List<Usuario> filterByNameAndDistance(String name)
	{
		return this.dao.findUserContaining(name, usuario.getPreferencia(), usuario.getSexo(), usuario.getId(), usuario.getLatitud(), usuario.getLongitud());
	}
	
	/**
	 * Filter by name and preference
	 * 
	 * @return
	 */
	public List<Usuario> filterByDistanceOnly()
	{
		return this.dao.filterByDistance(usuario.getPreferencia(), usuario.getSexo(), usuario.getId(), usuario.getLatitud(), usuario.getLongitud());
	}
	
	/**
	 * Get selected user profile
	 * 
	 * @param selectedUser
	 */
	public void verPerfilSeleccionado(Usuario selectedUser)
	{
		if (selectedUser != null)
		{
			EditUserProfileController editUserProfileController = new EditUserProfileController(selectedUser, dao);
			UserProfileController userProfileController = new UserProfileController(selectedUser, usuario, dao);
			new UserProfile(null, true, editUserProfileController, userProfileController, selectedUser, this.usuario).setVisible(true);
		}
	}

	/**
	 * Return the list of questions of user to initialize at QuestionPanel
	 * 
	 * @return
	 */
	public List<Pregunta> getQuestions()
	{
		return dao.getBestRatedQuestions();
	}
	
	/**
	 * Return 1 random question
	 * 
	 * @return
	 */
	public Pregunta getRandomQuestion()
	{
		return dao.getRandomQuestion();
	}

	/**
	 * Open answer dialog
	 */
	public void contestarPregunta(Pregunta pregunta)
	{
		new AnswerDialog(null, true, this, pregunta).setVisible(true);
	}
	
	/**
	 * Get answers from a question
	 * 
	 * @param id
	 * @return
	 */
	public List<Opcion> getAnswers(Integer id)
	{
		return dao.getAnswers(id);
	}

	/**
	 * Open InviteAnswerQuestion dialog 
	 * 
	 * @param id
	 */
	public void inviteAnswerQuestion(Pregunta pregunta)
	{
		new InviteAnswerQuestion(null, true, this, pregunta).setVisible(true);
	}

	/**
	 * Rates a question from database
	 * 
	 * @param pregunta
	 * @param opcion
	 */
	public void commitAnswer(Opcion opcion, Integer valoracion)
	{
		Valoracion valoration = new Valoracion(new ValoracionUsuario(this.usuario, opcion), valoracion);
		
		this.dao.commitAnswer(valoration, this.usuario.getId());
		this.dao.getBestRatedQuestions();
	}

	/**
	 * Show user friends
	 * 
	 * @return
	 */
	public List<Usuario> filterByFriendsOnly()
	{
		return this.dao.filterByFriendsOnly(usuario.getId(), usuario.getLatitud(), usuario.getLongitud());
	}

	/**
	 * Show user friends that contains a string in the filter box
	 * 
	 * @param text
	 * @return
	 */
	public List<Usuario> filterByNameDistanceAndFriends(String text)
	{
		return this.dao.filterByNameDistanceAndFriends(text,usuario.getId(), usuario.getLatitud(), usuario.getLongitud());
	}

	/**
	 * Send a question to user to answer it
	 * 
	 * @param selectedUser
	 * @param idPregunta
	 */
	public void sendQuestionMessage(Usuario selectedUser, Pregunta pregunta)
	{
		this.dao.sendQuestionMessage(usuario, selectedUser, pregunta);
	}

	/**
	 * Mark all user received messages as read 
	 */
	public void markAsReadAllMessagesReceived()
	{
		this.dao.markAsReadAllMessagesReceived(usuario.getId(), usuario);
	}

	/**
	 * Get all user messages not read
	 * 
	 * @return
	 */
	public String getAllMessagesNotRead()
	{
		return this.dao.getAllMessagesNotRead(usuario.getId());
	}

	/**
	 * Call DAO to search question and opens it
	 * 
	 * @param question
	 */
	public void hyperLinkOpenQuestion(String question)
	{
		Pregunta pregunta = this.dao.searchQuestionById(question);
		
		new AnswerDialog(null, true, this, pregunta).setVisible(true);
	}

	/**
	 * Call DAO to search user and open his profile
	 * 
	 * @param string
	 */
	public void hyperLinkOpenUser(String string)
	{
		Usuario user = this.dao.findUserByEmail(string);
		
		new UserProfile(null, true, editUserProfileController, new UserProfileController(user, usuario, dao), user, usuario).setVisible(true);
	}

}
