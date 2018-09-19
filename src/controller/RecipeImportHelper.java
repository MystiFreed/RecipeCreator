package controller;
// Mysti Freed, mrfreed@dmacc.edu 
// 9/19/2018

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import org.eclipse.persistence.jpa.PersistenceProvider;
import javax.persistence.TypedQuery;

import model.RecipeImport;


public class RecipeImportHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("RecipeCreator");
	public void tidy() {
		emfactory.close();
	}
	
	/**
	 * This method is used to insert a recipe component into the recipe_create table in the recipes database on MySQL
	 * in conjunction with the addLineItem() method from the RecipeCreate program.
	 * @param ri = RecipeImport
	 */
	public void insertComponent(RecipeImport ri) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ri);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * This method is used to remove a recipe component from the recipe_create table in the recipes database on MySQL
	 * in conjunction with the deleteAnItem() method from the RecipeCreate program.
	 * @param toRemove = component to be removed, along with its ID, quantity and measurement
	 */
	public void removeComponent (RecipeImport toRemove) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.component = :selectedComponent", RecipeImport.class);
		typedQuery.setParameter("selectedComponent", toRemove.getComponent());
		typedQuery.setMaxResults(1);
		RecipeImport result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * This method is used to find a recipe component by component name in the recipe_create table in the recipes database on MySQL
	 * in conjunction with the recipeUpdate() method from the RecipeCreate program.
	 * @param recipeComponent = component to search the table by
	 * @return RecipeImport List
	 */
	public List<RecipeImport> findComponent (String recipeComponent) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport where ri.component = :selectedComponent", RecipeImport.class);
		typedQuery.setParameter("selectedComponent", recipeComponent);
		List<RecipeImport> foundComponents = typedQuery.getResultList();
		em.close();
		return foundComponents;
	}
	
	/**
	 * This method is used to show all of the recipe components in the recipe_create table in the recipes database on MySQL
	 * in conjunction with the showRecipe() method from the RecipeCreate program.
	 * @return RecipeImport List
	 */
	public List<RecipeImport> showRecipe() {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri", RecipeImport.class);
		List<RecipeImport> fullRecipe = typedQuery.getResultList();
		em.close();
		return fullRecipe;
	}
	
	/**
	 * This method is used to update a recipe component in the recipe_create table in the recipes database on MySQL
	 * in conjunction with the recipeUpdate() method from the RecipeCreate program.
	 * @param toUpdate = component to be updated
	 */
	public void updateRecipe(RecipeImport toUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * This method is used to find a recipe component by quantity in the recipe_create table in the recipes database on MySQL
	 * in conjunction with the recipeUpdate() method from the RecipeCreate program.
	 * @param componentQuantity
	 * @return RecipeImport List
	 */
	public List<RecipeImport> searchForComponentByQuantity(double componentQuantity) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.quantity = :selectedItem", RecipeImport.class);
		typedQuery.setParameter("selectedItem", componentQuantity);
		List<RecipeImport> foundIt = typedQuery.getResultList();
		em.close();
		return foundIt;
	}
	
	/**
	 * This method is used to find a recipe component by measurement in the recipe_create table in the recipes database on MySQL
	 * in conjunction with the recipeUpdate() method from the RecipeCreate program.
	 * @param componentMeasure
	 * @return RecipeImport List
	 */
	public List<RecipeImport> searchForComponentByMeasurement(String componentMeasure) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.measurement = :selectedItem", RecipeImport.class);
		typedQuery.setParameter("selectedItem", componentMeasure);
		List<RecipeImport> foundIt = typedQuery.getResultList();
		em.close();
		return foundIt;
	}
	
	/**
	 * This method is used to find a recipe component by component name in the recipe_create table in the recipes database on MySQL
	 * in conjunction with the recipeUpdate() method from the RecipeCreate program.
	 * @param theComponent
	 * @return RecipeImport List
	 */
	public List<RecipeImport> searchForComponentByComponent(String theComponent) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<RecipeImport> typedQuery = em.createQuery("select ri from RecipeImport ri where ri.component = :selectedItem", RecipeImport.class);		
		typedQuery.setParameter("selectedItem", theComponent);
		List<RecipeImport> foundIt = typedQuery.getResultList();
		em.close();
		return foundIt;
	}
	
	/**
	 * This method is used to find a recipe component by ID in the recipe_create table in the recipes database on MySQL
	 * in conjunction with the recipeUpdate() method from the RecipeCreate program.
	 * @param id
	 * @return ID
	 */
	public RecipeImport searchForItemByID(int id){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		RecipeImport idFinder = em.find(RecipeImport.class, id);
		em.close();
		return idFinder;
	}
}
