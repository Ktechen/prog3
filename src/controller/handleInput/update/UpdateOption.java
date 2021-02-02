package controller.handleInput.update;

public class UpdateOption {

    private UpdateController updateController;

    public UpdateOption() {
        this.updateController = new UpdateController();
    }

    public String run(String address) {
        this.updateController.update(address);
        return "Update Address";
    }
}
