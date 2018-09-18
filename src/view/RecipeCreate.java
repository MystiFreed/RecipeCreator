package view;

import java.util.List;
import java.util.Scanner;

import controller.RecipeImportHelper;
import model.RecipeImport;

public class RecipeCreate {

	static Scanner in = new Scanner(System.in);
	static RecipeImportHelper rih = new RecipeImportHelper();

	private static void addLineItem() {

		System.out.print("Enter the amount (decimals are allowed, for example - 1.25): ");
		Double quantity = in.nextDouble();
		System.out.print("Enter the measurement (T for tablespoon, tsp for teaspoon, or cups, etc): ");
		String measurement = in.nextLine();
		System.out.println("Enter the recipe component: ");
		String component = in.nextLine();
		RecipeImport toAdd = new RecipeImport(quantity, measurement, component);
		rih.insertComponent(toAdd);
	}

	private static void deleteAnItem() {
		
		System.out.print("Enter the quantity of the recipe component to delete: ");
		Double quantity = in.nextDouble();
		System.out.print("Enter the component to delete: ");
		String component = in.nextLine();
		RecipeImport toRemove = new RecipeImport(quantity, component);
		rih.removeComponent(toRemove);
	}

	private static void recipeUpdate() {
		
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by quantity");
		System.out.println("2 : Search by measurement");
		System.out.println("3 : Search by component");
		int searchBy = in.nextInt();
		in.nextLine();
		List<RecipeImport> foundIt;
		/*if (searchBy == 1) {
			System.out.print("Enter the quantity of the component: ");
			Double componentQuantity = in.nextDouble();
			foundIt = rih.searchForComponentByQuantity(componentQuantity);
		} 
		else if (searchBy == 2) {
			System.out.print("Enter the measurement: ");
			String componentMeasure = in.nextLine();
			foundIt = rih.searchForComponentByMeasurement(componentMeasure);
		}
		else if (searchBy == 3) {
			System.out.print("Enter the component: ");
			String theComponent = in.nextLine();
			foundIt = rih.searchForComponentByComponent(theComponent);
		}
		if (!foundIt.isEmpty()) {
			System.out.println("Found Results.");
			for (RecipeImport l : foundIt) {
				System.out.println(l.getId() + " : " + l.toString()); // does this need modified?
			}
			System.out.print("Which ID would you like to edit: ");
			int componentID = in.nextInt();

			RecipeImport toUpdate = rih.searchForItemByID(componentID);
			System.out.println("Recipe component " + toUpdate.getComponent() + ".");
			System.out.println("1 : Update quantity.");
			System.out.println("2 : Update measurement.");
			System.out.println("3 : Update component.");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New quantity: ");
				Double newQuantity = in.nextDouble();
				toUpdate.setQuantity(newQuantity);
			} 
			else if (update == 2) {
				System.out.print("New measurement: ");
				String newMeasurement = in.nextLine();
				toUpdate.setMeasurement(newMeasurement);
			}
			else if (update == 3) {
				System.out.print("New component: ");
				String newComponent = in.nextLine();
				toUpdate.setComponent(newComponent);
			}

			rih.updateRecipe(toUpdate);

		} else {
			System.out.println("That item is not in the recipe.");
		}*/
	}

	public static void main(String[] args) {
		runMenu();
	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Recipe Creator, please select and option from the below list:");
		while (goAgain) {
			System.out.println("*  1 -- Add an item to your recipe.");
			System.out.println("*  2 -- Edit a recipe.");
			System.out.println("*  3 -- Remove a recipe component.");
			System.out.println("*  4 -- View your recipe.");
			System.out.println("*  5 -- Quit.");
			System.out.print("Enter 1 - 5 now - ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addLineItem();
			} 
			else if (selection == 2) {
				recipeUpdate();
			} 
			else if (selection == 3) {
				deleteAnItem();
			} 
			else if (selection == 4) {
				showRecipe();
			} 
			else {
				rih.tidy();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void showRecipe() {
	}


}

