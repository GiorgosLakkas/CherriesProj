import java.util.ArrayList;
import java.util.List;

class User {
    private List<String> characteristics;

    public User(List<String> characteristics) {
        if (characteristics.size() != 22) {
            throw new IllegalArgumentException("There must be exactly 22 characteristics.");
        }
        this.characteristics = new ArrayList<>(characteristics);
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }
}
