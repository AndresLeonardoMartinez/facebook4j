package colegaGrupo.coleguita;

import facebook4j.Facebook;

import java.net.URL;
import java.util.*;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Group;
import facebook4j.PostUpdate;
import facebook4j.RawAPIResponse;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.org.json.JSONObject;

public class ConexionFacebook {

	private List<String> lista;
	private Map<String, String> mapeo;

	public ConexionFacebook() {
		lista = new ArrayList<String>();
		mapeo = new HashMap<String, String>();
	}

	/*
	 * entrada String : palabra clave para buscar grupos salida Lista <String> :
	 * conjunto de grupos vacios
	 * 
	 */
	public List<String> buscarGrupos(Facebook facebook, String aBuscar) {
		List<String> aRetornar = new ArrayList<String>();

		ResponseList<Group> results = null;
		// Reading reading = new Reading().limit(25).fields("comments");
		int j = 0;
		for (int b = 1; b <= 30; b++) {
			try {
				results = facebook.searchGroups(aBuscar, new Reading().limit(10).offset(j));
				lista.clear();
				System.out.println("j vale = " + j);
			} catch (FacebookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < results.size(); i++) {
				Group grupo = results.get(i);
				String id = grupo.getId();
				String nombreGrupo = grupo.getName();
				lista.add(id);
				mapeo.put(id, nombreGrupo); // id - nombre del grupo
				// System.out.println(b+" " +i+" "+grupo);
			}
			for (int i = 0; i < results.size(); i++) {

				RawAPIResponse a = null;
				try {
					String id = lista.get(i);
					String nombreGrupo = mapeo.get(id);
					a = facebook.callGetAPI(id + "/admins");
					JSONObject json = null;
					json = a.asJSONObject();
					String data = json.getString("data");
					if (data.equals("[]")) {
						aRetornar.add(nombreGrupo);
						System.out.println(i + ") " + nombreGrupo + " " + data);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			j = lista.size() * b;

		}
		return aRetornar;
	}

	public void publicEnGrupo(Facebook facebook, String Grupo) {
		ResponseList<Group> results = null;
		try {
			//results = facebook.searchGroups(Grupo, new Reading().limit(10));
			
//			facebook.postGroupFeed("124254214899470", "hola");
			//facebook.postGroupStatusMessage("124254214899470", "hola mundo");
			PostUpdate post = new PostUpdate(new URL("http://facebook4j.org"))
                    .picture(new URL("http://facebook4j.org/images/hero.png"))
                    .name("Facebook4J - A Java library for the Facebook Graph API")
                    .caption("facebook4j.org")
                    .description("Facebook4J is a Java library for the Facebook Graph API.");
			facebook.postGroupFeed("124254214899470", post);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public Facebook configurar() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthAppId("493229024368521").setOAuthAppSecret("c429f1b8bf7bd50cfe4d57516bc1fc26")
		.setOAuthAccessToken(
				"EAAHAltTKV4kBABfrR4JCgOxZBPhPVnXbVqyLe8dXgKJ1765GQAlQMbr1HUWyptvt2jSPbrFmxmh9jtkhq3oIbXDnHJ9pC44HdfX9oOVdZBDUC5QxLqYPSZALPOTJEMstb5CvZCZAWF2KFZCcfqJIxjn7BjuXkZAd4MZD")
		// EAACEdEose0cBAAC5ZB7ViERdxREOqMGMmGQeDjY6OF6OVok2G2kLt0wXX7bMj8cIgBUYsDociqUkne87FckBrUn2ZCXzEWK3SzUCsF0IuV8NIoAZAmCYEzbAK9lV4ds2rLKxXDzxl33vL1MoWFWdAjs4FiXBc3im0qlj2XXYDCZBGOZCgK4ij5ebVi8dsElCW4Kf8bpYkVAZDZD
			.setOAuthPermissions("publish_actions");
		FacebookFactory ff = new FacebookFactory(cb.build());
		return ff.getInstance();
		
	}
}
