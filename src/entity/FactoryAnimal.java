package entity;

public class FactoryAnimal {

	public AnimalImpl getChicken(Pair<Integer, Integer> position) {
		return new AnimalImpl(position, AnimalType.CHICKEN);
	}
	
	public AnimalImpl getCow(Pair<Integer, Integer> position) {
		return new AnimalImpl(position, AnimalType.COW);
	}
	
	public AnimalImpl getPig(Pair<Integer, Integer> position) {
		return new AnimalImpl(position, AnimalType.PIG);
	}
	
	public AnimalImpl generateAnimal(Pair<Integer, Integer> position, AnimalType type) {
		return new AnimalImpl(position, type);
	}
}
