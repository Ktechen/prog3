package controller.handleInput.show;

public class ShowOption {

    private ShowController showController;

    public ShowOption() {
        this.showController = new ShowController();
    }

    public StringBuffer run(String nr, String value) {
        switch (nr) {
            case "1":
                return this.showController.showFilter(value);
            case "2":
                return this.showController.showUsername(value);
            case "3":
                return this.showController.showUsedTags();
        }

        return null;
    }
}
