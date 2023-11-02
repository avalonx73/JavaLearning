package classes;

public abstract class AbstractService {

    public void search() {
        System.out.println("AbstractService.search");
        matching();
    }

    private void matching() {

        System.out.println("AbstractService.matching");
    }
}
