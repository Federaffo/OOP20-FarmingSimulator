package entity;

public class FactoryAnimal {

	public Animal getChicken(Pair<Integer, Integer> position) {
		return new Animal(position, AnimalType.CHICKEN);
	}
	
	public Animal getCow(Pair<Integer, Integer> position) {
		return new Animal(position, AnimalType.COW);
	}
	
	public Animal getPig(Pair<Integer, Integer> position) {
		return new Animal(position, AnimalType.PIG);
	}
	
	public Animal generateAnimal(Pair<Integer, Integer> position, AnimalType type) {
		return new Animal(position, type);
	}
}
