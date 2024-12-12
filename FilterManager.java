import java.util.ArrayList;
import java.util.List;

public class FilterManager {
    private List<FilterCriteria> selectedFilters;
    private int maxFilters;

    public FilterManager(int maxFilters) {
        this.maxFilters = maxFilters;
        this.selectedFilters = new ArrayList<>();
    }

    public boolean addFilter(FilterCriteria filter) {
        if (selectedFilters.size() < maxFilters) {
            selectedFilters.add(filter);
            return true;
        }
        return false; // Returns false if max filters reached
    }

    public boolean removeFilter(FilterCriteria filter) {
        return selectedFilters.remove(filter);
    }

    public List<FilterCriteria> getSelectedFilters() {
        return new ArrayList<>(selectedFilters);
    }

    public void clearFilters() {
        selectedFilters.clear();
    }

    public boolean isFilterSelectionFull() {
        return selectedFilters.size() >= maxFilters;
    }
}

