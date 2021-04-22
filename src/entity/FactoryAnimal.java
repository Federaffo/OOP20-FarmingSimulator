package entity;

public class FactoryAnimal {

    /**
     * @param position
     * @return an animal of type chicken
     */
    public AnimalImpl getChicken(final Pair<Integer, Integer> position) {
        return new AnimalImpl(position, AnimalType.CHICKEN);
    }

    /**
     * @param position
     * @return an animal of type cow
     */
    public AnimalImpl getCow(final Pair<Integer, Integer> position) {
        return new AnimalImpl(position, AnimalType.COW);
    }

    /**
     * @param position
     * @return an animal of type pig
     */
    public AnimalImpl getPig(final Pair<Integer, Integer> position) {
        return new AnimalImpl(position, AnimalType.PIG);
    }

    /**
     * @param position
     * @param type
     * @return an animal of type [type]
     */
    public AnimalImpl generateAnimal(final Pair<Integer, Integer> position, final AnimalType type) {
        return new AnimalImpl(position, type);
    }
}
