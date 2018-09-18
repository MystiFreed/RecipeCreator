package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.RecipeImport;
//import view.RecipeCreate;

public class RecipeImportHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("RecipeCreator");
	
	public void tidy() {
		emfactory.close();
	}
	
	public void insertComponent(RecipeImport ri) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ri);
		em.getTransaction().commit();
		em.close();
	}
	
	public void removeComponent (RecipeImport toRemove) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.quantity = :selectedQuantity and ri.component = :selectedComponent", RecipeImport.class);
		typedQuery.setParameter("selectedQuantity", toRemove.getQuantity());
		typedQuery.setParameter("selectedComponent", toRemove.getComponent());
		typedQuery.setMaxResults(1);
		RecipeImport result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<RecipeImport> findComponent (String recipeComponent) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.component = :selectedComponent", RecipeImport.class);
		typedQuery.setParameter("selectedComponent", recipeComponent);
		List<RecipeImport> foundComponents = typedQuery.getResultList();
		em.close();
		return foundComponents;
	}
	
	public List<RecipeImport> showRecipe() {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri", RecipeImport.class);
		List<RecipeImport> fullRecipe = typedQuery.getResultList();
		em.close();
		return fullRecipe;
	}
	
	public void updateRecipe(RecipeImport toUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<RecipeImport> searchForComponentByQuantity(double componentQuantity) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.quantity = :selectedItem", RecipeImport.class);
		typedQuery.setParameter("selectedItem", componentQuantity);
		List<RecipeImport> foundIt = typedQuery.getResultList();
		em.close();
		return foundIt;
	}
	
	public List<RecipeImport> searchForComponentByMeasurement(String componentMeasure) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.measurement = :selectedItem", RecipeImport.class);
		typedQuery.setParameter("selectedItem", componentMeasure);
		List<RecipeImport> foundIt = typedQuery.getResultList();
		em.close();
		return foundIt;
	}
	
	public List<RecipeImport> searchForComponentByComponent(String theComponent) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.component = :selectedItem", RecipeImport.class);		
		typedQuery.setParameter("selectedItem", theComponent);
		List<RecipeImport> foundIt = typedQuery.getResultList();
		em.close();
		return foundIt;
	}
	
	public RecipeImport searchForItemByID(int id){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		RecipeImport idFinder = em.find(RecipeImport.class, id);
		em.close();
		return idFinder;
	}
}
